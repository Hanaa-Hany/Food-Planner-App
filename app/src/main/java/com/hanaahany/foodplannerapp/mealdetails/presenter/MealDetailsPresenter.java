package com.hanaahany.foodplannerapp.mealdetails.presenter;

import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class MealDetailsPresenter implements NetworkCallBack, MealPresenterInterface {
    MealDetailsViewInterface _view;
    Repository _repo;

    public MealDetailsPresenter(MealDetailsViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMeal() {
        _repo.makeNetworkCallCategory(this, 8);
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showMealDetails((List<Meal>) list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }
}
