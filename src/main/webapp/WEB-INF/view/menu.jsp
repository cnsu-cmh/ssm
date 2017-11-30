<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<!-- jqgrid-->
<link href="${path }/resources/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<c:forEach items="${operationInfo }" var="oper">
		<input type="hidden" id="${oper.key }" value="${oper.value }"/>
	</c:forEach>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_menu"></table>
							<div id="pager_menu"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="ui-jqdialog modal-content" id="alertmod_table_menu_mod"
		dir="ltr" tabindex="-1" role="dialog"
		aria-labelledby="alerthd_table_menu" aria-hidden="true"
		style="width: 200px; height: auto; z-index: 950; overflow: hidden; top: 274px; left: 534px; display: none;">
		<div class="ui-jqdialog-titlebar modal-header" id="alerthd_table_menu"
			style="cursor: move;">
			<span class="ui-jqdialog-title" style="float: left;">注意</span> <a id ="alertmod_table_menu_mod_a"
				class="ui-jqdialog-titlebar-close" style="right: 0.3em;"> <span
				class="glyphicon glyphicon-remove-circle"></span></a>
		</div>
		<div class="ui-jqdialog-content modal-body" id="alertcnt_table_menu">
			<div>请选择记录</div>
			<span tabindex="0"> <span tabindex="-1" id="jqg_alrt"></span></span>
		</div>
		<div
			class="jqResize ui-resizable-handle ui-resizable-se glyphicon glyphicon-import"></div>
	</div>

	<!-- Peity -->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- jqGrid -->
	<script src="${path }/resources/js/plugins/jqgrid/i18n/grid.locale-cn.js?0820"></script>
	<script src="${path }/resources/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>

	<!-- layer javascript -->
    <script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
	<!-- 自定义js -->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	<script type="text/javascript">
		$(function() {
			init();
		});		
		
		var init = function(){
			var o_table = new TableInit();
		    o_table.Init();
		    var o_btn = new ButtonInit();
		    o_btn.Init();
		};
		
		var TableInit = function () {
			var oTable = new Object();
			oTable.Init = function () {
				$.jgrid.defaults.styleUI = 'Bootstrap';
				$("#table_menu").jqGrid({
					url : 'treeGridMenu.htm',
					postData : {
						'parentId' : "-1"
					}, //发送数据  
					datatype : "json",
					treeGrid : true,//ture则为树形表格
					treeGridModel : "adjacency",
					ExpandColumn : "menuname",//展开的列
					ExpandColClick : true,//树形表格是否展开
					height : "400",
					autowidth : true,
					colNames : [ '菜单编号', '父节点', '状态', '菜单名称', '图标',
							'样式','包含按钮', '路径', '顺序', '备注' ],
					colModel : [ {
						name : "menuid",
						index : "menuid",
						hidden : true,
						editable : true,
						key : true
					}, {
						name : "parentid",
						index : "parentid",
						hidden : true,
						editable : true,
						editrules : {
							required : true
						}
					}, {
						name : "state",
						index : "state",
						hidden : true
					}, {
						name : "menuname",
						index : "menuname",
						hidden : false,
						editable : true,
						editrules : {
							required : true
						}
					}, {
						name : "",
						editable : false,
						formatter : function(cellvalue, options, rowObject){
							return '<span class="'+rowObject.iconcls+'" aria-hidden="true"></span>';
						}
					}, {
						name : "iconcls",
						index : "iconcls",
						editable : true
					},{
						name : "operationnames",
						index : "operationnames",
						hidden : false,
						editable : false
					}, {
						name : "menuurl",
						index : "menuurl",
						hidden : false,
						editable : true
					}, {
						name : "seq",
						index : "seq",
						hidden : false,
						editable : true,
						editrules : {
							required : true,
							number : true
						}

					}, {
						name : "menudescription",
						index : "menudescription",
						hidden : false,
						editable : true
					} ],
					pager : "#pager_menu",
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
					editurl : 'reserveMenu.htm'
				});
				//---------------------------------
					
			};
			
			return oTable;
		};

		var ButtonInit = function () {
		    var btnInit = new Object();
		    btnInit.Init = function(){
				// Setup buttons
				var jqnav = $("#table_menu").jqGrid(
					'navGrid',
					'#pager_menu',
					{
						add : ($("#add").val() && $("#add").val() == "true"),
						edit : ($("#edit").val() && $("#edit").val() == "true"),
						del : ($("#del").val() && $("#del").val() == "true"),
						search : false,
						refresh : false,
						addtext : "添加",
						edittext : "编辑",
						deltext : "删除"
					},
					{//edit
						height : 400,
						reloadAfterSubmit : true,
						closeAfterEdit : true
					},
					{//add
						height : 400,
						reloadAfterSubmit : true,
					    beforeShowForm : function(frm) {
					    	var ids = $("#table_menu").jqGrid('getGridParam', 'selrow');
					    	frm.find('#parentid').val(parentid.value == '' ? ids : parentid.value);
						},
						closeAfterAdd : true
					},
					{//del
						url : "deleteMenu.htm",
						afterSubmit: function(xhr, postdata) {
							var obj = eval('(' + xhr.responseText + ')');
	                		if(obj.errorMsg){
	                			layer.alert(obj.errorMsg, {icon: 2});
	                			$("#delmodtable_menu").hide();
	                			return false;
	                		}else{
	                			 $("#table_menu").trigger("reloadGrid");
								$("#delmodtable_menu").hide();
			                	return true;
	                		}
						}
					},
					{}
				);
				
				if($("#btnCtrl").val() && $("#btnCtrl").val() == "true"){
					//Add btn to navGrid
					jqnav.navButtonAdd('#pager_menu', {
						caption: "按钮管理",
						title:"按钮管理",
						buttonicon: "ace-icon fa fa-hand-rock-o",
						onClickButton: function () {  
		                  	var id = $("#table_menu").jqGrid('getGridParam', 'selrow');
							if (!id || id == null) {
								$("#alertmod_table_menu_mod").show();
							}else{
								var rowData =$("#table_menu").jqGrid('getRowData',id);
								if(rowData.state=="isParent" || rowData.state=="close"){
									layer.alert("该菜单不提供按钮,请选择其他菜单,谢谢！",{icon: 5});
									return;
								}
								parent.layer.open({
								    type: 2,
								    title:'按钮管理',
								    skin: 'layui-layer-rim', //加上边框
								    area: ['1000px', '600px'], //宽高
								    maxmin: true, //开启最大化最小化按钮
								    content: 'operation/operationIndex.htm?menuid='+id
								});
							}
		               	},
						position: "last"
					});
				}
	
	
				$("#add_table_menu").click(
					function() {
						var ids = $("#table_menu").jqGrid('getGridParam', 'selrow');
						if (!ids || ids == null) {
							$("#cData").click();
							$("#alertmod_table_menu_mod").show();
						}
					}
				);
	
				$("#alertmod_table_menu_mod_a").click(function() {
					$("#alertmod_table_menu_mod").hide();
				});
			};
			return btnInit;
		};
			
		$("#table_menu").on("click", 'tr[role="row"]', function() {
			$("#alertmod_table_menu_mod").hide();
		})
	</script>
</body>
</html>

