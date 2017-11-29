package com.xiaoshu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class CodeUtil {
	static Logger logger = Logger.getLogger(CodeUtil.class);

	/**
	 * md5加密
	 * @param plainText：原字符串
	 * @param type：16位/32位加密
	 * @return
	 */
	public static String getMd5(String plainText, int type) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if (type == 32) {
				return buf.toString();
			} else {
				return buf.toString().substring(8, 24);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
}
