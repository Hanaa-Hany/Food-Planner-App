package com.hanaahany.foodplannerapp.favmeal.view;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public interface FavViewInterface {
    void showData(LiveData<List<Meal>> list);
}
