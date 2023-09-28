package com.hanaahany.foodplannerapp.signup.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.hanaahany.foodplannerapp.R;

public class SignUpFragment extends Fragment {

    ImageView imageViewProfile;
    TextInputEditText textInputEditTextEmail,textInputEditTextPassword,textInputEditTextConfirmPass;
    MaterialButton materialButtonSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    private void initViews() {
        imageViewProfile=getView().findViewById(R.id.imageView);
        textInputEditTextEmail=getView().findViewById(R.id.et_email_sign_up);
        textInputEditTextPassword=getView().findViewById(R.id.et_password_sign_up);
        textInputEditTextConfirmPass=getView().findViewById(R.id.et_confirm_password_sign_up);
        materialButtonSignUp=getView().findViewById(R.id.btn_sign_up);


    }
}