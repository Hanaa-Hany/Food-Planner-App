package com.hanaahany.foodplannerapp.mealoftheday.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.network.RemoteSource;

import java.util.List;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragmentRes";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

//        MealsClient.getInstance().makeNetworkCall(new NetworkCallBack() {
//            @Override
//            public void onSuccess(List<Meal> list) {
//                Log.i(TAG, "onSuccess: "+list.size());
//            }
//
//            @Override
//            public void onFailure(String errorMassage) {
//
//            }
//        });



    }
}