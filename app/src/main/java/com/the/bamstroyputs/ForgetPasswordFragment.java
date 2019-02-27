package com.the.bamstroyputs;


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

import com.the.bamstroyputs.databinding.FragmentForgetPasswordBinding;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.Utils;
import com.the.bamstroyputs.viewmodel.ForgetPasswordViewModel;


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

        viewModel.getIsOk().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                Toast.makeText(getContext(), getResources().getString(R.string.get_email), Toast.LENGTH_SHORT).show();
                ActivityUtil.backToHomeScreen(getFragmentManager());
            }
        });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Utils.allowToLogin(getActivity(), binding.emailAddress.getText().toString(), getString(R.string.empty_fields))){
                    return;
                }
                if(!Utils.isValidEmail(binding.emailAddress.getText().toString())){
                    Toast.makeText(getContext(), getResources().getString(R.string.wrong_email_format), Toast.LENGTH_SHORT).show();
                    return;
                }

                viewModel.sendEmailRequest(binding.emailAddress.getText().toString());
            }
        });

        viewModel.sendEmailRequest(binding.emailAddress.getText().toString());

        return view;
    }

}
