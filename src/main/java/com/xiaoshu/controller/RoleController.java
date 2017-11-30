package com.xiaoshu.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.entity.Role;
import com.xiaoshu.service.MenuService;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.service.RoleService;
import com.xiaoshu.service.UserService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private OperationService operationService;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	static Logger logger = Logger.getLogger(RoleController.class);
	
	
	@RequestMapping("roleIndex")
	public String index(HttpServletRequest request,Integer menuid){
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		return "role";
	}
	
	@RequestMapping("roleList")
	public void userList(HttpServletRequest request,HttpServletResponse response,String offset,String limit){
		try {
			Role role = new Role();
			String rolename = request.getParameter("rolename");
			String order = request.getParameter("order");
			String ordername = request.getParameter("ordername");
			if (StringUtil.isNotEmpty(rolename)) {
				role.setRolename(rolename);
			}
			role.setRolename(rolename);
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Role> pageInfo = roleService.findRolePage(role,pageNum,pageSize,ordername,order);
			JSONObject jsonObj = new JSONObject();
			request.setAttribute("rolename",rolename);
			jsonObj.put("total",pageInfo.getTotal() );
			jsonObj.put("rows", pageInfo.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色展示错误",e);
		}
	}
	
	private List<Role> findAllRole(Role role){
		try {
			return roleService.findRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("roleCombobox")
	public void roleCombobox(HttpServletRequest request,HttpServletResponse response){
		try {
			JSONArray jsonArray=new JSONArray();
			List<Role> list = findAllRole(new Role());
			jsonArray.addAll(list);
			WriterUtil.write(response, jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("reserveRole")
	public void addUser(HttpServletRequest request,Role role,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			if (role.getRoleid() != null ) {
				role.setRoleid(role.getRoleid());
				roleService.updateRole(role);
				result.put("success", true);
			}else {
				if(roleService.existRoleWithRoleName(role.getRolename())==null){  // 没有重复可以添加
					roleService.addRole(role);
					result.put("success", true);
				} else {
					result.put("success", true);
					result.put("errorMsg", "该角色名被使用");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色保存错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	
	@RequestMapping("deleteRole")
	public void delRole(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] roleIds = request.getParameter("ids").split(",");
			for (int i=0;i<roleIds.length;i++) {
				boolean b = userService.existUserWithRoleId(Integer.parseInt(roleIds[i]))==null; //该角色下面没有用户
				if(!b){
					result.put("errorIndex", i);
					result.put("errorMsg", "有角色下面有用户，不能删除");
					WriterUtil.write(response, result.toString());
					return;
				}
			}
			if (roleIds.length==1) {
				roleService.deleteRole(Integer.parseInt(roleIds[0]));
			} else {
				roleService.deleteRoleByRoleIds(roleIds);
			}
			result.put("success", true);
			result.put("delNums", roleIds.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("角色删除错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping("rightCtrl")
	public String chooseMenu(HttpServletRequest request,Integer roleid){
		request.setAttribute("roleid",roleid);
		return "rightCtrl";
	}
	
	@RequestMapping("chooseMenu")
	public void chooseMenu(HttpServletRequest request,HttpServletResponse response,String parentid,Integer roleid){
		try {
			Role role = roleService.findOneRole(roleid);
			String menuIds = role.getMenuids();
			String operationIds = role.getOperationids();
			JSONArray jsonArray = getCheckedMenusByParentId(parentid, menuIds,operationIds,0);
			WriterUtil.write(response, jsonArray.toString());
			System.out.println(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加载权限菜单树错误",e);
		}
	}
	
	// 选中已有的角色
	public JSONArray getCheckedMenusByParentId(String parentId,String menuIds,String operationIds,int l)throws Exception{
		JSONArray resultJsonArray = new JSONArray();
		JSONArray jsonArray = this.getCheckedMenuByParentId(parentId,menuIds,operationIds,l);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			resultJsonArray.add(jsonObject);
			if(!"isParent".equals(jsonObject.getString("state"))){
				continue;
			}else{
				resultJsonArray.addAll(getCheckedMenusByParentId(jsonObject.getString("menuid"),menuIds,operationIds,++l));
			}
		}
		return resultJsonArray;
	}
	
	public JSONArray getCheckedMenuByParentId(String parentId,String menuIds,String operationIds,int l)throws Exception{
		JSONArray jsonArray = new JSONArray();
		Menu menu = new Menu();
		menu.setParentid(Integer.parseInt(parentId));
		List<Menu> list = menuService.findMenu(menu);
		for(Menu m : list){
			JSONObject jsonObject = new JSONObject();
			int menuId = m.getMenuid();
			jsonObject.put("menuid", menuId);
			jsonObject.put("menuname", m.getMenuname());
			jsonObject.put("parentid", m.getParentid());
			jsonObject.put("iconcls", m.getIconcls());
			jsonObject.put("state", m.getState());
			jsonObject.put("seq", m.getSeq());
			jsonObject.put("menuurl", m.getMenuurl());
			jsonObject.put("menudescription", m.getMenudescription());
			jsonObject.put("level", l);
			jsonObject.put("isLeaf", (StringUtil.isEmpty(m.getState())||"close".equals(m.getState()) ));
			jsonObject.put("parent", m.getParentid().compareTo(new Integer(0))>0?m.getParentid():null);
			jsonObject.put("laoded", true);
			jsonObject.put("expanded", true);
			if (StringUtil.isNotEmpty(menuIds)) {
				if (StringUtil.existStrArr(menuId+"", menuIds.split(","))) {
					jsonObject.put("checked", true);
				} 	
			}
			jsonObject.put("operations", getOperationJsonArray(menuId,operationIds));
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
	
	
	public JSONArray getOperationJsonArray(int menuId,String operationIds){
		JSONArray jsonArray = new JSONArray();
		try {
			Operation operation = new Operation();
			operation.setMenuid(menuId);
			List<Operation> list = operationService.findOperation(operation);
			for(Operation o : list){
				JSONObject jsonObject = new JSONObject();
				int operationId = o.getOperationid();
				jsonObject.put("operationid", operationId);
				jsonObject.put("operationname", o.getOperationname());
				jsonObject.put("iconcls", o.getIconcls());
				if (StringUtil.isNotEmpty(operationIds)) {
					if (StringUtil.existStrArr(operationId+"", operationIds.split(","))) {
						jsonObject.put("checked", true);
					} 	
				}
				jsonArray.add(jsonObject);
			}
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("updateRoleMenu")
	public void updateRoleMenu(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		try {
			String roleid = request.getParameter("roleid");
			String menuids = request.getParameter("menuids");
			String operationids = request.getParameter("operationids");
			Role role = new Role();
			role.setRoleid(Integer.parseInt(roleid));
			if (StringUtil.isNotEmpty(menuids)) {
				String[] menuArrIds = menuids.split(",");
				String menuidsStr = getMenuIdAndParentMenuId(menuArrIds);
				role.setMenuids(menuidsStr);
			}
			if (StringUtil.isNotEmpty(operationids)) {
				role.setOperationids(operationids);
			}
			roleService.updateRole(role);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("授权失败",e);
			result.put("errorMsg", "对不起，授权失败");
		}
		WriterUtil.write(response, result.toString());
	}

	private String getMenuIdAndParentMenuId(String[] menuArrIds) {
		Set<Integer> menuSetIds = StringUtil.stringArrToIntergerSet(menuArrIds);
		Iterator<Integer> it = menuSetIds.iterator();
		Set<Integer> menuIds = new HashSet<Integer>();
		while (it.hasNext()) {
			Integer menuid = it.next();
			addParentMenuid(menuSetIds,menuIds,menuid);
		}
		menuSetIds.addAll(menuIds);
		return StringUtils.join(menuSetIds,",");
	}
	
	private void addParentMenuid(Set<Integer> menuSetIds,Set<Integer> menuIds,Integer menuid){
		Menu menu = menuService.findMenuByMenuid(menuid);
		Integer parentid = menu.getParentid();
		if(parentid.compareTo(0)>0){
			if(!menuSetIds.contains(parentid)){
				menuIds.add(parentid);
				addParentMenuid(menuSetIds,menuIds,parentid);
			}
		}
	}
}
