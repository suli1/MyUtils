package com.suli.myutils;

import android.app.Application;

import com.appsee.Appsee;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import common.net.volley.RequestQueue;
import common.net.volley.toolbox.HurlStack;
import common.net.volley.toolbox.Volley;
import io.fabric.sdk.android.Fabric;

/**
 * Created by suli on 2015/4/24.
 */
public class GlobalContext extends Application {

    private static GlobalContext mGlobalContext = null;

    private RequestQueue mRequestQueue;

    public static GlobalContext getInstance() {
        return mGlobalContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        Fabric.with(this);
        Appsee.start(getString(R.string.com_appsee_apikey));

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
        mRequestQueue = Volley.newRequestQueue(this, new HurlStack());
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
