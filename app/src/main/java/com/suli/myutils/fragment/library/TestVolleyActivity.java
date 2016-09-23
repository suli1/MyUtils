package com.suli.myutils.fragment.library;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.common.Contract;
import com.suli.myutils.Constants;
import com.suli.myutils.GlobalContext;
import com.suli.myutils.R;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

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
public class TestVolleyActivity extends Activity {

    private EditText mEtRequest;
    private TextView mTvResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_network);
        initView(getWindow().getDecorView());
    }

    private void initView(View v) {
        mEtRequest = (EditText) v.findViewById(R.id.et_request);
        mTvResponse = (TextView) v.findViewById(R.id.tv_response);

        assertThat(mEtRequest).isEmpty();

        mEtRequest.setText(Constants.BASE_URL);

        v.findViewById(R.id.btn_send_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequestInfo();
            }
        });

    }

    private void getRequestInfo() {
        String url = Contract.BASE_URL + "menusv3/v3/account/login";
        List<FormText> listItem = new ArrayList<>();
        addCommonParam(listItem);


        listItem.add(new FormText("account_phone", "18664519382"));

        String strRequest = JSONObject.toJSONString(listItem);
        mEtRequest.setText(strRequest);

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
                mTvResponse.setText(error.getMessage());
            }
        });

        //DebugLog.d(request.toString());

        GlobalContext.getInstance().getRequestQueue().add(request);
    }

    private void addCommonParam(List<FormText> list) {
        list.add(new FormText("str_client_os", "android"));
        list.add(new FormText("str_client_os_version", SystemUtil.getVersion()));
        list.add(new FormText("str_client_model_type", SystemUtil.getModel()));
        list.add(new FormText("str_client_screen_resolution", SystemUtil.getScreenResolution()));
        list.add(new FormText("str_client_mac", SystemUtil.getMac()));
        list.add(new FormText("str_client_language", SystemUtil.getLanguage()));
        list.add(new FormText("str_client_app_version", SystemUtil.getVersionName()));
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
