package com.example.myframework;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.example.myframework.net.CallBack;
import com.example.myframework.net.NetUtils3;
import com.example.myframework.net.RequestVo;
import com.example.myframework.net.RequestVo.RequestForWhat;

public class MainActivity extends Activity {

	private RequestVo requestVo2;
	private RequestVo requestVo;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.tv);
		url = "http://api.stay4it.com/v1/public/core/";

		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// get
//				requestVo2 = new RequestVo(
//						"http://flash.weather.com.cn/wmaps/xml/china.xml",
//						RequestForWhat.STRING);
//				requestVo2.setCallBack(new CallBack() {
//
//					@Override
//					public void onSuccess(String response) {
//						// TODO Auto-generated method stub
//						Log.e("我在外面",
//								(requestVo.method == Method.GET ? "GET请求："
//										: "POST请求：") + response);
//					}
//
//					@Override
//					public void onFailure(VolleyError error) {
//						// TODO Auto-generated method stub
//						Log.e("我在外面", "网络请求失败");
//					}
//				});
//				NetUtils3.execute(requestVo2);

				// post
				// 1.准备请求参数
				HashMap<String, String> requestParams = new HashMap<String, String>();
				requestParams.put("service", "user.login");
				requestParams.put("account", "stay4it");
				requestParams.put("password", "123456");
				// 2.创建requestVo
				requestVo = new RequestVo(url, Method.POST,
						RequestForWhat.STRING, requestParams, "1", null,
						new CallBack() {

							@Override
							public void onSuccess(String response) {
								// TODO Auto-generated method stub
								Log.e("我在外面",
										(requestVo.method == Method.GET ? "GET请求："
												: "POST请求：")
												+ response);
							}

							@Override
							public void onFailure(VolleyError error) {
								// TODO Auto-generated method stub
								Log.e("我在外面", "网络请求失败");
							}
						});

				// 3.执行execute
				NetUtils3.execute(requestVo);
				// NetUtils2.volley_Get_json();
				// NetUtils2.gsonTest();

			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		// 退出时，取消该activity的所有请求
		// MyApplication.volleyRequestQueue.cancelAll(requestVo2.tag);
		// MyApplication.volleyRequestQueue.cancelAll(requestVo.tag);
	}

}
