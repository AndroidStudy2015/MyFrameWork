package com.example.myframework.net;

import com.android.volley.VolleyError;

public interface StringCallBack {
	void onSuccess(String response);
	void onFailure(VolleyError error);
}
