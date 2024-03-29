package com.the.bamstroyputs.authentication.login;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.the.bamstroyputs.HomeActivity;
import com.the.bamstroyputs.R;
import com.the.bamstroyputs.authentication.forgetpass.ForgetPasswordFragment;
import com.the.bamstroyputs.controller.DataController;
import com.the.bamstroyputs.databinding.FragmentLoginBinding;
import com.the.bamstroyputs.model.User;
import com.the.bamstroyputs.util.ActivityUtil;
import com.the.bamstroyputs.util.Utils;


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
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();

        viewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);

        viewModel.getUserMutableLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loginRequest(binding.email.getText().toString(), binding.password.getText().toString());
            }
        });

        binding.forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.pushFragment(ForgetPasswordFragment.newInstance(), getFragmentManager(), R.id.fragment, true);
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.getUserMutableLiveData().removeObservers(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && isResumed()){
            binding.email.setText("");
            binding.password.setText("");
            Toast.makeText(getActivity(), "I`m back", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "I`m back not", Toast.LENGTH_SHORT).show();
        }
    }

    //    @Override
//    public boolean getUserVisibleHint() {
//        binding.email.setText("");
//        binding.password.setText("");
//        Toast.makeText(getActivity(), "I`m back", Toast.LENGTH_SHORT).show();
//        return super.getUserVisibleHint();
//    }
}
