package com.thegoldenbook.util;

public class SQLUtils {
	
	public static final String wrapForLike (String pattern){
		StringBuilder sb = new StringBuilder();
		sb.append("%").append(pattern.toUpperCase()).append("%");
		return sb.toString();
	}

}
