<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>日志主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<link href="${path }/resources/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<div class="panel-body">
		<div id="toolbar" class="btn-group">
			<c:forEach items="${operationList}" var="oper">
				<privilege:operation operationId="${oper.operationid }" id="${oper.operationcode }" name="${oper.operationname }" clazz="${oper.iconcls }"  color="#093F4D"></privilege:operation>
			</c:forEach>
        </div>
        <div class="row">
        
	        <div class="form-horizontal m-t">
			  <div class="col-lg-3">
				<div class="input-group">
			      <span class="input-group-addon">操作人</span>
			      <input type="text" name="username" class="form-control" id="txt_search_username" >
				</div>
			  </div>
			  <div class="col-lg-3">
				<div class="input-group">
			      <span class="input-group-addon">操作类型</span>
			      <input type="text" name="operation" class="form-control" id="txt_search_operation" >
				</div>
			  </div>
			  <div class="col-lg-3">
				<div class="input-group">
			      <span class="input-group-addon">所属模块</span>
			      <input type="text" name="module" class="form-control" id="txt_search_module" >
				</div>
			  </div>
			  <button id="btn_search" type="button" class="btn btn-default">
	          	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
	          </button>
			</div>
			<div class="form-horizontal m-t">			  
			  <div class="form-group col-lg-7">
                 <label class="col-sm-2 control-label">操作时间</label>
                 <div class="col-sm-8">
                     <input placeholder="开始时间" id="txt_search_start" name="start" class="laydate-icon form-control layer-date"/>
                     <input placeholder="结束时间"id="txt_search_end" name="end" class="laydate-icon form-control layer-date"/>
                 </div>
	          </div>
		  	</div>
        </div>
        <table id="table_log"></table>
		
	</div>
	
	<!--删除对话框 -->
	<div class="modal fade" id="modal_log_del" role="dialog" aria-labelledby="modal_log_del" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					 <h4 class="modal-title" id="modal_log_del_head"> 刪除  </h4>
				</div>
				<div class="modal-body">
							删除所选记录？
				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-danger"  id="del_log_btn">刪除</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
			</div>
		</div>
	</div>
	
	<div class="ui-jqdialog modal-content" id="alertmod_table_log_mod"
		dir="ltr" role="dialog"
		aria-labelledby="alerthd_table_log" aria-hidden="true"
		style="width: 200px; height: auto; z-index: 2222; overflow: hidden;top: 274px; left: 534px; display: none;position: absolute;">
		<div class="ui-jqdialog-titlebar modal-header" id="alerthd_table_log"
			style="cursor: move;">
			<span class="ui-jqdialog-title" style="float: left;">注意</span> <a id ="alertmod_table_log_mod_a"
				class="ui-jqdialog-titlebar-close" style="right: 0.3em;"> <span
				class="glyphicon glyphicon-remove-circle"></span></a>
		</div>
		<div class="ui-jqdialog-content modal-body" id="alertcnt_table_log">
			<div id="select_message"></div>
			<span tabindex="0"> <span tabindex="-1" id="jqg_alrt"></span></span>
		</div>
		<div
			class="jqResize ui-resizable-handle ui-resizable-se glyphicon glyphicon-import"></div>
	</div>
	
	<table id="backupLog"></table>
	
	<!-- Peity-->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>
	
	<!-- Bootstrap table-->
	<script src="${path }/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="${path }/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

	<!-- layer javascript -->
    <script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
    
	<!-- 自定义js-->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	
	<!-- layerDate plugin javascript -->
	<script src="${path }/resources/js/plugins/layer/laydate/laydate.js"></script>
	
	<script type="text/javascript">
	
