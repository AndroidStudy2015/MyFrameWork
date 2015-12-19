package com.example.myframework.net;

import com.android.volley.VolleyError;

public interface GsonCallback {
	 void onSuccess(Object o);
	 void onFailure(VolleyError error);
	 
}
