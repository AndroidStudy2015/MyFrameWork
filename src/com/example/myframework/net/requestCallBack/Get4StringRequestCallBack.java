package com.example.myframework.net.requestCallBack;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.myframework.javabean.StayPost;
import com.example.myframework.javabean.StayPost.Data;
import com.example.myframework.javabean.Weather;
import com.example.myframework.javabean.Weather.WeatherInfo;
import com.example.myframework.net.core.RequestCallBack;

public class Get4StringRequestCallBack<T> implements RequestCallBack<String> {
	private TextView tv;

	public Get4StringRequestCallBack(TextView tv) {
		this.tv = tv;
	}

	@Override
	public void onSuccess(String s) {

		Log.e("Get4String", s.toString());
		tv.setText(s.toString());
	}

	@Override
	public void onFailure(VolleyError error) {
		// TODO Auto-generated method stub
		Log.e("Get4String", "请求错误了");
	}

}
