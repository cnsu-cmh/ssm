package com.xiaoshu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TimeUtil {
	
	/**
	 * 将日期格式化成String类型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatTime(Date date,String pattern){
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 将日期格式化成String类型
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException 
	 */
	public static Date ParseTime(String date,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).parse(date);
	}
	
}
