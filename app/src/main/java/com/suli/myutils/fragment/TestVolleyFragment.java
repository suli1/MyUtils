package com.suli.myutils.fragment;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suli.myutils.GlobalContext;
import com.suli.myutils.R;

import java.util.HashMap;
import java.util.Map;

import common.log.DebugLog;
import common.net.volley.AuthFailureError;
import common.net.volley.Request;
import common.net.volley.Response;
import common.net.volley.VolleyError;
import common.net.volley.toolbox.StringRequest;
import common.utils.SystemUtil;

/**
 * Created by suli on 2015/4/24.
 */
public class TestVolleyFragment extends PlaceholderFragment{

    private TextView mTvRequest;
    private TextView mTvResponse;

    public TestVolleyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_volley, container, false);
        initView(rootView);

        return rootView;
    }

    private void initView(View v) {
       mTvRequest = (TextView)v.findViewById(R.id.request_tv);
       mTvResponse = (TextView)v.findViewById(R.id.response_tv);

        v.findViewById(R.id.request_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequestInfo();
            }
        });

    }

    private void getRequestInfo() {
        String url = "http://10.10.25.232:8989/menusv2/account/login";
        //String url = "http://www.baidu.com";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                DebugLog.d(response);
                mTvResponse.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DebugLog.d(error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //return super.getParams();
                Map<String, String> map = new HashMap<String, String>();
                map.put("str_client_os", "android");
                map.put("str_client_os_version", SystemUtil.getVersion());
                map.put("str_client_model_type", SystemUtil.getModle());
                map.put("str_client_screen_resolution", getScreenResolution());
                map.put("str_client_mac", SystemUtil.getMac());
                map.put("str_client_language", SystemUtil.getLanguage());
                map.put("str_client_app_version", SystemUtil.getVersionName());

                map.put("email", "suli@zime.com.cn");
                map.put("password", "123456");

                DebugLog.d(map.toString());

                return map;
            }
        };

        //DebugLog.d(request.toString());

        GlobalContext.getInstance().getRequestQueue().add(request);
    }

    public String getScreenResolution() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels + "*" + dm.heightPixels;
    }
}
