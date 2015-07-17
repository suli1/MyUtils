package com.suli.myutils.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suli.myutils.R;

/**
 * Created by suli on 2015/7/13.
 */
public class TableFragment extends PlaceholderFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.table_row_item, container, false);

        return rootView;
    }
}
