package com.suli.myutils;

import android.app.Application;

import common.net.volley.RequestQueue;
import common.net.volley.toolbox.Volley;

/**
 * Created by Administrator on 2015/4/24.
 */
public class GlobalContext extends Application{

    private static GlobalContext mGlobalContext = null;

    private RequestQueue mRequestQueue;

    public static GlobalContext getInstance() {
        return mGlobalContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalContext = this;

        init();
    }

    private void init() {
       mRequestQueue = Volley.newRequestQueue(this);
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
