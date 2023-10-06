package com.hanaahany.foodplannerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.ui.HomeActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;




public class SplachScreenFragment extends Fragment {

    FirebaseAuth mAuth;
    TextView textViewLogo;

    private static final String TAG = "SplachScreenFragment";

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splach_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewLogo=getView().findViewById(R.id.tv_logo);

                Animation anim= AnimationUtils.loadAnimation(getContext(),R.anim.logo_animation);
                textViewLogo.startAnimation(anim);

        mAuth=FirebaseAuth.getInstance();
        if (getArguments()!=null){

        }
        //RXjava


        Completable
                .timer(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete()
                    {
                        navController= Navigation.findNavController(getActivity(),R.id.nav_host_fragment);

                        if (mAuth.getCurrentUser()!=null){
                            startActivity(new Intent(getActivity(), HomeActivity.class));
                            getActivity().finish();

                        }else {
                            navController.navigate(R.id.action_splachScreenFragment_to_signFragment);
                        }
                        Log.i(TAG, "onComplete: ");
                    }



                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}