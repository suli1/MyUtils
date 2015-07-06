package com.suli.myutils;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

import common.net.volley.RequestQueue;
import common.net.volley.toolbox.HurlStack;
import common.net.volley.toolbox.OkHttpStack;
import common.net.volley.toolbox.Volley;

/**
 * Created by suli on 2015/4/24.
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

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());

        init();
    }

    private void init() {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());
        //mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack(client));
        mRequestQueue = Volley.newRequestQueue(this, new HurlStack());
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
