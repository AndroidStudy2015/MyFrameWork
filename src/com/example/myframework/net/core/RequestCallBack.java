package com.example.myframework.net.core;

import com.android.volley.VolleyError;

public interface RequestCallBack<T> {
	 void onSuccess(T t);
	 void onFailure(VolleyError error);
	 
}
