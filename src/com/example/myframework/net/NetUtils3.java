package com.example.myframework.net;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myframework.application.MyApplication;
import com.example.myframework.net.Weather.WeatherInfo;

public class NetUtils3 {
	/**
	 * 所有网络请求都只有这一个方法，入口
	 * 
	 * @param requestVo
	 */
	public static void execute(final RequestVo requestVo) {
		switch (requestVo.getRequestForWhat()) {
		case STRING:
			STRINGRequest(requestVo);
			break;

		case GSON:
			GSONRequest(requestVo);
			break;

		case XML:
		case JSON:
			break;
		}

	}

	private static void STRINGRequest(final RequestVo requestVo) {

		StringRequest request = new StringRequest(requestVo.getMethod(),
				requestVo.getUrl(), new Listener<String>() {

					@Override
					public void onResponse(String response) {
						if (requestVo.getStringCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack
							Log.e("STRINGRequest",
									(requestVo.getMethod() == Method.GET ? "GET请求："
											: "POST请求：")
											+ response);
							requestVo.getStringCallBack().onSuccess(response);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (requestVo.getStringCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack
							requestVo.getStringCallBack().onFailure(error);
						}
					}
				}) {
			// 请求体添加post请求参数
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				if (requestVo.getRequestParams() == null
						|| requestVo.getRequestParams().size() == 0) {
					return null;
				}
				return requestVo.getRequestParams();
			}

			// ★设置请求头(没添加请求头时，返回null报错，不知为什么，先注释掉+++++
			// 明白了，当你重写了此方法，就把之前默认设置的header给覆盖了，
			// 而你又返回一个null的map，所以报错，解决方法是如果你没给headers，
			// 就返回super.getHeaders())
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (requestVo.getRequestHeaders() == null
						|| requestVo.getRequestHeaders().size() == 0) {
					return super.getHeaders();// ★你没设置，就返回父类设置好的
				}
				return requestVo.getRequestHeaders();
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
		if (requestVo.getTag() != null && !("".equals(requestVo.getTag()))) {
			request.setTag(requestVo.getTag());
		}
		MyApplication.volleyRequestQueue.add(request);
	}

	// *******************************************************************************************************
	private static <T> void GSONRequest(final RequestVo requestVo) {
//		Type type = ((ParameterizedType) getClass().getGenericSuperclass())
//				.getActualTypeArguments()[0];

		GsonRequest<Object> gsonRequest = new GsonRequest<Object>(
				requestVo.getUrl(), requestVo.getClz(), new Listener<Object>() {

					@Override
					public void onResponse(Object o) {
						// TODO Auto-generated method stub
						Log.e("GsonRequest",
								(requestVo.getMethod() == Method.GET ? "GET请求："
										: "POST请求：") + o.toString());
						 requestVo.getGsonCallback().onSuccess(o);
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("ccccccccc", "错了");
						requestVo.getGsonCallback().onFailure(error);
					}
				});
		MyApplication.volleyRequestQueue.add(gsonRequest);

	}

}
