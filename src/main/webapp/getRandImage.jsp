<%@page import="com.xiaoshu.util.CaptchaUtil"%>
<%@ page contentType="image/jpeg"
	import="java.awt.*,
java.awt.image.*,java.util.*,javax.imageio.*" pageEncoding="utf-8"%>
<%
out.clear();
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
CaptchaUtil captchaUtil = CaptchaUtil.getInstance();
String sRand = captchaUtil.getString();
request.getSession().setAttribute("sRand", sRand);
// 将验证码存入SESSION
session.setAttribute("sRand",sRand);
ImageIO.write(captchaUtil.getImage(), "JPEG", response.getOutputStream());
%>