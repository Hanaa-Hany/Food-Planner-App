package com.hanaahany.foodplannerapp.chooseday.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.button.MaterialButton;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.chooseday.presenter.ChooseDayPresenter;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealDetailsPresenter;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealPresenterInterface;
import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsFragment;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import java.util.List;


public class ChooseDayFragment extends Fragment implements ChooseDayViewInterface{

    MealPresenterInterface mealPresenterInterface;
    MaterialButton materialButtonSat, materialButtonSun, materialButtonMon,
            materialButtonTue, materialButtonWed, materialButtonThu, materialButtonFriday,materialButton;
    Meal meal;
    public static String DAY;
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
        if (getArguments()!=null){
          meal=  ChooseDayFragmentArgs.fromBundle(getArguments()).getMeal();
        }
        mealPresenterInterface=new ChooseDayPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        initViews();
        onClicks();
    }


    private void onClicks() {
        materialButtonMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Monday";
            }
        });
        materialButtonTue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Tuesday";
            }
        });
        materialButtonFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Friday";
            }
        });
        materialButtonWed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Wednesday";
            }
        });
        materialButtonSat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Saturday";
            }
        });
        materialButtonSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Sunday";
            }
        });
        materialButtonThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAY = "Thursday";
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+ MealDetailsFragment.ID_OF_MEAL);
                meal.setDay(ChooseDayFragment.DAY);
                mealPresenterInterface.insertMealToPlan(meal);
                Log.i(TAG, "onClick: "+meal.getNameOfMeal());
                Log.i(TAG, "onClick: "+meal.getDay());
                Toast.makeText(getContext(), "Saved To Plan", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_chooseDayFragment_to_planFragment);
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

    @Override
    public void showMealDetails(List<Meal> list) {

    }
}