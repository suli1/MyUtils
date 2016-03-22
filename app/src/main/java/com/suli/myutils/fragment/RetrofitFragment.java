package com.suli.myutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suli.myutils.R;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.log.DebugLog;
import common.net.retrofit.request.LoginRequest;
import common.net.retrofit.MyServer;
import common.utils.PasswordHash;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by suli on 2015/11/12.
 * Retrofit库实例
 */
public class RetrofitFragment extends PlaceholderFragment {

    @Bind(R.id.et_request)
    EditText mEtRequest;

    @Bind(R.id.tv_response)
    TextView mTvResponse;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_net, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
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
            Toast.makeText(getActivity(), "onCompleted", Toast.LENGTH_LONG).show();
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
