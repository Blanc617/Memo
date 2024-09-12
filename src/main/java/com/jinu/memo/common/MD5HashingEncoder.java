package com.jinu.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {
	
	
	public static String edcode(String message) {
		
		String result = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");

			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			// byte 배열의 값을 16진수 문자열 형태로 변환
			for(int i = 0; i < digest.length; i++) {
				// 01010111
				// 11011101
				
				// 01010101 둘 다 1이면 1
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			// return null;
		}
		
		return result;
	}


}