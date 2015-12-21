package com.example.myframework.net.request;

import java.util.HashMap;

public class RequestParamsUtils {

	public static HashMap<String, String> stayPostRequestParams() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("service", "user.login");
		requestParams.put("account", "stay4it");
		requestParams.put("password", "123456");
		return requestParams;
	}

	public static HashMap<String, String> get4StringRequestParams() {

		return null;
	}
}
