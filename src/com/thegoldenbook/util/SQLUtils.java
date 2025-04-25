package com.thegoldenbook.util;

public class SQLUtils {
	
	public static final String envolverLike (String pattern){
		StringBuilder sb = new StringBuilder();
		sb.append("%").append(pattern.toUpperCase()).append("%");
		return sb.toString();
	}

}
