package com.hanaahany.foodplannerapp.auth.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanaahany.foodplannerapp.ui.HomeActivity;
import com.hanaahany.foodplannerapp.R;


public class LoginFragment extends Fragment {

    TextInputEditText textInputEditTextEmail,textInputEditTextPassword;
    MaterialButton materialButtonLogin;
    TextView textViewForgotPassword,textViewSignUp;
    NavController navController;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "LoginFragment";
    LottieAnimationView lottieAnimationViewLoading;

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
        firebaseAuth=FirebaseAuth.getInstance();
        navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        onClicks();
    }

    private void onClicks() {

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_loginFragment_to_forgetPasswordFragment);
            }
        });
        materialButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationViewLoading.setVisibility(View.VISIBLE);
                String email=textInputEditTextEmail.getText().toString().trim();
                String password=textInputEditTextPassword.getText().toString().trim();
                Log.i(TAG, "onClick: "+email);
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
                                    //updateUI(null);
                                }
                            }
                        });

//                Intent intent=new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        Intent intent=new Intent(getActivity(),HomeActivity.class);
        lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }

    private void initViews() {
        textInputEditTextEmail=getView().findViewById(R.id.et_email_login);
        textInputEditTextPassword=getView().findViewById(R.id.et_confirm_password_login);
        materialButtonLogin=getView().findViewById(R.id.btn_login);
        textViewForgotPassword=getView().findViewById(R.id.tv_forget_password);
        lottieAnimationViewLoading=getView().findViewById(R.id.lotti_login);

    }
}