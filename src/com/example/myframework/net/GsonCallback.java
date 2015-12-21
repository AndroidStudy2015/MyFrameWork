package com.example.myframework.net;

import com.android.volley.VolleyError;

public interface GsonCallback<T> {
	 void onSuccess(T t);
	 void onFailure(VolleyError error);
	 
}
