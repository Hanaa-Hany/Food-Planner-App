package com.hanaahany.foodplannerapp.chooseday.presenter;

import com.hanaahany.foodplannerapp.chooseday.view.ChooseDayViewInterface;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealPresenterInterface;
import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class ChooseDayPresenter implements NetworkCallBack, MealPresenterInterface {
    ChooseDayViewInterface _view;
    Repository _repo;

    public ChooseDayPresenter(ChooseDayViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

//    @Override
//    public void getMeal(String day) {
//        _repo.getMealsOfDay(day);
//    }

    @Override
    public void getMeal() {

    }

    @Override
    public void insertMealToFavourite(Meal meal) {

    }

    @Override
    public void deleteMeal(Meal meal) {
        _repo.deleteMealFav(meal);
    }

    @Override
    public void insertMealToPlan(Meal meal) {
        _repo.insertMealFav(meal);
    }

//    @Override
//    public void updateDayOfMeal(String id, String day) {
//
//    }
//
//    @Override
//    public void getMealById(String id) {
//
//    }

    @Override
    public void onSuccess(List<?> list) {

    }

    @Override
    public void onFailure(String errorMassage) {

    }
}
