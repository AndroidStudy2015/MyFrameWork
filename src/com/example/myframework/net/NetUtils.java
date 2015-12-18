package com.example.myframework.net;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myframework.application.MyApplication;

public class NetUtils {
	/**
	 * 所有网络请求都只有这一个方法，入口
	 * 
	 * @param requestVo
	 */
	public static void execute(final RequestVo requestVo) {
		switch (requestVo.method) {
		case GET:
			volley_Get(requestVo);
			break;

		case POST:
			volley_Post(requestVo);
			break;
		}

	}

	private static void volley_Get(final RequestVo requestVo) {
		StringRequest request = new StringRequest(Method.GET, requestVo.url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.e("tag", "GET" + response);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("tag", "网络请求失败");
					}
				});

		request.setRetryPolicy(new DefaultRetryPolicy(
				DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,// 设置超时时间（默认是2.5秒）
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		if (requestVo.tag != null && !("".equals(requestVo.tag))) {
			request.setTag(requestVo.tag);
		}
		MyApplication.volleyRequestQueue.add(request);
	}

	private static void volley_Post(final RequestVo requestVo) {

		StringRequest request = new StringRequest(Method.POST, requestVo.url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.e("tag", "POST" + response);

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("tag", "网络请求失败");
					}
				}) {
			// 请求体添加post请求参数
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				if (requestVo.requestParams == null
						|| requestVo.requestParams.size() == 0) {
					return null;
				}
				return requestVo.requestParams;
			}

			// ★设置请求头(没添加请求头时，返回null报错，不知为什么，先注释掉+++++
			// 明白了，当你重写了此方法，就把之前默认设置的header给覆盖了，
			// 而你又返回一个null的map，所以报错，解决方法是如果你没给headers，
			// 就返回super.getHeaders())
			@Override
			public Map getHeaders() throws AuthFailureError {
				if (requestVo.requestHeaders == null
						|| requestVo.requestHeaders.size() == 0) {
					return super.getHeaders();//★你没设置，就返回父类设置好的
				}
				return requestVo.requestHeaders;
			}

			// 设置超时时间（默认是2.5秒）、重试次数（一般为1次，表示不让重试）、回避指数（这个参数不懂）
			@Override
			public RetryPolicy getRetryPolicy() {
				RetryPolicy retryPolicy = new DefaultRetryPolicy(2500,
						DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
						DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
				return retryPolicy;
			}
		};
		if (requestVo.tag != null && !("".equals(requestVo.tag))) {
			request.setTag(requestVo.tag);
		}
		MyApplication.volleyRequestQueue.add(request);
	}
}
