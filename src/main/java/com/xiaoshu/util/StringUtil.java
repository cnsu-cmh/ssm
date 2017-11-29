package com.xiaoshu.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringUtil {
	
	/**
	 * 判断字符串是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串不是空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 判断某一个字符串数组中是否含有某一字符串
	 * @param str
	 * @param strArr
	 * @return
	 */
	public static boolean existStrArr(String str,String []strArr){
		return Arrays.asList(strArr).contains(str);
		/*for(int i=0;i<strArr.length;i++){
			if(strArr[i].equals(str)){
				return true;
			}
		}
		return false;*/
	}
	
	/**
	 * String[]转int[]
	 * @param arrs
	 * @return
	 */
	public static Integer[] stringArrToIntergerArr(String[] arrs){
	    Integer[] ints = new Integer[arrs.length];
	    for(int i=0;i<arrs.length;i++){
	        ints[i] = Integer.parseInt(arrs[i]);
	    }
	    return ints;
	}
	
	/**
	 * String[]转Set<Integer> 并去除重复元素
	 * Integer[] 可有Set<Integer>.toArray()取得
	 * @param arrs
	 * @return
	 */
	public static Set<Integer> stringArrToIntergerSet(String[] arrs){
	    Set<Integer> integerSet = new HashSet<Integer>();
	    for(int i=0;i<arrs.length;i++){
	    	integerSet.add(Integer.parseInt(arrs[i]));
	    }
	    return integerSet;
	}
	
	
}
