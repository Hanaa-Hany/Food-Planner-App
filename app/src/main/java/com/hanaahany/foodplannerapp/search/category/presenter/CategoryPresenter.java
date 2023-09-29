package com.hanaahany.foodplannerapp.search.category.presenter;

import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.search.category.model.Category;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.category.view.SearchViewInterface;
import com.hanaahany.foodplannerapp.search.country.model.Country;

import java.util.List;

public class CategoryPresenter implements NetworkCallBack,CategoryPresenterInterface {

    private SearchViewInterface _view;
    private Repository _repo;

    public CategoryPresenter(SearchViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccess(List<?> list) {

        _view.showData((List<Category>) list);
      //  _view.showCountry((List<Country>) list);
    }

    @Override
    public void onFailure(String errorMassage) {
//        _repo.makeNetworkCallCategory(this);
    }

    @Override
    public void getCategory() {
        _repo.makeNetworkCallCategory(this,2);
    }




}
