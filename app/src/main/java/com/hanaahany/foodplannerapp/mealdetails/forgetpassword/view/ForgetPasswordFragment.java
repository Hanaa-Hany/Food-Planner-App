package com.hanaahany.foodplannerapp.mealdetails.forgetpassword.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanaahany.foodplannerapp.R;


public class ForgetPasswordFragment extends Fragment {

    MaterialButton materialButtonForgetPassword;
    TextInputEditText textInputEditTextEmail;
    FirebaseAuth mAuth;
    private static final String TAG = "ForgetPasswordFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forget_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        initViews();
        onClicks();

    }

    private void onClicks() {
        materialButtonForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=textInputEditTextEmail.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Email sent.");
                                }
                            }
                        });
            }
        });

    }

    private void initViews() {
        materialButtonForgetPassword=getView().findViewById(R.id.btn_send_forget);
        textInputEditTextEmail=getView().findViewById(R.id.et_email_forget);
    }
}