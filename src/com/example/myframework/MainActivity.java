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
import com.example.myframework.net.NetUtils2;
import com.example.myframework.net.StringCallBack;
import com.example.myframework.net.GsonCallback;
import com.example.myframework.net.NetUtils3;
import com.example.myframework.net.RequestVo;
import com.example.myframework.net.Weather;
import com.example.myframework.net.RequestVo.RequestForWhat;
import com.example.myframework.net.Weather.WeatherInfo;

public class MainActivity extends Activity {

	private RequestVo<Weather> requestVo2;
	private RequestVo<String> requestVo;
	private String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView tv = (TextView) findViewById(R.id.tv);
//		url = "http://api.stay4it.com/v1/public/core/";
		url = "http://www.weather.com.cn/data/sk/101010100.html";

		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				testGsonGet();
//
//				testStringPost();
//				// NetUtils2.volley_Get_json();
//				 NetUtils2.gsonTest();
//
			}

			private void testGsonGet() {
				// get
				requestVo2 = new RequestVo<Weather>(url, Method.GET, RequestForWhat.GSON, null, "111", null, null, new GsonCallback<Weather>() {
					
					@Override
					public void onSuccess(Weather t) {
						// TODO Auto-generated method stub
						WeatherInfo weatherInfo = t.getWeatherinfo();
						Log.e("gsonRequest", "city is " + weatherInfo.getCity());
						Log.e("gsonRequest", "temp is " + weatherInfo.getTemp());
						Log.e("gsonRequest", "time is " + weatherInfo.getTime());
					}

					@Override
					public void onFailure(VolleyError error) {
						// TODO Auto-generated method stub
						error.printStackTrace();
					}
				});
				requestVo2.setClz(Weather.class);
				NetUtils3.execute(requestVo2);
			}

			private void testStringPost() {
				// post
				// 1.准备请求参数
				HashMap<String, String> requestParams = new HashMap<String, String>();
				requestParams.put("service", "user.login");
				requestParams.put("account", "stay4it");
				requestParams.put("password", "123456");
				// 2.创建requestVo
				requestVo = new RequestVo<String>(url, Method.POST,
						RequestForWhat.STRING, requestParams, "1", null,
						new StringCallBack() {

							@Override
							public void onSuccess(String response) {
								// TODO Auto-generated method stub
								Log.e("我在外面",
										(requestVo.getMethod() == Method.GET ? "GET请求："
												: "POST请求：")
												+ response);
							}

							@Override
							public void onFailure(VolleyError error) {
								// TODO Auto-generated method stub
								Log.e("我在外面", "网络请求失败");
							}
						},null);

				// 3.执行execute
				NetUtils3.execute(requestVo);
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
