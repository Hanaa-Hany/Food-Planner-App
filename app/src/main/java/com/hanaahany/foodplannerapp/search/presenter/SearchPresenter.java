package com.hanaahany.foodplannerapp.search.presenter;

import com.hanaahany.foodplannerapp.model.Category;
import com.hanaahany.foodplannerapp.model.Country;
import com.hanaahany.foodplannerapp.model.Ingredients;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.view.SearchViewInterface;

import java.util.List;

public class SearchPresenter implements SearchPresenterInterface, NetworkCallBack {
    SearchViewInterface _view;
    Repository _repo;

    public SearchPresenter(SearchViewInterface _view, Repository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccess(List<?> list) {
        if (list.get(0) instanceof Category) {
            _view.searchCategory((List<Category>) list);
        } else if (list.get(0) instanceof Country) {
            _view.searchCountry((List<Country>) list);
        } else if (list.get(0) instanceof Ingredients) {
            _view.searchIngredient((List<Ingredients>) list);
        }
        else if (list.get(0) instanceof Meal) {
            _view.searchByName((List<Meal>) list);
        }
//        _view.searchCategory((List<Category>)list);
//        _view.searchCountry((List<Country>)list);
        //_view.searchIngredient((List<Ingredients>)list);
    }

    @Override
    public void onFailure(String errorMassage) {

    }

    @Override
    public void getCategory() {

        _repo.makeNetworkCall(this,2);
    }

    @Override
    public void getCountry() {

        _repo.makeNetworkCall(this,3);
    }

    @Override
    public void getIngredient() {

        _repo.makeNetworkCall(this,4);
    }

    @Override
    public void searchByName(String meal) {
        _repo.makeNetworkCall(this,9);
    }
}
