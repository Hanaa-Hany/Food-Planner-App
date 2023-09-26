package com.hanaahany.foodplannerapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;


public class LoginFragment extends Fragment {

    TextInputEditText textInputEditTextEmail,textInputEditTextPassword;
    MaterialButton materialButtonLogin;
    TextView textViewForgotPassword,textViewSignUp;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        onClicks();
    }

    private void onClicks() {
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });
    }

    private void initViews() {
        textInputEditTextEmail=getView().findViewById(R.id.et_email_login);
        textInputEditTextPassword=getView().findViewById(R.id.et_password_login);
        materialButtonLogin=getView().findViewById(R.id.btn_login);
        textViewForgotPassword=getView().findViewById(R.id.tv_forget_password);
        textViewSignUp=getView().findViewById(R.id.tv_sign_up);
    }
}