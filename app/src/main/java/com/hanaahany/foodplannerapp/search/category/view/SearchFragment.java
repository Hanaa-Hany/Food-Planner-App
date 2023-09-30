package com.hanaahany.foodplannerapp.search.category.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.search.category.model.Category;
import com.hanaahany.foodplannerapp.search.category.presenter.CategoryPresenter;
import com.hanaahany.foodplannerapp.search.category.presenter.CategoryPresenterInterface;
import com.hanaahany.foodplannerapp.search.country.model.Country;
import com.hanaahany.foodplannerapp.search.country.presenter.CountryPresenter;
import com.hanaahany.foodplannerapp.search.country.presenter.CountryPresenterInterface;
import com.hanaahany.foodplannerapp.search.country.view.CountryAdapter;
import com.hanaahany.foodplannerapp.search.country.view.CountryViewInterface;
import com.hanaahany.foodplannerapp.search.country.view.OnClickCountryInterface;
import com.hanaahany.foodplannerapp.search.ingredients.model.Ingredients;
import com.hanaahany.foodplannerapp.search.ingredients.presenter.IngredientPresenter;
import com.hanaahany.foodplannerapp.search.ingredients.presenter.IngredientPresenterInterface;
import com.hanaahany.foodplannerapp.search.ingredients.view.IngredientViewInterface;
import com.hanaahany.foodplannerapp.search.ingredients.view.IngredientsAdapter;
import com.hanaahany.foodplannerapp.search.ingredients.view.OnIngredientCallListener;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface , IngredientViewInterface , CountryViewInterface , OnClickCountryInterface, OnIngredientCallListener {

    private static final String TAG = "SearchFragment";
    RecyclerView recyclerViewCotegories, recyclerViewCountries,recyclerViewIngredients;
    List<Category> categories = new ArrayList<>();
    List<Country> countryList = new ArrayList<>();
    CategoryPresenterInterface categoryPresenterInterface;
    IngredientPresenterInterface ingredientPresenterInterface;
    CountryPresenterInterface countryPresenterInterface;
    CategoryAdapter categoryAdapter;
    CountryAdapter countryAdapter;
    IngredientsAdapter ingredientsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        categoryPresenterInterface = new CategoryPresenter(this, Repository.getInstance(MealsClient.getInstance()));
        ingredientPresenterInterface=new IngredientPresenter(this, Repository.getInstance(MealsClient.getInstance()));
        countryPresenterInterface=new CountryPresenter(this, Repository.getInstance(MealsClient.getInstance()));

//        categoryAdapter=new CategoryAdapter(getContext(),categories);
//        recyclerViewCotegories.setAdapter(categoryAdapter);
//        countryAdapter=new CountryAdapter(countryList,getContext());
//        Log.i(TAG, "showDataCountry: "+countryList.size());
//        recyclerViewCountries.setAdapter(countryAdapter);
        categoryPresenterInterface.getCategory();
        countryPresenterInterface.getCountry();
        ingredientPresenterInterface.getIngredients();


    }

    private void initViews() {
        recyclerViewCotegories = getView().findViewById(R.id.recycler_category);
        recyclerViewCountries = getView().findViewById(R.id.recycler_country);
        recyclerViewIngredients=getView().findViewById(R.id.recycler_ingredient);
        recyclerViewCotegories.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
        recyclerViewCountries.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewIngredients.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));


    }


    @Override
    public void showData(List<Category> list) {

        categoryAdapter = new CategoryAdapter(getContext(), list);
        Log.i(TAG, "showData: " + list.size());
        recyclerViewCotegories.setAdapter(categoryAdapter);
    }




    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showIngredient(List<Ingredients> list) {
        ingredientsAdapter = new IngredientsAdapter(list,getContext(),this);
        recyclerViewIngredients.setAdapter(ingredientsAdapter);
        Log.i(TAG, "showIngredient: "+list.size());
    }

    @Override
    public void showCountry(List<Country> list) {
        countryAdapter = new CountryAdapter(list,getContext(),this);
        recyclerViewCountries.setAdapter(countryAdapter);
        Log.i(TAG, "showIngredient: "+list.size());
    }


    @Override
    public void onItemClicked(String nameOfCountry) {
        com.hanaahany.foodplannerapp.search.category.view.SearchFragmentDirections.ActionSearchFragmentToCountryMealsFragment action=
                SearchFragmentDirections.actionSearchFragmentToCountryMealsFragment(nameOfCountry);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), nameOfCountry, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendName(String nameOfIngredient) {
        SearchFragmentDirections.ActionSearchFragmentToIngredientMealsFragment action=
                SearchFragmentDirections.actionSearchFragmentToIngredientMealsFragment(nameOfIngredient);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), nameOfIngredient, Toast.LENGTH_SHORT).show();
    }
}