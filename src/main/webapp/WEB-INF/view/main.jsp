<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>
<link href="${path }/resources/css/style.css" rel="stylesheet">
<script src="${path }/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${path }/resources/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
<script src="${path }/resources/js/hplus.js?v=4.1.0"></script>
<script src="${path }/resources/js/contabs.js"></script>
<script src="${path }/resources/js/plugins/pace/pace.min.js"></script>
<script src="${path }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	function editPassword () {
		layer.open({
			  type: 1,
			  title: '修改密码',
			  skin: 'layui-layer-rim',
			  area: ['600px', '400px'],
			  content: $('#editPasswordDiv')
			});
	}
	
	function checkPassword () {
		var oldpassword = $.trim($("#oldpassword").val());
		var newpassword = $.trim($("#newpassword").val());
		var newpassword2 = $.trim($("#newpassword2").val());
		console.info(oldpassword);
		console.info(newpassword);
		console.info(newpassword2);
		if(oldpassword && newpassword && newpassword2 && oldpassword != '' && newpassword != '' && newpassword2 != ''){
			if(newpassword == newpassword2){
				return true;
			}else{
				layer.alert("两次输入密码不一致!",{icon: 2});
				return false;
			}
		}else{
			layer.alert("页面所有项都为必填项!",{icon: 2});
			return false;
		}
	}
	
	function submitEditPassword () {
		if(checkPassword()){
			$("#editPasswordForm").ajaxSubmit({
		    	dataType:"json",
		    	success: function (res) {
		    		if(res.success){
		    			layer.alert("修改成功！",{icon: 1,closeBtn: 0,anim: 4},function(){
		    				window.location.reload();
		    			});
		    		}else{
		    			layer.alert(res.errorMsg,{icon: 2});
		    		}
            	}
      		});  
		}
	}
