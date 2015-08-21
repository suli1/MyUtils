package com.suli.myutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import common.opengl.MyRenderer;

/**
 * Created by suli on 2015/8/6.
 */
public class TestRendererFragment extends PlaceholderFragment {

    private MyRenderer glSurface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        glSurface = new MyRenderer(getActivity());
        return glSurface;
    }

    @Override
    public void onResume() {
        super.onResume();
        glSurface.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        glSurface.onPause();
    }
}
