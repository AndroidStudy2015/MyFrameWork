package com.example.myframework.net;

import java.io.IOException;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.myframework.application.MyApplication;
import com.example.myframework.net.Weather.WeatherInfo;

public class NetUtils2 {
public static void xmlTest(){
		XMLRequest xmlRequest = new XMLRequest(
				"http://flash.weather.com.cn/wmaps/xml/china.xml",
				new Response.Listener<XmlPullParser>() {
					@Override
					public void onResponse(XmlPullParser response) {
						try {
							int eventType = response.getEventType();
							while (eventType != XmlPullParser.END_DOCUMENT) {
								switch (eventType) {
								case XmlPullParser.START_TAG:
									String nodeName = response.getName();
									if ("city".equals(nodeName)) {
										String pName = response
												.getAttributeValue(0);
										Log.e("TAG", "pName is " + pName);
									}
									break;
								}
								eventType = response.next();
							}
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		MyApplication.volleyRequestQueue.add(xmlRequest);
}
	public static void gsonTest() {
		GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>(
				"http://www.weather.com.cn/data/sk/101010100.html",
				Weather.class, new Listener<Weather>() {

					@Override
					public void onResponse(Weather weather) {
						// TODO Auto-generated method stub
						WeatherInfo weatherInfo = weather.getWeatherinfo();
						Log.e("gsonRequest", "city is " + weatherInfo.getCity());
						Log.e("gsonRequest", "temp is " + weatherInfo.getTemp());
						Log.e("gsonRequest", "time is " + weatherInfo.getTime());

					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						// TODO Auto-generated method stub
						Log.e("ccccccccc", "错了");
					}
				});
		MyApplication.volleyRequestQueue.add(gsonRequest);
	}

	public static void volley_Get_json() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET,
				"http://api.stay4it.com/v1/public/core/", null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("TAG", response.toString());
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("TAG", error.getMessage(), error);
					}
				});
		MyApplication.volleyRequestQueue.add(jsonObjectRequest);
	}
}
