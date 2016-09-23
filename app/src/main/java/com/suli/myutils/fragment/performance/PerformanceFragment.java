package com.suli.myutils.fragment.performance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suli.myutils.R;
import com.suli.myutils.fragment.PlaceholderFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by suli on 16-9-22.
 * 一些性能相关的测试
 */

public class PerformanceFragment extends PlaceholderFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_performance, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_arn)
    public void onTestAnr() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_memory_leak)
    public void onTestMemoryLeak() {
        startActivity(new Intent(getContext(), NextActivity.class));
    }
}
