package com.hanaahany.foodplannerapp.favmeal.presenter;

import androidx.lifecycle.LiveData;

import com.hanaahany.foodplannerapp.favmeal.view.FavViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.model.RepositoryInterface;

import java.util.List;

public class FavPresenter implements FavPresenterInterface {
    FavViewInterface _view;
    RepositoryInterface _repo;

    public FavPresenter(FavViewInterface _view, RepositoryInterface _repo) {
        this._view = _view;
        this._repo = _repo;
    }


    @Override
    public void getFavMeal() {
        _view.showData(_repo.getStoredMeals());
    }
}
