package com.example.myframework.application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
	public static Context context;
	/**
	 * 整个app使用一个请求序列：volleyRequestQueue
	 */
	private static RequestQueue requestQueue;

	public static Context getContext() {
		return context;
	}

	public static RequestQueue getRequestQueue() {
		return requestQueue;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		requestQueue = Volley.newRequestQueue(context);
	}
}