</script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">

	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<span><img alt="image" class="img-circle"
								src="${path }/resources/img/profile_small.jpg" /></span> <a
								data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
								class="clear"> <span class="block m-t-xs"><strong
										class="font-bold">${currentUser.username }</strong></span> <span
									class="text-muted text-xs block">${currentUser.roleName }<b
										class="caret"></b></span>
							</span>
							</a>
							<ul class="dropdown-menu animated fadeInRight m-t-xs">
								<li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
								</li>
								<li><a class="J_menuItem" href="profile.html">个人资料</a></li>
								<li><a class="J_menuItem" href="contacts.html">联系我们</a></li>
								<li><a class="J_menuItem" href="mailbox.html">信箱</a></li>
								<li class="divider"></li>
								<li><a href="logout.htm">安全退出</a></li>
							</ul>
						</div>
						<div class="logo-element">H+</div>
					</li>
					<c:forEach items="${menuTree.children }" var="menu">
						<li><a href="${menu.attributes.menuUrl }?menuid=${menu.id}">
						 <i class="${menu.iconCls }"></i> <span class="nav-label">${menu.text }</span>
								<c:if test="${menu.state eq 'isParent'}"><span class="fa arrow"></span></c:if> 
							</a> 
							<c:if test="${fn:length(menu.children) gt 0 }">
								<ul class="nav nav-second-level">
									<c:forEach items="${menu.children }" var="menu_c">
										<li><a class="J_menuItem" href="${menu_c.attributes.menuUrl }?menuid=${menu_c.id}">
											<i class="${menu_c.iconCls }"></i><span class="nav-label">${menu_c.text }</span>
											<c:if test="${menu_c.state eq 'isParent'}"><span class="fa arrow"></span></c:if>
											</a>
										<c:if test="${fn:length(menu_c.children) gt 0 }">
											<ul class="nav nav-third-level">
												<c:forEach items="${menu_c.children}" var="menu_c_c">
													<li><a class="J_menuItem" href="${menu_c_c.attributes.menuUrl }?menuid=${menu_c_c.id}">
														<i class="${menu_c_c.iconCls }"></i><span class="nav-label">${menu_c_c.text }</span></a>
													</li>
												</c:forEach>
											</ul>
										</c:if>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</li>
					</c:forEach>
				</ul>
			</div>
		</nav>
		<!--左侧导航结束-->

		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
							href="javascript:void(0)"><i class="fa fa-bars"></i> </a>
						<form role="search" class="navbar-form-custom" method="post"
							action="search_results.html">
							<div class="form-group">
								<input type="text" placeholder="请输入您需要查找的内容 …"
									class="form-control" name="top-search" id="top-search">
							</div>
						</form>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
								<span class="label label-warning">16</span>
						</a>
						</li>
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-bell"></i> <span
								class="label label-primary">8</span>
						</a>
						</li>
						<li class="dropdown hidden-xs"><a
							class="right-sidebar-toggle" aria-expanded="false"> <i
								class="fa fa-tasks"></i> 主题
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
					<div class="page-tabs-content">
						<a href="javascript:;" class="active J_menuTab"
							data-id="index_v1.html">首页</a>
					</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="login.html" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="index.htm?v=4.0" frameborder="0" data-id="index.html"
					seamless></iframe>
			</div>
			<div class="footer">
				<div class="pull-right">
					&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's
						blog</a>
				</div>
			</div>
		</div>
		<!--右侧部分结束-->
		<!--右侧边栏开始-->
		<div id="right-sidebar">
			<div class="sidebar-container">

				<ul class="nav nav-tabs navs-3">

					<li class="active"><a data-toggle="tab" href="#tab-1"> <i
							class="fa fa-gear"></i> 主题
					</a></li>
					<li class=""><a data-toggle="tab" href="#tab-2"> 通知 </a></li>
					<li><a data-toggle="tab" href="#tab-3"> 项目进度 </a></li>
				</ul>

				<div class="tab-content">
					<div id="tab-1" class="tab-pane active">
						<div class="sidebar-title">
							<h3>
								<i class="fa fa-comments-o"></i> 主题设置
							</h3>
							<small><i class="fa fa-tim"></i>
								你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
						</div>
						<div class="skin-setttings">
							<div class="title">主题设置</div>
							<div class="setings-item">
								<span>收起左侧菜单</span>
								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="collapsemenu"
											class="onoffswitch-checkbox" id="collapsemenu"> <label
											class="onoffswitch-label" for="collapsemenu"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="setings-item">
								<span>固定顶部</span>

								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="fixednavbar"
											class="onoffswitch-checkbox" id="fixednavbar"> <label
											class="onoffswitch-label" for="fixednavbar"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="setings-item">
								<span> 固定宽度 </span>

								<div class="switch">
									<div class="onoffswitch">
										<input type="checkbox" name="boxedlayout"
											class="onoffswitch-checkbox" id="boxedlayout"> <label
											class="onoffswitch-label" for="boxedlayout"> <span
											class="onoffswitch-inner"></span> <span
											class="onoffswitch-switch"></span>
										</label>
									</div>
								</div>
							</div>
							<div class="title">皮肤选择</div>
							<div class="setings-item default-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-0">
										默认皮肤 </a>
								</span>
							</div>
							<div class="setings-item blue-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-1">
										蓝色主题 </a>
								</span>
							</div>
							<div class="setings-item yellow-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-3">
										黄色/紫色主题 </a>
								</span>
							</div>
						</div>
					</div>
					<div id="tab-2" class="tab-pane">

					</div>
					<div id="tab-3" class="tab-pane">


					</div>
				</div>

			</div>
		</div>
		<!--右侧边栏结束-->
		<div id="editPasswordDiv" style="display: none">
			<form class="form-horizontal m-t" role="form" id="editPasswordForm" action="user/editPassword.htm" method="post">
                <div class="form-group">
                	<label class="col-sm-3 control-label">旧密码:</label>
                	<div class="col-sm-8">
                    	<input type="password" name="oldpassword"  id="oldpassword" class="form-control" placeholder="请输入旧密码" required >
                	</div>
                </div>
                <div class="form-group">
                	<label class="col-sm-3 control-label">新密码:</label>
                	<div class="col-sm-8">
                    	<input type="password" name="newpassword" id="newpassword" class="form-control" placeholder="请输入新密码" required >
                	</div>
                </div>
                <div class="form-group">
                	<label class="col-sm-3 control-label">确认密码:</label>
                    <div class="col-sm-8">
                    	<input type="password" name="newpassword2" id="newpassword2" class="form-control" placeholder="请再次输入密码" required >
                	</div>
                </div>
                <button type="button" class="btn btn-primary block full-width m-b" onclick="submitEditPassword()">修改</button>
            </form>
		</div>
		
	</div>

</body>

</html>
