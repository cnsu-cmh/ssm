<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单按钮页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<!-- jqgrid-->
<link href="${path }/resources/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<input type="hidden" id="hide_menuid" name = "menuid" value="${menuid }"/>
	<input type="hidden" id="hide_menuname" name = "menuname" value="${menuname }"/>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_menu_operation"></table>
							<div id="pager_menu_operation"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Peity -->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- jqGrid -->
	<script src="${path }/resources/js/plugins/jqgrid/i18n/grid.locale-cn.js?082"></script>
	<script src="${path }/resources/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
	
	<!-- layer javascript -->
    <script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
    
	<!-- 自定义js -->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	
	<script type="text/javascript">
	
	$(function() {
		mo_init();
	});		
		
	var mo_init = function(){
		var mo_table = new mo_TableInit();
	    mo_table.Init();
	    var mo_btn = new mo_ButtonInit();
	    mo_btn.Init();
	};
	
	var mo_TableInit = function () {
		var mo_oTable = new Object();
		mo_oTable.Init = function () {
			$.jgrid.defaults.styleUI = 'Bootstrap';
			$("#table_menu_operation").jqGrid({
				url : 'operationList.htm',
				postData : {
					'menuid' : $("#hide_menuid").val()
				}, //发送数据  
				datatype : "json",
				height : "300",
				autowidth : true,
				colNames : [ '按钮编号', '按钮名称', '按钮ID', '按钮图标', '按钮样式', '菜单ID', '所属菜单' ],
				colModel : [ {
					name : "operationid",
					index : "operationid",
					hidden : true,
					editable : true,
					sortable: false,
					key : true
				}, {
					name : "operationname",
					index : "operationname",
					editable : true,
					editrules : {
						required : true
					},
					sortable: false
				},
				{
					name : "operationcode",
					index : "operationcode",
					editable : true,
					editrules : {
						required : true
					},
					sortable: false
				},
				{
					name : "",
					editable : false,
					formatter : function(cellvalue, options, rowObject){
						return '<span class="'+rowObject.iconcls+'" aria-hidden="true"></span>';
					}
				},
				{
					name : "iconcls",
					index : "iconcls",
					editable : true,
					sortable: false
				},
				{
					name : "menuid",
					index : "menuid",
					hidden : true,
					editable : true,
					sortable: false
				},
				{
					name : "menuname",
					index : "menuname",
					editable : true,
					sortable: false
				} ],
				pager : "#pager_menu_operation",
				viewrecords : true,
				rowNum : "5",
				rowList : ["5", "10", "15" ],
				sortname : "operationid",
				sortorder : "asc",
				editurl : 'reserveOperation.htm'
			});
			
		};
			
		return mo_oTable;
	};

					
	var mo_ButtonInit = function () {
    	var mo_btnInit = new Object();
    	mo_btnInit.Init = function(){
	    	// Setup buttons
			var mo_jqnav = $("#table_menu_operation").jqGrid( 'navGrid', '#pager_menu_operation', {
				add : true,
				edit : true,
				del : true,
				search : false,
				refresh : false,
				addtext : "添加",
				edittext : "修改",
				deltext : "删除"
			},
			{//edit
				height : 300,
				reloadAfterSubmit : true,
			    beforeShowForm : function(frm) {
			    	var id = $("#table_menu_operation").jqGrid('getGridParam', 'selrow');
			    	frm.find('#operationid').val(id);
			    	frm.find('#menuid').val(menuid.value);
			    	frm.find('#operationname').val(operationname.value);
			    	frm.find('#menuname').val(menuname.value).attr('readonly',true);
				},
				afterSubmit: function(xhr, postdata) {
	             		var obj = eval('(' + xhr.responseText + ')');
	             		if(obj.errorMsg === undefined){
	             			/* $("#table_menu_operation").trigger("reloadGrid"); */
	             			$("#tokeInOutList").jqGrid('resetSelection');
	               		return true;
	             		}else{
	             			layer.alert(obj.errorMsg, {icon: 2});
	             			return false;
	             		}
				},
				closeAfterEdit : true
			},
			{//add
				height : 300,
				reloadAfterSubmit : true,
			    beforeShowForm : function(frm) {
			    	frm.find('#menuid').val($("#hide_menuid").val());
			    	frm.find('#menuname').val($("#hide_menuname").val()).attr('readonly',true);
				},
				afterSubmit: function(xhr, postdata) {
	             		var obj = eval('(' + xhr.responseText + ')');
	             		if(obj.errorMsg === undefined){
	             			$("#tokeInOutList").jqGrid('resetSelection');
	             			/* $("#table_menu_operation").trigger("reloadGrid"); */
	               		return true;
	             		}else{
	             			layer.alert(obj.errorMsg, {icon: 2});
	             			return false;
	             		}
				},
				closeAfterAdd : true
	            },
	            {//del
	             	url : 'deleteOperation.htm',
	             	afterSubmit: function(xhr, postdata) {
	             		var obj = eval('(' + xhr.responseText + ')');
	             		if(obj.errorMsg){
	             			layer.alert(obj.errorMsg, {icon: 2});
	             			$("#delmodtable_menu_operation").hide();
	             			return false;
	             		}else{
	             			/* $("#table_menu_operation").trigger("reloadGrid"); */
	             			$("#tokeInOutList").jqGrid('resetSelection');
						$("#delmodtable_menu_operation").hide();
	               		return true;
	             		}
					}
	             },
	             {}
	        );
     	};
		return mo_btnInit;
	};
	</script>
</body>