package com.example.myframework.net.request;

import java.util.HashMap;

public class RequestHeadersUtils {

	public static HashMap<String, String> stayPostRequestHeaders() {
		 HashMap<String, String> requestParams = new HashMap<String,
		 String>();
//		 requestParams.put("Cache-control", "max-age=1000");
		return requestParams;
	}

	public static HashMap<String, String> get4StringRequestHeaders() {
		return null;
	}
}
