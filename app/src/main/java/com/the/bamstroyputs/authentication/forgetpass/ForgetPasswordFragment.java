package com.the.bamstroyputs.authentication.forgetpass;


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
import com.the.bamstroyputs.databinding.FragmentForgetPasswordBinding;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.Utils;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends Fragment {
    private FragmentForgetPasswordBinding binding;
    private ForgetPasswordViewModel viewModel;

    public static ForgetPasswordFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ForgetPasswordFragment fragment = new ForgetPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forget_password, container, false);

        View view = binding.getRoot();

        binding.emailAddress.requestFocus();

        viewModel = ViewModelProviders.of(this).get(ForgetPasswordViewModel.class);

        viewModel.getEmailMutableData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sendEmailRequest(binding.emailAddress.getText().toString());
                ActivityUtil.backToHomeScreen(getFragmentManager());
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.backToHomeScreen(getFragmentManager());
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getEmailMutableData().removeObservers(this);
    }
}
