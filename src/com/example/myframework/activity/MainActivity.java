package com.example.myframework.activity;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.VolleyError;
import com.example.myframework.R;
import com.example.myframework.R.id;
import com.example.myframework.R.layout;
import com.example.myframework.application.MyApplication;
import com.example.myframework.javabean.Stay;
import com.example.myframework.javabean.StayPost;
import com.example.myframework.javabean.StayPost.Data;
import com.example.myframework.net.core.NetUtils;
import com.example.myframework.net.core.RequestCallBack;
import com.example.myframework.net.core.RequestVo;
import com.example.myframework.net.core.RequestVo.RequestForWhat;
import com.example.myframework.net.request.RequestParamsUtils;
import com.example.myframework.net.request.RequestTagUtils;
import com.example.myframework.net.request.RequestUrlUtils;
import com.example.myframework.net.request.RequestVoPool;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		tv.setOnClickListener(this);

	}
@Override
protected void onStop() {
	super.onStop();
	MyApplication.volleyRequestQueue.cancelAll(RequestTagUtils.POSTSTAY_TAG);
	MyApplication.volleyRequestQueue.cancelAll(RequestTagUtils.GET_4_STRING_TAG);
}
	@Override
	public void onClick(View v) {
		get4String();
		// get4Gson();
		// post4String();
		// post4Gson();
	}

	private void post4Gson() {

		NetUtils.execute(RequestVoPool.postStayRequestVo(tv));
	}

	private void get4String() {
		NetUtils.execute(RequestVoPool.get4StringRequestVo(tv));
	}

	private void post4String() {
		HashMap<String, String> requestParams = new HashMap<String, String>();
		requestParams.put("service", "user.login");
		requestParams.put("account", "stay4it");
		requestParams.put("password", "123456");
		NetUtils.execute(new RequestVo<String>(RequestUrlUtils.STAY_URL,
				Method.POST, RequestForWhat.STRING, requestParams,
				"post4String", null, null, new RequestCallBack<String>() {

					@Override
					public void onSuccess(String t) {
						// TODO Auto-generated method stub
						Log.e("post4String", t.toString());
					}

					@Override
					public void onFailure(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("post4String", "请求错误了");
					}
				}));
	}

	private void get4Gson() {
		NetUtils.execute(new RequestVo<Stay>(RequestUrlUtils.STAY_URL,
				Method.GET, RequestForWhat.GSON, null, "get4Gson", Stay.class,
				null, new RequestCallBack<Stay>() {

					@Override
					public void onSuccess(Stay t) {
						// TODO Auto-generated method stub
						com.example.myframework.javabean.Stay.Data data = t
								.getData();
						Log.e("get4Gson", data.toString());

					}

					@Override
					public void onFailure(VolleyError error) {
						// TODO Auto-generated method stub
						Log.e("get4Gson", "请求错误了");
					}
				}));
	}
}
