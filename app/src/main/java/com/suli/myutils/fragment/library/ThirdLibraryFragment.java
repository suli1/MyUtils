package com.suli.myutils.fragment.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import com.suli.myutils.R;
import com.suli.myutils.fragment.PlaceholderFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by suli on 16-9-18.
 */
public class ThirdLibraryFragment extends PlaceholderFragment {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_third_library, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.btn_volley)
    public void onVolleyClick() {
        startActivity(new Intent(getContext(), TestVolleyActivity.class));
    }

    @OnClick(R.id.btn_retrofit2)
    public void onRetrofit2Click() {
        startActivity(new Intent(getContext(), TestRetrofitActivity.class));
    }

    @OnClick(R.id.btn_rx_java)
    public void onRxJavaClick() {
        startActivity(new Intent(getContext(), TestRxJavaActivity.class));
    }
}
