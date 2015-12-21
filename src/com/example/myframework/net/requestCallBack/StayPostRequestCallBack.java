package com.example.myframework.net.requestCallBack;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.myframework.javabean.StayPost;
import com.example.myframework.javabean.StayPost.Data;
import com.example.myframework.net.core.RequestCallBack;

public class StayPostRequestCallBack<T> implements RequestCallBack<StayPost> {
	TextView tv;

	public StayPostRequestCallBack(TextView tv) {
		this.tv = tv;
	}

	@Override
	public void onSuccess(StayPost t) {
		Data data = t.getData();
		Log.e("post4Gson", t.getRet() + "***" + data.toString());
		tv.setText(data.toString());
	}

	@Override
	public void onFailure(VolleyError error) {
		// TODO Auto-generated method stub
		Log.e("post4Gson", "请求错误了");
	}

}
