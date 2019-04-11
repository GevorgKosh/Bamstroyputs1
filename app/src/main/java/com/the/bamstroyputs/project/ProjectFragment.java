package com.the.bamstroyputs.project;


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
import com.the.bamstroyputs.buildings.BuildingsFragment;
import com.the.bamstroyputs.databinding.FragmentProjectBinding;
import com.the.bamstroyputs.interfaces.OnEditDialogClickListener;
import com.the.bamstroyputs.interfaces.OnItemClickListener;
import com.the.bamstroyputs.model.Project;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.DialogUtil;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment implements OnItemClickListener {
    private FragmentProjectBinding binding;
    private ProjectViewModel viewModel;
    private ProjectAdapter adapter;

    public static ProjectFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project, container, false);

        binding.generalRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProjectAdapter(this);

        binding.generalRecycler.setAdapter(adapter);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialog(getActivity(), getString(R.string.create_project), getString(R.string.project_name), R.layout.dialog, new OnEditDialogClickListener() {
                    @Override
                    public void onSaveName(String name) {
                        viewModel.addItem(name);
                    }
                });
            }
        });

        View view = binding.getRoot();
        viewModel = ViewModelProviders.of(this).get(ProjectViewModel.class);

        viewModel.getItemMutableLiveData().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> generalItems) {
                adapter.setList(generalItems);
            }
        });

        viewModel.getAddItemMutableLiveData().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project item) {
                adapter.addItem(item);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getAddItemMutableLiveData().removeObservers(this);
        viewModel.getItemMutableLiveData().removeObservers(this);
    }

    @Override
    public void onItemClick(String id) {
        ActivityUtil.pushFragment(BuildingsFragment.newInstance(id), getFragmentManager(), R.id.container, true);
    }
}
