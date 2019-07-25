package com.music.util;

import java.util.regex.Pattern;

public class Tools {
	public static String navigation(String s) {
		boolean f=Pattern.matches("[1-9]{1}[0-9]*", s);
	if(!f) {
		return "1";
	}
		return s;
		
	}
}
