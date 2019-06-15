package com.the.bamstroyputs.floor.editFloor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditFloorFragment extends Fragment {

    public static EditFloorFragment newInstance() {
        
        Bundle args = new Bundle();
        
        EditFloorFragment fragment = new EditFloorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public EditFloorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_floor, container, false);
    }

}
