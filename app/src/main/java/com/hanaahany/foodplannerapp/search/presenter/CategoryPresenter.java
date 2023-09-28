package com.hanaahany.foodplannerapp.search.presenter;

import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.model.categorymodel.Category;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.view.SearchViewInterface;

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
    }

    @Override
    public void onFailure(String errorMassage) {

    }

    @Override
    public void getCategory() {
        _repo.makeNetworkCall(this);
    }
}
