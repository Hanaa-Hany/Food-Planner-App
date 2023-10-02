package com.hanaahany.foodplannerapp.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.home.category.model.Category;
import com.hanaahany.foodplannerapp.home.category.view.CategoryAdapter;
import com.hanaahany.foodplannerapp.home.category.view.OnCategoryCallListener;
import com.hanaahany.foodplannerapp.home.country.model.Country;
import com.hanaahany.foodplannerapp.home.country.view.CountryAdapter;
import com.hanaahany.foodplannerapp.home.country.view.OnClickCountryInterface;
import com.hanaahany.foodplannerapp.home.ingredients.view.IngredientsAdapter;
import com.hanaahany.foodplannerapp.home.ingredients.view.OnIngredientCallListener;
import com.hanaahany.foodplannerapp.model.Ingredients;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.search.presenter.SearchPresenter;
import com.hanaahany.foodplannerapp.search.presenter.SearchPresenterInterface;

import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface, OnCategoryCallListener, OnClickCountryInterface, OnIngredientCallListener {

    Chip chipSamsung, chipApple, chipOppo;
    ChipGroup chipGroup;
    RecyclerView recyclerViewSearch;
    SearchPresenterInterface searchPresenterInterface;
    CountryAdapter countryAdapter;
    IngredientsAdapter ingredientAdapter;
    MealAdapter mealAdapter;
    public static String NAME_MEAL;

    CategoryAdapter categoryAdapter;
    private static final String TAG = "SearchFragment";
    public int flag = 0;
    SearchView searchView;

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
        searchPresenterInterface = new SearchPresenter(SearchFragment.this, Repository.getInstance(MealsClient.getInstance()));
        searchPresenterInterface.getCategory();
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                Chip chip = group.findViewById(checkedId);


                Log.i(TAG, "onCheckedChanged: " + chip);
                if (chip != null) {
                    String category = chip.getText().toString();
                    Log.i(TAG, "onCheckedChanged: " + category);
                    switch (category) {
                        case "Category":


//                            searchPresenterInterface = new SearchPresenter(SearchFragment.this, Repository.getInstance(MealsClient.getInstance()));
                            searchPresenterInterface.getCategory();

                            break;
                        case "Country":

                            searchPresenterInterface.getCountry();

                            break;
                        case "Ingredient":

                            searchPresenterInterface.getIngredient();

                            break;
                    }
                    Log.i(TAG, "onCheckedChanged: " + category);
                    //int selectedColor = getResources().getColor(R.color.pink);
                    chip.setChipBackgroundColorResource(R.color.pink);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                NAME_MEAL=query;

                Log.i(TAG, "onQueryTextSubmit: ");

                searchPresenterInterface.searchByName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.i(TAG, "onQueryTextChange: ");
                NAME_MEAL=newText;
                searchPresenterInterface.searchByName(newText);

                return true;
            }
        });

    }



    private void initViews() {
        recyclerViewSearch = getView().findViewById(R.id.recycler_search);
        chipSamsung = getView().findViewById(R.id.chip_samsung);
        chipApple = getView().findViewById(R.id.chip_Apple);
        chipOppo = getView().findViewById(R.id.chip_Oppo);
        chipGroup = getView().findViewById(R.id.chip_group);
         searchView = getView().findViewById(R.id.searchView);

    }




    @Override
    public void searchCountry(List<Country> list) {
        Log.i(TAG, "searchCountry: " + list.size());
        recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        countryAdapter = new CountryAdapter(list, getContext(), SearchFragment.this);
        recyclerViewSearch.setAdapter(countryAdapter);
        countryAdapter.notifyDataSetChanged();


    }
    @Override
    public void searchCategory(List<Category> list) {
        recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));

        Log.i(TAG, "search: " + list.size());
        categoryAdapter = new CategoryAdapter(getContext(), list, SearchFragment.this);
        recyclerViewSearch.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();


    }

    @Override
    public void searchIngredient(List<Ingredients> list) {
        Log.i(TAG, "search: "+list.size());
        recyclerViewSearch.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        ingredientAdapter = new IngredientsAdapter(list,getContext(),SearchFragment.this);
        recyclerViewSearch.setAdapter(ingredientAdapter);
    }

    @Override
    public void searchByName(List<Meal> list) {
        Log.i(TAG, "search: "+list.size());
        if (list.size()>0) {
            recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false));
            mealAdapter = new MealAdapter(list, getContext());
            recyclerViewSearch.setAdapter(mealAdapter);
            mealAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void sendNameOfCategory(String name) {
        Log.i(TAG, "sendNameOfCategory: "+name);
        SearchFragmentDirections.ActionSearchFragmentToCategoryMealsFragment action=
                SearchFragmentDirections.actionSearchFragmentToCategoryMealsFragment(name);
        Navigation.findNavController(getView()).navigate(action);

        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClicked(String name) {
        Log.i(TAG, "onItemClicked: "+name);
        SearchFragmentDirections.ActionSearchFragmentToCountryMealsFragment action=
                SearchFragmentDirections.actionSearchFragmentToCountryMealsFragment(name);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendName(String nameOfIngredient) {
        Log.i(TAG, "sendName: "+nameOfIngredient);
        SearchFragmentDirections.ActionSearchFragmentToIngredientMealsFragment action=
                SearchFragmentDirections.actionSearchFragmentToIngredientMealsFragment(nameOfIngredient);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), nameOfIngredient, Toast.LENGTH_SHORT).show();
    }
}