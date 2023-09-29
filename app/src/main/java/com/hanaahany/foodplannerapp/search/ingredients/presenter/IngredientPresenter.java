package com.hanaahany.foodplannerapp.search.ingredients.presenter;

import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.category.model.Category;
import com.hanaahany.foodplannerapp.search.category.view.SearchViewInterface;
import com.hanaahany.foodplannerapp.search.ingredients.model.Ingredients;
import com.hanaahany.foodplannerapp.search.ingredients.view.IngredientViewInterface;

import java.util.List;

public class IngredientPresenter implements NetworkCallBack,IngredientPresenterInterface{

    private IngredientViewInterface _view;
    private Repository _repo;

    public IngredientPresenter(IngredientViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showIngredient((List<Ingredients>) list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }

    @Override
    public void getIngredients() {

        _repo.makeNetworkCallCategory(this,4);
    }
}
