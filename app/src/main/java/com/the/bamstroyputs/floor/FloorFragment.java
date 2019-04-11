package com.the.bamstroyputs.floor;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.FragmentFloorBinding;
import com.the.bamstroyputs.interfaces.OnEditDialogClickListener;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Floor;
import com.the.bamstroyputs.room.RoomFragment;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.DialogUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FloorFragment extends Fragment implements OnItemClickListener {
    private FloorViewModel viewModel;
    private FragmentFloorBinding binding;
    private FloorAdapter adapter;

    public static FloorFragment newInstance(String building_id) {

        Bundle args = new Bundle();
        args.putString("BUILDING_ID", building_id);

        FloorFragment fragment = new FloorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FloorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_floor, container, false);

        adapter = new FloorAdapter(this);

        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(this).get(FloorViewModel.class);
        viewModel.getFloorListMutableLiveData(getArguments().getString("BUILDING_ID")).observe(this, new Observer<List<Floor>>() {
            @Override
            public void onChanged(@Nullable List<Floor> floors) {
                adapter.setList(floors);
            }
        });

        viewModel.getFloorMutableLiveData().observe(this, new Observer<Floor>() {
            @Override
            public void onChanged(@Nullable Floor floor) {
                adapter.addItem(floor);
            }
        });

        binding.fabFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addFloor(getArguments().getString("BUILDING_ID"));
            }
        });

        binding.floorRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.floorRecycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(String id) {
        ActivityUtil.pushFragment(RoomFragment.newInstance(id), getFragmentManager(), R.id.container, true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getFloorListMutableLiveData(getArguments().getString("BUILDING_ID")).removeObservers(this);
        viewModel.getFloorMutableLiveData().removeObservers(this);
    }
}
