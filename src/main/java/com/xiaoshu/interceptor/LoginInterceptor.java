package com.xiaoshu.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xiaoshu.entity.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 请求的路径
		String url = request.getServletPath().toString();
		HttpSession session = request.getSession();
		User currentUser = ((User) session.getAttribute("currentUser"));
		if (currentUser == null) {
			if (url.contains("login") || url.contains("auto")) {
				return true;
			}else{
				Cookie[] cookies = request.getCookies();
				if(cookies != null){
					for(int i=0; i<cookies.length; i++) {
		           		Cookie cookie = cookies[i];
		           		if("autoLogin".equals(cookie.getName())){
		           			response.sendRedirect("auto.htm");
		        	        return true;
		           		}
	           		}
				}
			}
			request.getRequestDispatcher("login.jsp").forward(request, response);
	        return true;
		}
		return true;
	}
}
