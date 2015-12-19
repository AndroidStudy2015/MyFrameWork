package com.example.myframework.net;

import java.util.Map;

import com.android.volley.Request.Method;

/**
 * 所有的请求需要的数据都归于这个类中
 * 
 */
public class RequestVo {

	public enum RequestForWhat {
		STRING, GSON, XML, JSON
	}

	/**
	 * 请求地址url
	 */
	public String url;
	/**
	 * 某个网络请求的tag，用于标记该请求，当想要取消该请求是使用
	 */
	public String tag;

	/**
	 * 需要增加的请求参数，多个请求参数存在map集合里
	 */
	public Map<String, String> requestParams;

	/**
	 * 需要增加的请求头，多个请求头存在map集合里
	 */
	public Map<String, String> requestHeaders;
	/**
	 * 请求方法(注意是volley包下的Method枚举值)<br>
	 * Method.GET/Method.POST
	 */
	public int method;
	/**
	 * 请求想得到什么类型的数据：STRING, GSON, XML, JSON
	 */
	public RequestForWhat requestForWhat;
	private CallBack callBack;

	public CallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	/**
	 * ·
	 * 
	 * @param url
	 *            只是传递url，默认为GET方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 */
	public RequestVo(String url, RequestForWhat requestForWhat) {
		this.url = url;
		this.requestForWhat = requestForWhat;
		this.method = Method.GET;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat) {
		this.url = url;
		this.requestForWhat = requestForWhat;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 * @param tag
	 *            请求标签
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat,
			String tag) {
		this.tag = tag;
		this.requestForWhat = requestForWhat;
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 * @param requestParams
	 *           post的请求参数 null表示不传递参数（GET请求，此处写null）
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat,
			Map<String, String> requestParams) {
		this.requestParams = requestParams;
		this.requestForWhat = requestForWhat;
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数（GET请求，此处写null）
	 * @param tag
	 *            请求标签
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat,
			Map<String, String> requestParams, String tag) {
		this.requestForWhat = requestForWhat;
		this.requestParams = requestParams;
		this.tag = tag;
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数（GET请求，此处写null）
	 * @param tag
	 *            请求标签
	 * @param requestHeaders
	 *            请求头 null表示不添加请求头
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat,
			Map<String, String> requestParams, String tag,
			Map<String, String> requestHeaders) {
		this.requestForWhat = requestForWhat;
		this.requestHeaders = requestHeaders;
		this.requestParams = requestParams;
		this.tag = tag;
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param RequestForWhat
	 *            请求的目的是获取什么类型的数据（STRING, GSON, XML, JSON）
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数（GET请求，此处写null）
	 * @param tag
	 *            请求标签
	 * @param requestHeaders
	 *            请求头 null表示不添加请求头
	 * @param callBack
	 *            请求后的回调，在这里处理请求回来的结果或异常.null表示不处理请求结果
	 */
	public RequestVo(String url, int method, RequestForWhat requestForWhat,
			Map<String, String> requestParams, String tag,
			Map<String, String> requestHeaders, CallBack callBack) {
		this.requestForWhat = requestForWhat;
		this.requestHeaders = requestHeaders;
		this.requestParams = requestParams;
		this.tag = tag;
		this.url = url;
		this.method = method;
		this.callBack=callBack;
	}
}
