package com.the.bamstroyputs.buildings;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.buildings.editBuilding.EditBuildingFragment;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.databinding.FragmentBuildingsBinding;
import com.the.bamstroyputs.floor.FloorFragment;
import com.the.bamstroyputs.interfaces.OnEditDialogTwoLinesClickListener;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.interfaces.OnMenuClickListenerWithName;
import com.the.bamstroyputs.model.Building;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.DialogUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuildingsFragment extends Fragment implements OnItemClickListener, OnMenuClickListenerWithName {
    private FragmentBuildingsBinding binding;
    private BuildingViewModel viewModel;
    private BuildingAdapter adapter;

    public static BuildingsFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("ID", id);
        BuildingsFragment fragment = new BuildingsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public BuildingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buildings, container, false);

        adapter = new BuildingAdapter(this, this);

        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(BuildingViewModel.class);

        viewModel.getListMutableLiveData(getArguments().getString("ID")).observe(this, new Observer<List<Building>>() {
            @Override
            public void onChanged(@Nullable List<Building> buildings) {
                adapter.setList(buildings);
            }
        });

        viewModel.getBuildingMutableLiveData().observe(this, new Observer<Building>() {
            @Override
            public void onChanged(@Nullable Building building) {
                adapter.addItem(building);
            }
        });

        binding.addBuildingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showTwoLinesDialog(getActivity(), getString(R.string.create_building),
                        getString(R.string.building_name), R.layout.two_lines_dialog, getString(R.string.floor_count), new OnEditDialogTwoLinesClickListener() {
                            @Override
                            public void onSaveNameAndCount(String name, String count) {
                                viewModel.createBuilding(name, count, getArguments().getString("ID"));
                            }
                        });
            }
        });

        binding.buildingRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        binding.buildingRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(String id) {
        ActivityUtil.pushFragment(FloorFragment.newInstance(id), getFragmentManager(), R.id.container, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getBuildingMutableLiveData().removeObservers(this);
        viewModel.getListMutableLiveData(getArguments().getString("ID")).removeObservers(this);
    }

    @Override
    public void onMenuClick(String id, String name) {
        ActivityUtil.pushFragment(EditBuildingFragment.newInstance(id, name, getArguments().getString("ID")), getFragmentManager(), R.id.container, true);
    }
}
