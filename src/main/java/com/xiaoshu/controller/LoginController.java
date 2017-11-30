package com.xiaoshu.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Role;
import com.xiaoshu.entity.Token;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.LogService;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.TokenService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.CodeUtil;
import com.xiaoshu.util.IpUtil;
import com.xiaoshu.util.StochasticUtil;
import com.xiaoshu.util.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@SuppressWarnings("unchecked")
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private LogService logService;
	@Autowired
	private TokenService tokenService;
	
	static Logger logger = Logger.getLogger(LoginController.class);
	
	
	@SuppressWarnings("static-access")
	@RequestMapping("login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			HttpSession session = request.getSession();
			String userName=request.getParameter("userName");
			String password=request.getParameter("password");
			String imageCode=request.getParameter("imageCode");
			String auto = request.getParameter("auto");
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("imageCode", imageCode);
			if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
				request.setAttribute("error", "账户或密码为空");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			if(StringUtil.isEmpty(imageCode)){
				request.setAttribute("error", "验证码为空");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			if(!imageCode.toUpperCase().equals(session.getAttribute("sRand").toString().toUpperCase())){
				request.setAttribute("error", "验证码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			User user = new User();
			user.setUsername(userName);
			user.setPassword(password);
			User currentUser = userService.loginUser(user);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else{
				// 加入登陆日志
				Log log = new Log();
				log.setUsername(userName);
				log.setCreatetime(new Date());
				log.setIp(IpUtil.getIpAddr(request));
				log.setOperation("登录");
				logService.insertLog(log);
				
				// 登录信息存入session
				Role role = roleService.findOneRole(currentUser.getRoleid());
				String roleName = role.getRolename();
				currentUser.setRoleName(roleName);
				session.setAttribute("currentUser", currentUser);  // 当前用户信息
				session.setAttribute("currentOperationIds", role.getOperationids());  // 当前用户所拥有的按钮权限
				
				// 勾选了两周内自动登录。
				if ("on".equals(auto)) {
					// 记住登录信息
					Token token = new Token();
					token.setUserid(currentUser.getUserid());
					String userAgent = StochasticUtil.getUUID();
					token.setUseragent(CodeUtil.getMd5(userAgent, 32));
					token.setCreatetime(new Date());
					Calendar cal = Calendar.getInstance();
					cal.add(cal.WEEK_OF_YEAR, 2);
					token.setExpiretime(cal.getTime());
					String t = CodeUtil.getMd5(currentUser.getUsername()+CodeUtil.getMd5(userAgent, 32), 32);
					token.setToken(t);
					tokenService.insertToken(token);
					
					// 设置cookie
					Cookie cookie = new Cookie("autoLogin",t);
					cookie.setMaxAge(3600*24*15);  // cookie时效15天
					response.addCookie(cookie);
				}
				
				// 跳转到主界面
				response.sendRedirect("main.htm");
			}
		} catch (Exception e) {
			logger.error("用户登录错误",e);
			throw e;
		}
	}
	
	
	// 进入系统主界面
	@RequestMapping("main")
	public String toMain(HttpServletRequest request,HttpServletResponse response) throws Exception{
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser == null ){
			return null;
		}
		getMenuTree("-1",currentUser,request,response);
		return "main";
	}
	
	// 进入系统主界面
	@RequestMapping("index")
	public String toIndex(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}
	
	
	// 加载最上级左菜单树
	public void getMenuTree(String parentId,User currentUser,HttpServletRequest request,HttpServletResponse response) throws Exception{
		try {
			
			Role role = roleService.findOneRole(currentUser.getRoleid());
			if(role != null && StringUtil.isNotEmpty(role.getMenuids())){
				String[] menuIds = role.getMenuids().split(",");
				Map map = new HashMap();
				map.put("parentId",parentId);
				map.put("menuIds", menuIds);
				JSONArray jsonArray = getMenusByParentId(parentId, menuIds);
				request.setAttribute("menuTree", jsonArray.get(0));
			}
		} catch (Exception e) {
			logger.error("加载左菜单错误",e);
			throw e;
		}
	}
	
	
	// 递归加载所所有树菜单
	public JSONArray getMenusByParentId(String parentId,String[] menuIds)throws Exception{
		JSONArray jsonArray = this.getMenuByParentId(parentId,menuIds);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if(!"isParent".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getMenusByParentId(jsonObject.getString("id"),menuIds));
			}
		}
		return jsonArray;
	}
	
	
	// 将所有的树菜单放入json数据中
	public JSONArray getMenuByParentId(String parentId,String[] menuIds)throws Exception{
		JSONArray jsonArray=new JSONArray();
		Map map= new HashMap();
		map.put("parentId",Integer.parseInt(parentId));
		map.put("menuIds", menuIds);
		List<Menu> list = menuService.menuTree(map);
		for(Menu menu : list){
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", menu.getMenuid());
			jsonObject.put("text", menu.getMenuname());
			jsonObject.put("iconCls", menu.getIconcls());
			JSONObject attributeObject = new JSONObject();
			attributeObject.put("menuUrl", menu.getMenuurl());
			jsonObject.put("state", menu.getState());				
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	
	// 判断是不是有子孩子，人工结束递归树
	public boolean hasChildren(Integer parentId,String[] menuIds) throws Exception{
		boolean flag = false;
		try {
			Map map= new HashMap();
			map.put("parentId",parentId);
			map.put("menuIds", menuIds);
			List<Menu> list = menuService.menuTree(map);
			if (list == null || list.size()==0) {
				flag = false;
			}else {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("加载左菜单时判断是不是有子孩子错误",e);
			throw e;
		}
		return flag;
	}
	
	//安全退出
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		// 记录日志
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Log log = new Log();
		log.setUsername(currentUser.getUsername());
		log.setCreatetime(new Date());
		log.setOperation("退出");
		logService.insertLog(log);
		
		// 清空session
		request.getSession().invalidate();
		
		// 清空cookie
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = new Cookie(cookies[i].getName(), null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		response.sendRedirect("login.jsp");
	}
	
	
	
	/**
	 * 自动登录
	 * @param request
	 * @param response
	 */
	@RequestMapping("auto")
	public void autoLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null) {
        	for(int i=0; i<cookies.length; i++) {
           		Cookie cookie = cookies[i];
           		if("autoLogin".equals(cookie.getName())){
					  Map map = new HashMap();
					  map.put("token", cookie.getValue());
					  map.put("expireTime", new Date());
					  Token token = tokenService.findOneToken(map);
					  if (token == null) {
						  request.getRequestDispatcher("login.jsp").forward(request, response);
						  return;
					  } else {
						  	int userId = token.getUserid();
						  	User currentUser = userService.findOneUser(userId);
						  	Log log = new Log();
							log.setUsername(currentUser.getUsername());
							log.setCreatetime(new Date());
							log.setIp(IpUtil.getIpAddr(request));
							log.setOperation("登录");
							logService.insertLog(log);
							
							// 登录信息存入session
							Role role = roleService.findOneRole(currentUser.getRoleid());
							String roleName = role.getRolename();
							currentUser.setRoleName(roleName);
							HttpSession session = request.getSession();
							session.setAttribute("currentUser", currentUser);  // 当前用户信息
							session.setAttribute("currentOperationIds", role.getOperationids());  // 当前用户所拥有的按钮权限
							// 跳转到主界面
							response.sendRedirect("main.htm");
							return;
					  }
           		}
        	}
    	}
    	request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
}
