package com.example.myframework;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;

import com.example.myframework.application.MyApplication;
import com.example.myframework.net.NetUtils;
import com.example.myframework.net.RequestVo;
import com.example.myframework.net.RequestVo.RequestMethod;

public class MainActivity extends Activity {

	private RequestVo requestVo2;
	private RequestVo requestVo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String url = "http://api.stay4it.com/v1/public/core/";

		requestVo2 = new RequestVo(url);
		NetUtils.execute(requestVo2);

		// post
		// 1.准备请求参数
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("service", "user.login");
		requestParams.put("account", "stay4it");
		requestParams.put("password", "123456");
		// 2.创建requestVo
		requestVo = new RequestVo(url, RequestMethod.POST, requestParams, "1",
				null);
		// 3.执行execute
		NetUtils.execute(requestVo);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// 退出时，取消该activity的所有请求
		MyApplication.volleyRequestQueue.cancelAll(requestVo2.tag);
		MyApplication.volleyRequestQueue.cancelAll(requestVo.tag);
	}

}
