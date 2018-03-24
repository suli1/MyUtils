package com.suli.myutils.fragment.principle;

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
public class PrincipleMainFragment extends PlaceholderFragment {

    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_principle, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_launch_mode)
    public void onTestLaunchMode() {
    }


    @OnClick(R.id.btn_view_draw)
    public void onTestViewDraw() {
        startActivity(new Intent(getContext(), ViewDrawActivity.class));
    }
}
