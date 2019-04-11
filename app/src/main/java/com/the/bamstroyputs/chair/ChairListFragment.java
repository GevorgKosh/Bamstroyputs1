package com.the.bamstroyputs.chair;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChairListFragment extends Fragment {


    public ChairListFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_chair_list, container, false);
    }

}
