<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>授权页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<!-- jqgrid-->
<link href="${path }/resources/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<input type="hidden" id="hide_roleid" name = "roleid" value="${roleid }"/>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_role_menu_operation"></table>
						</div>
						<div align="right">
							<button class="btn btn_default" id="btn_rightCtrl_ok"><span class="glyphicon glyphicon-ok-sign"></span>授权</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Peity -->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- jqGrid -->
	<script src="${path }/resources/js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
	<script src="${path }/resources/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
	
	<!-- 自定义js -->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	
	<!-- layer javascript -->
    <script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
    
    <script type="text/javascript">
    $(function () {
    	init();
		$("#btn_rightCtrl_ok").click(function(){
			var roleId = $("#hide_roleid").val();
			var menuArrIds = new Array();
			var operationArrIds = new Array();
			$("input:checkbox[name='menuids']:checked").each(function() {
				menuArrIds.push($(this).val());
			});
			$("input:checkbox[name='operationids']:checked").each(function() {
				operationArrIds.push($(this).val());
			});
			
			var menuIds = menuArrIds.join(",");
			var operationIds = operationArrIds.join(",");
			$.post("updateRoleMenu.htm",{menuids:menuIds,operationids:operationIds,roleid:roleId},
			function(result){
				if(result.success){
					layer.alert('授权成功！', {icon: 1});
					init();
				}else{
					layer.alert(result.errorMsg, {icon: 2});
				}
			},"json");
		});
    	
    });
    
    var init = function(){
	    $.jgrid.defaults.styleUI = 'Bootstrap';
		$("#table_role_menu_operation").jqGrid({
			url : 'chooseMenu.htm',
			postData : {
				'parentid' : "-1",
				'roleid' : $("#hide_roleid").val()
				
			}, //发送数据  
			datatype : "json",
			treeGrid : true,//ture则为树形表格
			treeGridModel : "adjacency",
			ExpandColumn : "menuname",//展开的列
			ExpandColClick : true,//树形表格是否展开
			height : "400",
			autowidth : true,
			colNames : [ '菜单编号', '父节点', '状态', '菜单名称','包含按钮'],
			colModel : [ {
				name : "menuid",
				index : "menuid",
				hidden : true,
				key : true
			}, {
				name : "parentid",
				index : "parentid",
				hidden : true,
			}, {
				name : "state",
				index : "state",
				hidden : true
			}, {
				name : "menuname",
				index : "menuname",
				resizable: true,
				formatter : function(cellvalue, options, rowObject){
					var rowId = rowObject.menuid;
					var checkbox = '<label >'+
										'<input type="checkbox" id="chxm'+rowId+'" class="ace" name="menuids" value="'+rowId+'"'+
										' onclick="menuCheckbox('+rowId+', this);"';
										if(rowObject.checked){
											checkbox += ' checked="'+rowObject.checked+'"';
										}
										checkbox += ' /><span class="'+rowObject.iconcls+'" ></span>'+ rowObject.menuname +
									'</label>';
					return checkbox;
				}
			}, {
				name : "operations",
				formatter : function(cellvalue, options, rowObject){
					var checkboxes = "";
					var $operations = rowObject.operations;
					if($operations && $operations.length>0){
						$operations.forEach(function(operation){
							var checkbox ='<label >'+
												'<input type="checkbox" id="chxo'+operation.operationid+'" class="ace" name="operationids" value="'+operation.operationid+'"'+
												' onclick="operationCheckbox('+operation.operationid+', this);"'
												if(operation.checked){
													checkbox += ' checked="'+operation.checked+'"';
												}
												checkbox +=' /><span class="'+operation.iconcls+'" ></span>'+ operation.operationname +
											'</label>';
							checkboxes += checkbox;
						});
					}
					return checkboxes;
				}
			}],
			viewrecords : true,
			jsonReader : {
				root : "dataRows",
				repeatitems : false
			},
			treeReader : {
				level_field : "level",
				parent_id_field : "parent",
				leaf_field : "isLeaf",
				expanded_field : "expanded"
			},
			sortorder : "asc",
		});
    };
    
 	var menuCheckbox = function(i,o){
    	
    };
    
    var operationCheckbox = function(i,o){
    	
    };
    
    </script>
</body>
</html>