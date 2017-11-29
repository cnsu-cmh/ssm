package com.xiaoshu.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiaoshu.entity.Menu;
import com.xiaoshu.entity.Operation;
import com.xiaoshu.service.OperationService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("operation")
public class OperationController {
	static Logger logger = Logger.getLogger(OperationController.class);
	
	@Autowired
	private OperationService operationService;

	@RequestMapping("operationIndex")
	public String index(HttpServletRequest request,HttpServletResponse response,@RequestParam("menuid") String menuid){
		
		if(StringUtil.isNotEmpty(menuid)){
			Menu menu = operationService.getMenuByMenuid(Integer.parseInt(menuid));
			request.setAttribute("menuid",menuid);
			request.setAttribute("menuname",menu.getMenuname());
		}
		
		return "operation";
	}
	
	@RequestMapping("operationList")
	public void list(HttpServletRequest request,HttpServletResponse response,Integer menuid,Integer page,Integer rows){
		try {
			Operation operation = new Operation();
			operation.setMenuid(menuid);
			PageInfo<Operation> pageinfo = operationService.findOperationPage(operation,page,rows);
			JSONObject jsonObj = new JSONObject();//new一个JSON
			jsonObj.put("total",pageinfo.getPages());
			jsonObj.put("records", pageinfo.getTotal());
			jsonObj.put("rows", pageinfo.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("按钮展示错误",e);
		}
		
	}
	
	@RequestMapping("reserveOperation")
	public void reserveMenu(HttpServletRequest request,HttpServletResponse response,Operation operation){
		Integer operationid = operation.getOperationid();
		JSONObject result=new JSONObject();
		try {
			if (operationid != null) {  //更新操作
				operation.setMenuid(operationid);
				operationService.updateOperation(operation);
			} else {
				operationService.addOperation(operation);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("按钮保存错误",e);
			result.put("success", true);
			result.put("errorMsg", "对不起，操作失败！");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
	

	@RequestMapping("deleteOperation")
	public void delUser(HttpServletRequest request,HttpServletResponse response,Integer id){
		JSONObject result=new JSONObject();
		try {
			operationService.deleteOperation(id);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除按钮错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	
}
