package com.suli.myutils.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.suli.myutils.GlobalContext;
import com.suli.myutils.R;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import common.log.DebugLog;
import common.net.volley.Response;
import common.net.volley.VolleyError;
import common.net.volley.toolbox.FormText;
import common.net.volley.toolbox.PostFormRequest;
import common.utils.PasswordHash;
import common.utils.SystemUtil;

import static org.assertj.android.api.Assertions.assertThat;


/**
 * Created by suli on 2015/4/24.
 */
public class TestVolleyFragment extends PlaceholderFragment {

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
        mTvRequest = (TextView) v.findViewById(R.id.request_tv);
        mTvResponse = (TextView) v.findViewById(R.id.response_tv);

        assertThat(mTvRequest).isEmpty();

        v.findViewById(R.id.request_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequestInfo();
            }
        });

    }

    private void getRequestInfo() {
        String url = "http://10.10.25.232:8989/menusv3/v3/account/login";
        //String url = "http://www.baidu.com";
        List<FormText> listItem = new ArrayList<>();
        listItem = null;
        addCommonParam(listItem);


        listItem.add(new FormText("account_phone", "18664519382"));

        String strRequest = JSONObject.toJSONString(listItem);
        mTvRequest.setText(strRequest);

        try {
            listItem.add(new FormText("password", PasswordHash.createHash("qwerty")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        PostFormRequest request = new PostFormRequest(url, listItem, String.class, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTvResponse.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                DebugLog.d(error.toString());
            }
        });

        //DebugLog.d(request.toString());

        GlobalContext.getInstance().getRequestQueue().add(request);
    }

    private void addCommonParam(List<FormText> list) {
        list.add(new FormText("str_client_os", "android"));
        list.add(new FormText("str_client_os_version", SystemUtil.getVersion()));
        list.add(new FormText("str_client_model_type", SystemUtil.getModle()));
        list.add(new FormText("str_client_screen_resolution", getScreenResolution()));
        list.add(new FormText("str_client_mac", SystemUtil.getMac()));
        list.add(new FormText("str_client_language", SystemUtil.getLanguage()));
        list.add(new FormText("str_client_app_version", SystemUtil.getVersionName()));
    }


    public String getScreenResolution() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.widthPixels + "*" + dm.heightPixels;
    }

    private static class BaseResponse {
        public int resultCode;
        public String errorMsg;

        @Override
        public String toString() {
            return "{" +
                    "resultCode=" + resultCode +
                    ", errorMsg='" + errorMsg + '\'' +
                    '}';
        }
    }
}
