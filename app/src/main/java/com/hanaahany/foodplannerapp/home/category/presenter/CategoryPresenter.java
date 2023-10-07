package com.hanaahany.foodplannerapp.home.category.presenter;

import com.hanaahany.foodplannerapp.home.category.view.CategoryFragmentViewInterface;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.model.Category;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;

import java.util.List;

public class CategoryPresenter implements NetworkCallBack, CategoryPresenterInterface {

    private CategoryFragmentViewInterface _view;
    private Repository _repo;

    public CategoryPresenter(CategoryFragmentViewInterface _view, Repository _repo) {
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
        _repo.makeNetworkCall(this,2);
    }




}
