package com.hanaahany.foodplannerapp.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.ui.HomeActivity;
import com.hanaahany.foodplannerapp.ui.MainActivity;


public class AccountFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    TextView textViewUserName,textViewEmail,textViewSettings,textViewAbout,textViewLogOut;
    ImageView imageViewUser;
    MaterialButton materialButtonEditProfile;
    private GoogleApiClient mGoogleApiClient;

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
                .enableAutoManage(getActivity() , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
        initViews();
        onClicks();
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
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent);
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
        });
        materialButtonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}