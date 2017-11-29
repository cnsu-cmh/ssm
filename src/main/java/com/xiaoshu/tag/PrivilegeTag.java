package com.xiaoshu.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.xiaoshu.util.StringUtil;

public class PrivilegeTag extends TagSupport {

	private static final long serialVersionUID = -532517444654109642L;

	private String operationId; // 对应Attribute,加上set方法。
	private String name;      // 按钮名（添加）
	private String clazz;     // 样式
	private String id;        // id名
	private String color;	  //文字颜色
	
	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClazz(String classes) {
		this.clazz = classes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 解析标签，形成原有的a标签
	 * <button id="btn_add" type="button" class="btn btn-default" style="color: #093F4D">
     *    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
     * </button>
	 */
	public int doStartTag() throws JspException {
		String currentOperationIds = (String) pageContext.getSession().getAttribute("currentOperationIds");
		if (StringUtil.isNotEmpty(currentOperationIds) && StringUtil.existStrArr(operationId, currentOperationIds.split(","))) {
			StringBuffer sb = new StringBuffer();
			sb.append("<button type=\"button\" ");
			sb.append("id=\""+id+"\"");
			sb.append("class=\"btn btn-default\" style=\"color:"+color+"\">");
			sb.append("<span class=\""+clazz+"\" aria-hidden=\"true\"></span>"+name);
			sb.append("</button>");
			try {
				pageContext.getOut().write(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return EVAL_PAGE;
		}
		return SKIP_BODY; // 跳过body,body部分不会显示
		/* 设置默认值 */
	}
	
}
