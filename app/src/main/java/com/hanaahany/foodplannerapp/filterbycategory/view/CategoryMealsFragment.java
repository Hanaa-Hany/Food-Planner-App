package com.hanaahany.foodplannerapp.filterbycategory.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.filterbyarea.presenter.CountryMealsPresenterInterface;
import com.hanaahany.foodplannerapp.filterbyarea.view.CountryMealsAdapter;
import com.hanaahany.foodplannerapp.filterbyarea.view.CountryMealsFragmentDirections;
import com.hanaahany.foodplannerapp.filterbyarea.view.OnItemClicked;
import com.hanaahany.foodplannerapp.filterbycategory.presenter.CategoryMealsPresenter;
import com.hanaahany.foodplannerapp.filterbycategory.presenter.CategoryMealsPresenterInterface;
import com.hanaahany.foodplannerapp.filterbyingredient.presenter.IngredientMealsPresenter;
import com.hanaahany.foodplannerapp.filterbyingredient.presenter.IngredientMealsPresenterInterface;
import com.hanaahany.foodplannerapp.filterbyingredient.view.IngredientMealsFragmentArgs;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import java.util.List;


public class CategoryMealsFragment extends Fragment implements CategoryMealsViewInterface, OnItemClicked {
    private MeowBottomNavigation bottomNavigation;
    CategoryMealsPresenterInterface categoryMealsPresenterInterface;
    RecyclerView recyclerView;
    CountryMealsAdapter countryMealsAdapter;
    public static String NAME_OF_CATEGORY;
    private static final String TAG = "CategoryMealsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        if (getArguments()!=null){
            NAME_OF_CATEGORY= CategoryMealsFragmentArgs.fromBundle(getArguments()).getNameOfCategory();

        }
        categoryMealsPresenterInterface = new CategoryMealsPresenter( Repository.getInstance(MealsClient.getInstance()),this);
        categoryMealsPresenterInterface.getCategory();

    }

    private void initViews() {
        bottomNavigation = getActivity().findViewById(R.id.bottomNavigation);
        recyclerView=getView().findViewById(R.id.recycler_category_meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
    }

    @Override
    public void showCategoryMeals(List<CountryMeals> list) {
        if (list!=null) {
            countryMealsAdapter = new CountryMealsAdapter(list, getContext(),this);
            recyclerView.setAdapter(countryMealsAdapter);
            Log.i(TAG, "showDataCountryMeals: " + list.size());
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        bottomNavigation.setVisibility(View.GONE);

    }

    @Override
    public void onPause() {
        super.onPause();
        bottomNavigation.setVisibility(View.VISIBLE);

    }

    //send id to details fragment
    @Override
    public void sendId(String id) {
        CategoryMealsFragmentDirections.ActionCategoryMealsFragmentToMealDetailsFragment action=
                CategoryMealsFragmentDirections.actionCategoryMealsFragmentToMealDetailsFragment(id);
        Navigation.findNavController(getView()).navigate(action);
        Log.i(TAG, "onClick: "+id);
    }
}