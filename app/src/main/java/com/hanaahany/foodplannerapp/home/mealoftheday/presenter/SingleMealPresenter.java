package com.hanaahany.foodplannerapp.home.mealoftheday.presenter;

import com.hanaahany.foodplannerapp.home.mealoftheday.view.MealViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class SingleMealPresenter implements NetworkCallBack, SingleMealPresenterInterface {

    private MealViewInterface _view;
    private Repository _repo;

    public SingleMealPresenter(MealViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getSingleMeal() {
        _repo.makeNetworkCallCategory(this,1);
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showMeal((List<Meal>) list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }
}
