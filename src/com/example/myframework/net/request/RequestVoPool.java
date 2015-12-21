package com.example.myframework.net.request;

import android.widget.TextView;

import com.android.volley.Request.Method;
import com.example.myframework.javabean.StayPost;
import com.example.myframework.javabean.Weather;
import com.example.myframework.net.core.RequestCallBack;
import com.example.myframework.net.core.RequestVo;
import com.example.myframework.net.core.RequestVo.RequestForWhat;
import com.example.myframework.net.requestCallBack.Get4StringRequestCallBack;
import com.example.myframework.net.requestCallBack.StayPostRequestCallBack;

public class RequestVoPool {

	public static RequestVo<StayPost> postStayRequestVo(TextView tv) {

		RequestVo<StayPost> requestVo = new RequestVo<StayPost>(
				RequestUrlUtils.STAY_URL, Method.POST, RequestForWhat.GSON,
				RequestParamsUtils.stayPostRequestParams(),
				RequestTagUtils.POSTSTAY_TAG, StayPost.class,
				RequestHeadersUtils.stayPostRequestHeaders(),
				new StayPostRequestCallBack<StayPost>(tv));
		return requestVo;
	}

	public static RequestVo<String> get4StringRequestVo(TextView tv) {
		RequestVo<String> requestVo = new RequestVo<String>(
				RequestUrlUtils.GET_4_STRING_URL, Method.GET,
				RequestForWhat.STRING,
				RequestParamsUtils.get4StringRequestParams(),
				RequestTagUtils.GET_4_STRING_TAG, String.class,
				RequestHeadersUtils.get4StringRequestHeaders(),
				new Get4StringRequestCallBack<String>(tv));
		return requestVo;
	}
}
