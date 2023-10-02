package com.hanaahany.foodplannerapp.filterbycategory.presenter;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.filterbycategory.view.CategoryMealsViewInterface;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class CategoryMealsPresenter implements NetworkCallBack,CategoryMealsPresenterInterface {
    Repository _repo;
    CategoryMealsViewInterface _view;

    public CategoryMealsPresenter(Repository _repo, CategoryMealsViewInterface _view) {
        this._repo = _repo;
        this._view = _view;
    }

    @Override
    public void getCategory() {
        _repo.makeNetworkCall(this,7);
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showCategoryMeals((List<CountryMeals>)list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }
}
