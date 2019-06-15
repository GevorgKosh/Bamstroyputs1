package com.the.bamstroyputs.buildings.editBuilding;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.FragmentEditBuildingBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditBuildingFragment extends Fragment {
    private FragmentEditBuildingBinding binding;
    private EditBuildingViewModel viewModel;

    public static EditBuildingFragment newInstance(String buildingId, String buildingName, String projectId) {
        Bundle args = new Bundle();
        args.putString("BUILDING_ID", buildingId);
        args.putString("PROJECT_ID", projectId);
        args.putString("BUILDING_NAME", buildingName);
        EditBuildingFragment fragment = new EditBuildingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public EditBuildingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_building, container, false);
        binding.setName(getArguments().getString("BUILDING_NAME"));
        binding.fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.changeBuildingData(binding.buildingNumberFloors.getText().toString(), getArguments().getString("PROJECT_ID"),
                                            getArguments().getString("BUILDING_ID"), binding.buildingNewName.getText().toString());
            }
        });

        viewModel = ViewModelProviders.of(this).get(EditBuildingViewModel.class);

        viewModel.getMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(getActivity(), getString(R.string.building_data_successfull_change), Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                }else {
                    Toast.makeText(getActivity(), getString(R.string.something_wrong), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }

}
