package com.xiaoshu.config.util;

public class ConfigUtil {
	
	private static Integer pageSize;

	public static Integer getPageSize() {
		return pageSize;
	}

	public static void setPageSize(Integer pageSize) {
		ConfigUtil.pageSize = pageSize != null ?pageSize : 10;
	}
	
	

}
