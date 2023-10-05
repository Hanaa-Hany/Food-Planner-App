package com.hanaahany.foodplannerapp.plan.view;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public interface PlanViewInterface {
    void showMealDetails(LiveData<List<Meal>> list);
    void showError(String massage);
}
