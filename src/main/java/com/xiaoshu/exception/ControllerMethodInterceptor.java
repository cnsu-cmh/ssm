package com.xiaoshu.exception;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.xiaoshu.entity.Log;
import com.xiaoshu.entity.User;
import com.xiaoshu.service.LogService;
import com.xiaoshu.util.IpUtil;
import com.xiaoshu.util.TimeUtil;
import com.xiaoshu.util.WriterUtil;

public class ControllerMethodInterceptor implements MethodInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerMethodInterceptor.class);
	private static final String NEWLINE = System.getProperty("line.separator");
	
	@Autowired
	private LogService logService;

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object proceed = null;
		StringBuffer info = new StringBuffer();
		info.append("intercept the method: ");
		Class<?> clazz = invocation.getMethod().getDeclaringClass();
		Method method = invocation.getMethod();
		info.append(clazz.getName());  
		info.append(".");  
		info.append(method.getName());
		try {  
			proceed = invocation.proceed();
		} catch (Exception e) {
			proceed = null;
			logger.debug("出错了======>"+info.toString()+"========>"+TimeUtil.formatTime(new Date(), "yyyy-MM-dd HH:mm:ss"));
			logger.debug(NEWLINE+e.getMessage());
			Log log = new Log();
			HttpServletRequest request = getCurrentRequest();
			if(request != null){
				User currentUser = (User) request.getSession().getAttribute("currentUser");
				if(currentUser == null ){
					return proceed;
				}
				log.setUsername(currentUser.getUsername()); 
				log.setModule(request.getContextPath());
			}
			log.setContent(info.append(e.getMessage()).toString());//操作内容  
			//利用反射调用invoke得到方法返回结果
//			try {
//				Object res = method.invoke(clazz.newInstance(),invocation.getArguments());
//				log.setContent((info+"--->"+res).toString());//操作内容  
//			} catch (Exception e2) {
//				logger.debug("ControllerMethodInterceptor中调用反射异常");
//			}
			
	        log.setCreatetime(new Date());//操作时间  
	        log.setOperation("拦截到异常");//操作
	        log.setIp(IpUtil.getIpAddr(request));
			logService.insertLog(log);
			JSONObject result = new JSONObject();
			result.put("errorMsg", "对不起！系统错误，请联系管理员。");
			WriterUtil.write(getCurrentResponse(), result.toString());
		}
		return proceed;
	}
	
	private HttpServletRequest getCurrentRequest(){
        ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttrs == null) {
        	logger.debug("当前线程中不存在 Request 上下文");
        	return null;
        }else{
        	return requestAttrs.getRequest();
        }
        
    }
	
	private HttpServletResponse getCurrentResponse(){
		ServletRequestAttributes requestAttrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		 if (requestAttrs == null) {
        	logger.debug("当前线程中不存在 Request 上下文");
        	return null;
        }else{
        	return requestAttrs.getResponse();
        }
    }
	
	
	
}
