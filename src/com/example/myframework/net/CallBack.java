package com.example.myframework.net;

import com.android.volley.VolleyError;

public interface CallBack {
	void onSuccess(String response);
	void onFailure(VolleyError error);
}
