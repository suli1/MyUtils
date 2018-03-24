package com.suli.myutils.fragment.library;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.Unbinder;
import com.suli.myutils.R;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import common.log.DebugLog;
import common.net.retrofit.MyServer;
import common.net.retrofit.request.LoginRequest;
import common.utils.PasswordHash;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by suli on 2015/11/12.
 * Retrofit库实例
 */
public class TestRetrofitActivity extends Activity {

    private Unbinder unbinder;

    @BindView(R.id.et_request)
    EditText mEtRequest;

    @BindView(R.id.tv_response)
    TextView mTvResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_network);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @OnClick(R.id.btn_send_request)
    public void onSendRequest() {
        String account = "18664519382";
        String password = "qwerty";

        try {
            password = PasswordHash.createHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.account_phone = account;
        loginRequest.password = password;

        MyServer.getAccountAPI().login(loginRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new LoginSubscribe());

    }

    private class LoginSubscribe extends Subscriber<String> {

        @Override
        public void onCompleted() {
            DebugLog.d("onComplete");
            Toast.makeText(TestRetrofitActivity.this, "onCompleted", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(Throwable e) {
            DebugLog.e("onError");
            mTvResponse.setText(e.getMessage());
        }

        @Override
        public void onNext(String s) {
            DebugLog.d("onNext:" + s);
            mTvResponse.setText(s);
        }
    }

}