Date.prototype.Format = function (fmt) {
	    var o = {  
	        "M+": this.getMonth() + 1, //月份   
	        "d+": this.getDate(), //日   
	        "H+": this.getHours(), //小时   
	        "m+": this.getMinutes(), //分   
	        "s+": this.getSeconds(), //秒   
	        "S": this.getMilliseconds() //毫秒   
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	};
	
    //外部js调用
    laydate({
        elem: '#txt_search_start', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus', //响应事件。如果没有传入event，则按照默认的click
        format: 'YYYY-MM-DD hh:mm:ss'// 日期格式
    });
    
    laydate({
        elem: '#txt_search_end',
        event: 'focus',
        format: 'YYYY-MM-DD hh:mm:ss'
    });
    
	$(function () {
	    init();
	    $("#btn_search").bind("click",function(){
	    	//先销毁表格  
	        $('#table_log').bootstrapTable('destroy');
	    	init();
	    }); 
	});
	
	var init = function () {
		//1.初始化Table
	    var oTable = new TableInit();
	    oTable.Init();
	    //2.初始化Button的点击事件
	    var oButtonInit = new ButtonInit();
	    oButtonInit.Init();
	};
	
	var TableInit = function () {
	    var oTableInit = new Object();
	    //初始化Table
	    oTableInit.Init = function () {
	        $('#table_log').bootstrapTable({
	            url: 'logList.htm',         //请求后台的URL（*）
	            method: 'post',                      //请求方式（*）
	            contentType : "application/x-www-form-urlencoded",
	            toolbar: '#toolbar',                //工具按钮用哪个容器
	            striped: true,                      //是否显示行间隔色
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	            pagination: true,                   //是否显示分页（*）
	            sortable: true,                     //是否启用排序
	            sortName: "logid",
	            sortOrder: "desc",                   //排序方式
	            queryParams: oTableInit.queryParams,//传递参数（*）
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,                       //初始化加载第一页，默认第一页
	            pageSize: 10,                       //每页的记录行数（*）
	            pageList: [10, 25, 50, 75, 100],    //可供选择的每页的行数（*）
	            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	            strictSearch: true,
	            showColumns: true,                  //是否显示所有的列
	            showRefresh: false,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: true,                //是否启用点击选中行
	           // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "logid",                     //每一行的唯一标识，一般为主键列
	            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                   //是否显示父子表
	            columns: [{
	                checkbox: true
	            },
	            {
	                field: 'logid',
	                title: '日志编号',
	                hidden: true
	            },
	            {
	                field: 'username',
	                title: '操作人',
	                sortable:true
	            }, {
	                field: 'createtime',
	                title: '操作时间',
	                sortable:true,
	                formatter:function(value,row,index){
	                	return new Date(value).Format('yyyy-MM-dd HH:mm:ss');
	                }
	            }, {
	                field: 'operation',
	                title: '操作类型'
	            },
	            {
	            	field: 'ip',
	                title: '地址'
	            	
	            },
	            {
	            	field: 'module',
	                title: '所属模块'
	            	
	            },{
	            	field: 'content',
	                title: '日志内容'
	            }],
	            onClickRow: function (row) {
	            	$("#alertmod_table_log_mod").hide();
	            }
	        });
	    };

	    //得到查询的参数
	    oTableInit.queryParams = function (params) {
	        var temp = {//这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
	            limit: params.limit,   //页面大小
	            offset: params.offset,  //页码
	            username: $("#txt_search_username").val(),
	            operation: $("#txt_search_operation").val(),
	            module: $("#txt_search_module").val(),
	            start: $("#txt_search_start").val(),
	            end: $("#txt_search_end").val(),
	            search:params.search,
	            order: params.order,
	            ordername: params.sort
	        };
	        return temp;
	    };
	    return oTableInit;
	};
	
	var ButtonInit = function () {
	    var oInit = new Object();
	    var postdata = {};

	    oInit.Init = function () {
	        //初始化页面上面的按钮事件
	    	$("#btn_delete").click(function(){
	    		var getSelections = $('#table_log').bootstrapTable('getSelections');
	    		if(getSelections && getSelections.length>0){
	    			$('#modal_log_del').modal({backdrop: 'static', keyboard: false});
	    			$("#modal_log_del").show();
	    		}else{
	    			$("#select_message").text("请选择数据");
	    			$("#alertmod_table_log_mod").show();
	    		}
	        });
	        
	        
	    };

	    return oInit;
	};
	
	$("#alertmod_table_log_mod_a").click(function(){
		$("#alertmod_table_log_mod").hide();
	});
	
	$("#del_log_btn").click(function(){
		var getSelections = $('#table_log').bootstrapTable('getSelections');
		var idArr = new Array();
		var ids;
		getSelections.forEach(function(item){
			idArr.push(item.logid);
		});
		ids = idArr.join(",");
		$.ajax({
		    url:"deleteLog.htm",
		    dataType:"json",
		    data:{"ids":ids},
		    type:"post",
		    success:function(res){
		    	if(res.success){
	    			$('#modal_log_del').modal('hide');
	    			$("#btn_search").click();
	    		}else{
	    			$("#select_message").text(res.errorMsg);
	    			$("#alertmod_table_log_mod").show();
	    		}
		    }
		});
	});
	
	$("#btn_downloadLog4j").click(function(){
		location.href="${path }/log/downloadLog4j.htm";
	});
	
	$("#btn_manualBackup").click(function(){
		layer.confirm('备份之后会将原来的日志删除。确定吗？', {
		    btn: ['备份','取消'], //按钮
		    shade : false, //遮罩,
		    icon : 3
		}, function(index){
			$.post("backup.htm", function(res) {
				if (res.success) {
					layer.close(index);
					$('#table_log').bootstrapTable('destroy');
				    init();
					layer.alert("<a href='javascript:downloadLogBus()'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备份成功点击查看备份</a>", {icon: 1});
				} else {
					
					layer.alert(res.errorMsg, {icon: 2});
				}
			}, "json");
		   
		});
		
	});
	
	$("#btn_downloadLogBus").click(downloadLogBus);
	
	function downloadLogBus(){
		parent.layer.open({
		    type: 2,
		    title:'日志备份记录',
		    skin: 'layui-layer-rim', //加上边框
		    area: ['1000px', '600px'], //宽高
		    maxmin: true, //开启最大化最小化按钮
		    content: 'attachment/attachmentIndex.htm'
		});
	}
	</script>
</body>