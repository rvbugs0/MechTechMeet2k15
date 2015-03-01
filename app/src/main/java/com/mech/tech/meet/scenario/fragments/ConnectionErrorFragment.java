package com.mech.tech.meet.scenario.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mech.tech.meet.R;

/**
 * Created by bugs-pc on 2/4/2015.
 */

public  class ConnectionErrorFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ConnectionErrorFragment newInstance(int sectionNumber) {
        ConnectionErrorFragment fragment = new ConnectionErrorFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int a = (int) getArguments().get("section_number");
        //Log.i("DATA", "DATA IS" + a);

    }

    public ConnectionErrorFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_connection_error, container, false);
        return rootView;
    }



}