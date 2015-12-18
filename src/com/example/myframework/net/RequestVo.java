package com.example.myframework.net;

import java.util.Map;

/**
 * 所有的请求需要的数据都归于这个类中
 * 
 */
public class RequestVo {
	public enum RequestMethod {
		GET, POST
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
	 * 请求方法
	 */
	public RequestMethod method;

	/**
	 * ·
	 * 
	 * @param url
	 *            只是传递url，默认为GET方法
	 */
	public RequestVo(String url) {
		this.url = url;
		this.method = RequestMethod.GET;
	}

	/**
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 */
	public RequestVo(String url, RequestMethod method) {
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param tag
	 *            请求标签
	 */
	public RequestVo(String url, RequestMethod method, String tag) {
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
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数
	 */
	public RequestVo(String url, RequestMethod method,
			Map<String, String> requestParams) {
		this.requestParams = requestParams;
		this.url = url;
		this.method = method;
	}

	/**
	 * 
	 * @param url
	 *            请求地址
	 * @param method
	 *            请求方法
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数
	 * @param tag
	 *            请求标签
	 */
	public RequestVo(String url, RequestMethod method,
			Map<String, String> requestParams, String tag) {
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
	 * @param requestParams
	 *            post的请求参数 null表示不传递参数
	 * @param tag
	 *            请求标签
	 * @param requestHeaders
	 *            请求头 null表示不添加请求头
	 */
	public RequestVo(String url, RequestMethod method,
			Map<String, String> requestParams, String tag,
			Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
		this.requestParams = requestParams;
		this.tag = tag;
		this.url = url;
		this.method = method;
	}
}
