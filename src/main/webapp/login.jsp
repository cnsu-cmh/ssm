<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>
<link href="${path }/resources/css/login.css" rel="stylesheet">
<script>
	if (window.top !== window.self) {
		window.top.location = window.location;
	}
</script>

<script type="text/javascript">
	function loadimage() {
		document.getElementById("randImage").src = "${path }/getRandImage.jsp?t=" + Math.random();
	}

</script>
<%
boolean auto = false;
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(int i=0; i<cookies.length; i++) {
   		Cookie cookie = cookies[i];
   		if("autoLogin".equals(cookie.getName())){
   			auto = true;
   			break;
   		}
	}
}
if(auto){
	response.sendRedirect("auto.htm");
}
%>
</head>

<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ -SSM- ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>SSM</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form id="form1" name="form1" action="${path }/login.htm" method="post">
                    <h4 class="no-margins">欢迎登录到SSM：</h4>
                    <input type="text" class="form-control uname" placeholder="用户名" name="userName" id="userName" required="required" value="${userName }"/>
                    <input type="password" class="form-control pword m-b" placeholder="密码" id="password" name="password" required="required" value="${password }"
						onkeydown="if(event.keyCode==13)form1.submit()" />
					<input type="text" class="form-control ucode" placeholder="验证码"
						required="required" value="${imageCode }" name="imageCode"
						id="imageCode" onkeydown="if(event.keyCode==13)form1.submit()" />
                   <c:if test="${not empty error }">
                    	<font size="4" color="red"><i class="fa fa-times animated"></i> ${error }</font>
                    </c:if>
                   <p class="text-muted text-center">
					<a href="login.htm"><small>忘记密码了？</small></a> | <input type="checkbox" name="auto" id="auto" >记住我 | <img onclick="javascript:loadimage();" title="换一张试试"
						name="randImage" id="randImage" src="${path }/getRandImage.jsp" height="20" border="1">
				</p>
                    <button type="submit" class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2017 All Rights Reserved. H+
            </div>
        </div>
    </div>
</body>

</html>