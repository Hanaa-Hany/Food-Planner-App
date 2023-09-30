package com.hanaahany.foodplannerapp.filterbyingredient.presenter;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.filterbyingredient.view.IngredientMealsFragment;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class IngredientMealsPresenter implements IngredientMealsPresenterInterface, NetworkCallBack{
    private IngredientMealsFragment _view;
    private Repository _repo;

    public IngredientMealsPresenter(IngredientMealsFragment _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredient() {
        _repo.makeNetworkCallCategory(this,6);
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.getIngredientMeals((List<CountryMeals>)list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }
}
