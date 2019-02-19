package com.the.bamstroyputs;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.databinding.FragmentLoginBinding;
import com.the.bamstroyputs.model.User;
import com.the.bamstroyputs.viewmodel.LoginViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private LoginViewModel viewModel;

    public static LoginFragment newInstance() {

        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);
        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

        viewModel.getUserMutableLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                DataController.getInstance().setUser(user);
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loginRequest(binding.email.getText().toString(), binding.password.getText().toString());
            }
        });

        return view;
    }

}
