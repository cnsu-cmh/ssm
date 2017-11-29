package com.xiaoshu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoshu.config.util.ConfigUtil;
import com.xiaoshu.entity.Attachment;
import com.xiaoshu.service.AttachmentService;
import com.xiaoshu.util.StringUtil;
import com.xiaoshu.util.WriterUtil;
       
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("attachment")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping("attachmentIndex")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "attachment";
	}
	
	@RequestMapping("attachmentList")
	public void list(HttpServletRequest request,HttpServletResponse response,String offset,String limit){
		try {
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<Attachment> pageInfo = attachmentService.findAttachment(pageNum,pageSize);
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",pageInfo.getTotal());
			jsonObj.put("rows", pageInfo.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
