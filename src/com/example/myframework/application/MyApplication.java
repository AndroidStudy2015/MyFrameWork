package com.example.myframework.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
	public static Context context;
	/**
	 * 整个app使用一个请求序列：volleyRequestQueue
	 */
	public static RequestQueue volleyRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		volleyRequestQueue = Volley.newRequestQueue(context);
	}
}
