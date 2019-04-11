package com.the.bamstroyputs.vendor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class VendorFragment extends Fragment {

    public static VendorFragment newInstance() {

        Bundle args = new Bundle();

        VendorFragment fragment = new VendorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public VendorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vendor, container, false);
    }

}
