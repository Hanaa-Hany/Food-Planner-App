package com.hanaahany.foodplannerapp.search.category.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.home.category.model.Category;
import com.hanaahany.foodplannerapp.home.category.presenter.CategoryPresenterInterface;
import com.hanaahany.foodplannerapp.home.category.view.CategoryAdapter;
import com.hanaahany.foodplannerapp.home.country.model.Country;
import com.hanaahany.foodplannerapp.home.country.presenter.CountryPresenterInterface;
import com.hanaahany.foodplannerapp.home.country.view.CountryAdapter;
import com.hanaahany.foodplannerapp.home.ingredients.presenter.IngredientPresenterInterface;
import com.hanaahany.foodplannerapp.home.ingredients.view.IngredientsAdapter;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment  {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}