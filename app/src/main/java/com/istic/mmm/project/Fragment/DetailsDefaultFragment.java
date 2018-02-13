package com.istic.mmm.project.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.istic.mmm.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsDefaultFragment extends Fragment {


    public DetailsDefaultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_default, container, false);
    }

}
