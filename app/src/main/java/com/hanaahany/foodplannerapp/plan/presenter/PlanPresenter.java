package com.hanaahany.foodplannerapp.plan.presenter;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.plan.view.PlanViewInterface;

import java.util.List;

public class PlanPresenter implements PlanPresenterInterface, NetworkCallBack {
    PlanViewInterface _view;
    Repository _repo;

    public PlanPresenter(PlanViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

//    @Override
//    public void getMeal(String day) {
//        _view.showMealDetails(_repo.getMealsOfDay(day));
//    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showMealDetails((LiveData<List<Meal>>) list);
    }

    @Override
    public void onFailure(String errorMassage) {
        _view.showError(errorMassage);
    }

    @Override
    public void getMealSat() {
        _view.showMealDetails(_repo.getMealsOfDaySat());
    }

    @Override
    public void getMealSun() {
        _view.showMealDetails(_repo.getMealsOfDaySun());
    }

    @Override
    public void getMealMon() {
        _view.showMealDetails(_repo.getMealsOfDayMon());
    }

    @Override
    public void getMealTue() {
        _view.showMealDetails(_repo.getMealsOfDayTue());
    }

    @Override
    public void getMealWed() {
        _view.showMealDetails(_repo.getMealsOfDayWed());
    }

    @Override
    public void getMealThu() {
        _view.showMealDetails(_repo.getMealsOfDayThu());
    }

    @Override
    public void getMealFri() {
        _view.showMealDetails(_repo.getMealsOfDayFri());
    }

    @Override
    public void deleteMeal(Meal meal) {
        _repo.deleteMealFav(meal);
    }
}
