package com.suli.myutils.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suli.myutils.MainActivity;
import com.suli.myutils.R;
import com.suli.myutils.fragment.effects.TestRendererFragment;
import com.suli.myutils.fragment.library.ThirdLibraryFragment;
import com.suli.myutils.fragment.performance.PerformanceFragment;
import com.suli.myutils.fragment.principle.PrincipleMainFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {

        PlaceholderFragment fragment;
        switch (sectionNumber) {
            case 0:
                fragment = new PrincipleMainFragment();
                break;
            case 1:
                fragment = new ThirdLibraryFragment();
                break;
            case 2:
                fragment = new TestRendererFragment();
                break;
            case 3:
                fragment = new PerformanceFragment();
                break;
            default:
                fragment = new PlaceholderFragment();
                break;

        }
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle args = getArguments();
        if (args != null) {
            ((MainActivity) context).onSectionAttached(args.getInt(ARG_SECTION_NUMBER));
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

