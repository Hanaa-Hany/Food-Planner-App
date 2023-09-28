package com.hanaahany.foodplannerapp.signup.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.List;

public interface FirebaseCallBack {

    void onSuccess(Task<AuthResult> task);
    void onFailure(String errorMassage);
}
