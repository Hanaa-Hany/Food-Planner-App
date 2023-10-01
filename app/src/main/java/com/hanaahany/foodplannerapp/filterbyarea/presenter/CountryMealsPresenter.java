package com.hanaahany.foodplannerapp.filterbyarea.presenter;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.filterbyarea.view.CountryMealsFragment;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class CountryMealsPresenter implements NetworkCallBack, CountryMealsPresenterInterface {
    private CountryMealsFragment _view;
    private Repository _repo;

    public CountryMealsPresenter(CountryMealsFragment _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccess(List<?> list) {
        
        _view.showData((List<CountryMeals>) list);
      //  Toast.makeText(_view.getContext(), list.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String errorMassage) {
      //  Toast.makeText(_view.getContext(), errorMassage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCountryMeals() {
        _repo.makeNetworkCallCategory(this,5);
    }
}
