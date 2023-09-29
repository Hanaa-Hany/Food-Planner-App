package com.hanaahany.foodplannerapp.search.country.presenter;

import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.category.view.SearchViewInterface;
import com.hanaahany.foodplannerapp.search.country.model.Country;
import com.hanaahany.foodplannerapp.search.country.view.CountryViewInterface;
import com.hanaahany.foodplannerapp.search.ingredients.model.Ingredients;

import java.util.List;

public class CountryPresenter  implements NetworkCallBack,CountryPresenterInterface{
    private CountryViewInterface _view;
    private Repository _repo;

    public CountryPresenter(CountryViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccess(List<?> list) {
        _view.showCountry((List<Country>) list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }

    @Override
    public void getCountry() {
        _repo.makeNetworkCallCategory(this,3);
    }
}
