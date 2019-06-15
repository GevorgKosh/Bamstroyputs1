package com.the.bamstroyputs.project.editProject;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.R;
import com.the.bamstroyputs.databinding.FragmentEditProjectBinding;
import com.the.bamstroyputs.interfaces.OnFragmentDetachedListener;
import com.the.bamstroyputs.interfaces.OnFragmentResult;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProjectFragment extends Fragment {
    private FragmentEditProjectBinding binding;
    private EditProjectViewModel viewModel;
    private OnFragmentDetachedListener fragmentDetachedListener;
    private Listener listener;

    public static EditProjectFragment newInstance(String projectId) {

        Bundle args = new Bundle();
        args.putString("PROJECT_ID", projectId);
        EditProjectFragment fragment = new EditProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public EditProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_project, container, false);

        binding.fabApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.changeProjectName(binding.projectNewName.getText().toString(), getArguments().getString("PROJECT_ID"));
                getActivity().onBackPressed();
            }
        });

        viewModel = ViewModelProviders.of(this).get(EditProjectViewModel.class);
        viewModel.getMutableLiveData(binding.projectNewName.getText().toString(), getArguments().getString("PROJECT_ID")).observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        return binding.getRoot();
    }

    interface Listener {
        void onGoneListener();
    }

    public void setFragmentDetachedListener(OnFragmentDetachedListener fragmentDetachedListener) {
        this.fragmentDetachedListener = fragmentDetachedListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();

//        if (fragmentDetachedListener == null) {
//            fragmentDetachedListener
//        }
//        fragmentDetachedListener.onDetachFragment();
    }


}
