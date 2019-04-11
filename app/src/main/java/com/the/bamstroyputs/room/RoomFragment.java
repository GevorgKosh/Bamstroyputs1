package com.the.bamstroyputs.room;


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
import com.the.bamstroyputs.databinding.FragmentRoomBinding;
import com.the.bamstroyputs.interfaces.OnEditDialogClickListener;
import com.the.bamstroyputs.interfaces.OnEditDialogTwoLinesClickListener;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Room;
import com.the.bamstroyputs.order.OrderFragment;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.DialogUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoomFragment extends Fragment implements OnItemClickListener {
    private RoomViewModel viewModel;
    private FragmentRoomBinding binding;
    private RoomAdapter adapter;

    public static RoomFragment newInstance(String id) {

        Bundle args = new Bundle();
        args.putString("Floor_ID", id);

        RoomFragment fragment = new RoomFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RoomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false);

        adapter = new RoomAdapter();

        adapter.setListener(this);

        viewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
        viewModel.getRoomListMutableLiveData(getArguments().getString("Floor_ID")).observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(@Nullable List<Room> rooms) {
                adapter.setList(rooms);
            }
        });

        viewModel.getAddRoomMutableLiveData().observe(this, new Observer<Room>() {
            @Override
            public void onChanged(@Nullable Room room) {
                adapter.addRoom(room);
            }
        });

        binding.fabRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showTwoLinesDialog(getActivity(), getString(R.string.create_room), getString(R.string.room_name),
                        R.layout.two_lines_dialog, getString(R.string.room_count), new OnEditDialogTwoLinesClickListener() {
                            @Override
                            public void onSaveNameAndCount(String name, String count) {
                                viewModel.addRoom(name, count, getArguments().getString("Floor_ID"));
                            }
                        });
            }
        });

        binding.roomRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.roomRecycler.setAdapter(adapter);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onItemClick(String id) {
        ActivityUtil.pushFragment(OrderFragment.newInstance(id), getFragmentManager(), R.id.container, true);
    }
}
