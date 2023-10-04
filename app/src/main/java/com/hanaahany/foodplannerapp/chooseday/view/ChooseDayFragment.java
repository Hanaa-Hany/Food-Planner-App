package com.hanaahany.foodplannerapp.chooseday.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsFragment;


public class ChooseDayFragment extends Fragment {

    MaterialButton materialButtonSat, materialButtonSun, materialButtonMon,
            materialButtonTue, materialButtonWed, materialButtonThu, materialButtonFriday,materialButton;
    public static String DAY=null;
    private static final String TAG = "ChooseDayFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        onClicks();
    }

    private void onClicks() {
        if (materialButtonFriday.performClick()) {
            DAY = "Friday";
            Log.i(TAG, "onClicks: "+DAY);
        } else if (materialButtonMon.isClickable()) {
            DAY = "Monday";
        } else if (materialButtonSat.isClickable()) {
            DAY = "Saturday";
        } else if (materialButtonSun.isClickable()) {
            DAY = "Sunday";
        } else if (materialButtonThu.isClickable()) {
            DAY = "Thursday";
        } else if (materialButtonWed.isClickable()) {
            DAY = "Wednesday";
        } else if (materialButtonTue.isClickable()) {
            DAY = "Tuesday";
        }
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+ MealDetailsFragment.ID_OF_MEAL);
              //  Navigation.findNavController(getView()).navigate(R.id.action_chooseDayFragment_to_mealDetailsFragment);
            }
        });
    }

    private void initViews() {
        materialButtonFriday = getView().findViewById(R.id.btn_friday);
        materialButtonSat = getView().findViewById(R.id.btn_saturday);
        materialButtonSun = getView().findViewById(R.id.btn_sunday);
        materialButtonMon = getView().findViewById(R.id.btn_monday);
        materialButtonTue = getView().findViewById(R.id.btn_tuesday);
        materialButtonWed = getView().findViewById(R.id.btn_wednesday);
        materialButtonThu = getView().findViewById(R.id.btn_thursday);
        materialButton=getView().findViewById(R.id.continye);
    }
}