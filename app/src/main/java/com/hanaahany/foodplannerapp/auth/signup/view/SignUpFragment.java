package com.hanaahany.foodplannerapp.auth.signup.view;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.ui.HomeActivity;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    ImageView imageViewProfile;
    TextInputEditText textInputEditTextEmail,textInputEditTextPassword,textInputEditTextConfirmPass;
    MaterialButton materialButtonSignUp;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpFragment";

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

        mAuth = FirebaseAuth.getInstance();
        onClicks();


    }

    private void onClicks() {


        materialButtonSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email=textInputEditTextEmail.getText().toString().trim();
                String password=textInputEditTextPassword.getText().toString().trim();
                String confirm=textInputEditTextConfirmPass.getText().toString().trim();
                isValidPassword(password);
                //String regex="^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
                if (isValidPassword(password)==true&&password.equals(confirm)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.i(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        updateUI(user);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getContext(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }else{
                    textInputEditTextPassword.setError("must contain 1 letter small,capital , special char,and 8 digit at least");
                }
            }
        });
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK&&data!=null){
            Uri image=data.getData();
            imageViewProfile.setImageURI(image);
        }
    }

    private void updateUI(FirebaseUser user) {
        Intent intent=new Intent(getActivity(), HomeActivity.class);
        intent.putExtra("Token",user);
        startActivity(intent);
    }

    private void initViews() {
        imageViewProfile=getView().findViewById(R.id.circle_image_profile);
        textInputEditTextEmail=getView().findViewById(R.id.et_email_sign_up);
        textInputEditTextPassword=getView().findViewById(R.id.et_password_sign_up);
        textInputEditTextConfirmPass=getView().findViewById(R.id.et_confirm_password_sign_up);
        materialButtonSignUp=getView().findViewById(R.id.btn_sign_up);


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }
    }
    public static boolean isValidPassword(String password)
    {

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}