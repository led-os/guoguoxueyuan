package com.test720.grasshoppercollege.alipay;

import android.app.Activity;

public abstract class CallBack {
	Activity activity;
	
	public CallBack(Activity activity) {
		this.activity = activity;
	}
	public abstract void call(PayResult result);
	public abstract void call(int what,Object obj);
	
}
