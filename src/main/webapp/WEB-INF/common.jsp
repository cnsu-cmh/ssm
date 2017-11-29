<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri="/privilege"  prefix="privilege" %>

<%
	/**-====================================================================
	*                               基本常量的设定
	*=====================================================================**/
	//设定context path
	String path = request.getContextPath();
	if("/".equals(path.trim())) path = "";
	pageContext.setAttribute("path",path);
%>
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="${path }/resources/css/bootstrap.min.css?v=3.3.6" />
<link rel="stylesheet" type="text/css" href="${path }/resources/css/font-awesome.css?v=4.4.0" />
<link rel="stylesheet" type="text/css" href="${path }/resources/css/animate.css" />
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
<![endif]-->
<!-- JAVASCRIPT -->
<script src="${path }/resources/js/jquery.min.js?v=2.1.4"></script>
<script src="${path }/resources/js/bootstrap.min.js?v=3.3.6"></script>