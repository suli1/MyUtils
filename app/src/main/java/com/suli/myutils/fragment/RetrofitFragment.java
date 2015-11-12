package com.suli.myutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.suli.myutils.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }


}
