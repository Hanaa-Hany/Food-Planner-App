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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.UserData;
import com.hanaahany.foodplannerapp.ui.HomeActivity;

import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment {

    ImageView imageViewProfile;
    TextInputEditText textInputEditTextEmail,textInputEditTextPassword,textInputEditTextConfirmPass,textInputEditTextUserName;
    MaterialButton materialButtonSignUp;
    private FirebaseAuth mAuth;
    private final StorageReference storageReference= FirebaseStorage.getInstance().getReference();
    private final FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    LottieAnimationView lottieAnimationViewLoading;
    private static final String TAG = "SignUpFragment";
    Uri image;
    String imageURL;
    String email;
    String userName;

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
                email=textInputEditTextEmail.getText().toString().trim();
                userName=textInputEditTextUserName.getText().toString().trim();
                String password=textInputEditTextPassword.getText().toString().trim();
                String confirm=textInputEditTextConfirmPass.getText().toString().trim();
                isValidPassword(password);
                //String regex="^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
                if (email.isEmpty()||password.isEmpty()||confirm.isEmpty()||userName.isEmpty()||image==null){
                    Toast.makeText(getContext(), "Please,Fill the Info", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (isValidPassword(password)&&password.equals(confirm)) {
                    lottieAnimationViewLoading.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.i(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        uploadImage();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(getContext(), task.getException().getLocalizedMessage(),
                                                Toast.LENGTH_SHORT).show();
                                        lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
                                        //updateUI(null);
                                    }
                                }
                            });
                }else if (!password.equals(confirm)){
                    Toast.makeText(getContext(), "Confirm doesn't match with Password", Toast.LENGTH_SHORT).show();

                    //textInputEditTextPassword.setError("must contain 1 letter small,capital , special char,and 8 digit at least");
                }else{
                    Toast.makeText(getContext(), "must contain 1 letter small,capital , special char,and 8 digit at least", Toast.LENGTH_SHORT).show();

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
            image=data.getData();
            imageViewProfile.setImageURI(image);
        }
    }

    private void updateUI() {
        Intent intent=new Intent(getActivity(), HomeActivity.class);
        lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }
    //Upload Profile image in Firebase Storage
    private void uploadImage(){
        storageReference.child("Photos")
                .child(mAuth.getCurrentUser().getUid())
                .putFile(image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            //Toast.makeText(getContext(), "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            getImageURL();
                        }else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    //get Image url after uploading in storage
    private  void getImageURL(){
        storageReference.child("Photos")
                .child(mAuth.getCurrentUser().getUid())
                .getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            imageURL=task.getResult().toString();
                            uploadUserData();

                        }else {
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    //Upload Data of user in FireStore db
    private void uploadUserData() {
        UserData userInfo=new UserData(email,userName,imageURL);
        firebaseFirestore.collection("Users")
                .document(mAuth.getCurrentUser().getUid())
                .set(userInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            updateUI();
                            Toast.makeText(getContext(), "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initViews() {
        imageViewProfile=getView().findViewById(R.id.circle_image_profile);
        textInputEditTextEmail=getView().findViewById(R.id.et_email_sign_up);
        textInputEditTextPassword=getView().findViewById(R.id.et_password_sign_up);
        textInputEditTextConfirmPass=getView().findViewById(R.id.et_confirm_password_sign_up);
        materialButtonSignUp=getView().findViewById(R.id.btn_sign_up);
        textInputEditTextUserName=getView().findViewById(R.id.et_user_name_sign_up);
        lottieAnimationViewLoading=getView().findViewById(R.id.lotti);


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