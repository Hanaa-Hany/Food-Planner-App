package com.hanaahany.foodplannerapp.auth.account;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.UserData;
import com.hanaahany.foodplannerapp.ui.HomeActivity;
import com.hanaahany.foodplannerapp.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    TextView textViewUserName,textViewEmail,textViewSettings,textViewAbout,textViewLogOut;
    ImageView imageViewUser;
    MaterialButton materialButtonEditProfile;
    private GoogleApiClient mGoogleApiClient;
    private final FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    private final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private static final String TAG = "AccountFragment";
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), AccountFragment.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        initViews();
        onClicks();
        getData();
    }

    private void onClicks() {
        textViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textViewAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        textViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (firebaseAuth.getCurrentUser().getProviderId().equals("password")){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }else {

                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    FirebaseAuth.getInstance().signOut();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "Logout Successfully!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        materialButtonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_accountFragment_to_editProfileFragment);

            }
        });
    }

    private void initViews() {
        textViewAbout=getView().findViewById(R.id.tv_about_us);
        textViewUserName=getView().findViewById(R.id.tv_name_of_user);
        textViewSettings=getView().findViewById(R.id.tv_general_setting);
        textViewLogOut=getView().findViewById(R.id.tv_log_out);
        imageViewUser=getView().findViewById(R.id.image_profile);
        textViewEmail=getView().findViewById(R.id.tv_email_of_user);
        materialButtonEditProfile=getView().findViewById(R.id.btn_edit_profile);
        navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment_home);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void getData(){
        firestore.collection("Users")
                .document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()&&task.getResult()!=null) {
                            DocumentSnapshot user = task.getResult();
                            Log.i(TAG, "onComplete: "+user.getString("userName"));
                            textViewUserName.setText(user.getString("userName"));
                            textViewEmail.setText(user.getString("email"));
                            Picasso.get().load(user.getString("imageUser")).into(imageViewUser);
                        }
                    }
                });
    }
}