package com.example.myframework.net.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
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
import com.example.myframework.application.MyApp;
import com.example.myframework.utils.LogUtils;

public class NetUtils {
	/**
	 * 所有网络请求都只有这一个方法，入口
	 * 
	 * @param <T>
	 * 
	 * @param requestVo
	 */
	public static <T> void execute(final RequestVo<T> requestVo) {
		switch (requestVo.getRequestForWhat()) {
		case STRING:
			STRINGRequest((RequestVo<String>) requestVo);
			break;

		case GSON:
			GSONRequest(requestVo);
			break;
		}
	}

	/**
	 * 请求获得String
	 * 
	 * @param requestVo
	 *            配置请求的参数
	 */
	private static void STRINGRequest(final RequestVo<String> requestVo) {
		StringRequest request = new StringRequest(requestVo.getMethod(),
				requestVo.getUrl(), new Listener<String>() {

					@Override
					public void onResponse(String response) {
						if (requestVo.getCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack
							Log.e("StringRequest",
									(requestVo.getMethod() == Method.GET ? "GET请求："
											: "POST请求：")
											+ response);
							requestVo.getCallBack().onSuccess(response);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (requestVo.getCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack
							Log.e("StringRequest",
									(requestVo.getMethod() == Method.GET ? "GET请求："
											: "POST请求：")
											+ "请求失败");
							requestVo.getCallBack().onFailure(error);
						}
					}
				}) {
			/**
			 * 请求体添加post请求参数，★如果指定get或者post方法，默认为get方法，则此方法不执行★
			 */
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

		if (requestVo.getRequestTag() != null
				&& !("".equals(requestVo.getRequestTag()))) {
			request.setTag(requestVo.getRequestTag());
		}

		MyApp.getRequestQueue().add(request);
	}

	// *******************************************************************************************************
	/**
	 * 发送请求得到json对应的javabean对象
	 * 
	 * @param requestVo
	 *            配置请求的参数
	 */
	private static <T> void GSONRequest(final RequestVo<T> requestVo) {
		if (requestVo.getClzz() == null) {
			Log.e("GsonRequest",
					"注意★：请确定你要转换成的javabean类型，requestVo.getClzz()不能为空");
			return;

		}
		GsonRequest<T> request = new GsonRequest<T>(requestVo.getMethod(),
				requestVo.getUrl(), requestVo.getClzz(), new Listener<T>() {

					@Override
					public void onResponse(T t) {
						if (requestVo.getCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack

							Log.e("GsonRequest",
									(requestVo.getMethod() == Method.GET ? "GET请求："
											: "POST请求：")
											+ t.toString());
							requestVo.getCallBack().onSuccess(t);
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						if (requestVo.getCallBack() != null) {// ★注意这里非空判断，如果不考虑请求的结果，可以不初始化setCallBack

							Log.e("GsonRequest",
									(requestVo.getMethod() == Method.GET ? "GET请求："
											: "POST请求：")
											+ "请求失败");
							requestVo.getCallBack().onFailure(error);
						}
					}
				}) {
			/**
			 * 请求体添加post请求参数，★如果指定get或者post方法，默认为get方法，则此方法不执行★<br>
			 * 如果上边的 new GsonRequest<T>()里面，不加入★requestVo.getMethod()★这个参数，本方法（
			 * 即getParams）就不会执行
			 */
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

		if (requestVo.getRequestTag() != null
				&& !("".equals(requestVo.getRequestTag()))) {
			request.setTag(requestVo.getRequestTag());
		}
		MyApp.getRequestQueue().add(request);

	}

	/**
	 * wifi是否连接
	 * 
	 * @param context
	 * @return wifi是否连接
	 */
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 是否能上网
	 * 
	 * @param context
	 * @return 是否能上网
	 */
	public static boolean hasConnectedNetwork(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService("connectivity");
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 是否是wifi网络
	 * 
	 * @param mContext
	 *            上下文
	 * @return 是否是wifi网络
	 */
	public static boolean isWifi(Context mContext) {
		ConnectivityManager connectivityManager = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 写入本地字符串进文件
	 * 
	 * @param path
	 *            路径
	 * @param content
	 *            内容
	 * @throws IOException
	 */
	public static void wirteJsonToLocal(String path, String content)
			throws IOException {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
		FileOutputStream out = null;
		try {
			LogUtils.d("path=" + path);
			out = new FileOutputStream(file);
			byte[] bytes = content.getBytes("UTF-8");
			out.write(bytes);
		} catch (Exception e) {
			LogUtils.d(e.toString());
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
