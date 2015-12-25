package com.example.myframework.activity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.VolleyError;
import com.example.myframework.R;
import com.example.myframework.R.id;
import com.example.myframework.R.layout;
import com.example.myframework.application.MyApp;
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
import com.example.myframework.utils.AppInfoUtil;

public class MainActivity extends Activity implements OnClickListener {

	private TextView tv;
	private TextView tv1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		tv.setOnClickListener(this);
		tv1 = (TextView) findViewById(R.id.tv1);
		tv1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"a.txt";
				tv.setText(AppInfoUtil.getPkName(MyApp.context)+"_"+AppInfoUtil.getPhoneModle(MyApp.context)+path);
				AppInfoUtil.stratNotification(MyApp.context, "标题", "圣诞节快乐！！！",
						R.drawable.ic_launcher,
						AppInfoUtil.getPkName(MyApp.context), "MainActivity");
				try {
					NetUtils.wirteJsonToLocal(path, "圣诞节快乐！");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	protected void onStop() {
		super.onStop();
		// 当关闭activity时，取消相关请求
		MyApp.getRequestQueue().cancelAll(RequestTagUtils.POSTSTAY_TAG);
		MyApp.getRequestQueue().cancelAll(RequestTagUtils.GET_4_STRING_TAG);
	}

	@Override
	public void onClick(View v) {
		get4String();
		get4Gson();
		post4String();
		post4Gson();
	}

	/**
	 * POST请求Gson的例子
	 */
	private void post4Gson() {

		NetUtils.execute(RequestVoPool.postStayRequestVo(tv));
	}

	/**
	 * GET请求String的例子
	 */
	private void get4String() {
		NetUtils.execute(RequestVoPool.get4StringRequestVo(tv));
	}

	/**
	 * POST请求String的例子
	 */
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

	/**
	 * PGET请求Gson的例子
	 */
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
