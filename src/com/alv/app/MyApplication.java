package com.alv.app;

import android.app.Application;
import android.content.Context;


//from http://stackoverflow.com/questions/21818905/get-application-context-from-non-activity-singleton-class?rq=1



public class MyApplication extends Application {
	private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
