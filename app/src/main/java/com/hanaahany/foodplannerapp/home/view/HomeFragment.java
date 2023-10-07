package com.hanaahany.foodplannerapp.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hanaahany.foodplannerapp.R;


import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.home.category.view.CategoryFragmentViewInterface;
import com.hanaahany.foodplannerapp.home.category.view.OnCategoryCallListener;
import com.hanaahany.foodplannerapp.home.mealoftheday.presenter.SingleMealPresenter;
import com.hanaahany.foodplannerapp.home.mealoftheday.presenter.SingleMealPresenterInterface;
import com.hanaahany.foodplannerapp.home.mealoftheday.view.MealViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import com.hanaahany.foodplannerapp.model.Category;
import com.hanaahany.foodplannerapp.home.category.presenter.CategoryPresenter;
import com.hanaahany.foodplannerapp.home.category.presenter.CategoryPresenterInterface;
import com.hanaahany.foodplannerapp.home.category.view.CategoryAdapter;
import com.hanaahany.foodplannerapp.model.Country;
import com.hanaahany.foodplannerapp.home.country.presenter.CountryPresenter;
import com.hanaahany.foodplannerapp.home.country.presenter.CountryPresenterInterface;
import com.hanaahany.foodplannerapp.home.country.view.CountryAdapter;
import com.hanaahany.foodplannerapp.home.country.view.CountryViewInterface;
import com.hanaahany.foodplannerapp.home.country.view.OnClickCountryInterface;
import com.hanaahany.foodplannerapp.model.Ingredients;
import com.hanaahany.foodplannerapp.home.ingredients.presenter.IngredientPresenter;
import com.hanaahany.foodplannerapp.home.ingredients.presenter.IngredientPresenterInterface;
import com.hanaahany.foodplannerapp.home.ingredients.view.IngredientViewInterface;
import com.hanaahany.foodplannerapp.home.ingredients.view.IngredientsAdapter;
import com.hanaahany.foodplannerapp.home.ingredients.view.OnIngredientCallListener;

import java.util.List;


public class HomeFragment extends Fragment implements CategoryFragmentViewInterface, IngredientViewInterface, CountryViewInterface, MealViewInterface, OnClickCountryInterface, OnIngredientCallListener, OnCategoryCallListener {

    private static final String TAG = "HomeFragmentRes";
    RecyclerView recyclerViewCotegories, recyclerViewCountries,recyclerViewIngredients;
    CategoryPresenterInterface categoryPresenterInterface;
    IngredientPresenterInterface ingredientPresenterInterface;
    CountryPresenterInterface countryPresenterInterface;
    SingleMealPresenterInterface singleMealPresenterInterface;
    CategoryAdapter categoryAdapter;
    CountryAdapter countryAdapter;
    IngredientsAdapter ingredientsAdapter;
    ImageView imageViewSingleMeal;
    TextView textViewNameOfSingleMeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");

        initViews();
        categoryPresenterInterface = new CategoryPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        ingredientPresenterInterface=new IngredientPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        countryPresenterInterface=new CountryPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        singleMealPresenterInterface=new SingleMealPresenter(this,Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));


        categoryPresenterInterface.getCategory();
        countryPresenterInterface.getCountry();
        ingredientPresenterInterface.getIngredients();
        singleMealPresenterInterface.getSingleMeal();




    }
    private void initViews() {
        recyclerViewCotegories = getView().findViewById(R.id.recycler_category);
        recyclerViewCountries = getView().findViewById(R.id.recycler_country);
        recyclerViewIngredients=getView().findViewById(R.id.recycler_ingredient);
        recyclerViewCotegories.setLayoutManager(new GridLayoutManager(getContext(), 3, RecyclerView.VERTICAL, false));
        recyclerViewCountries.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerViewIngredients.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
        imageViewSingleMeal=getView().findViewById(R.id.image_meal_of_the_day);
        textViewNameOfSingleMeal=getView().findViewById(R.id.tv_name_of_meal_the_day);

    }
    @Override
    public void showData(List<Category> list) {

        categoryAdapter = new CategoryAdapter(getContext(), list,this);
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
        HomeFragmentDirections.ActionHomeFragmentToCountryMealsFragment action=
                HomeFragmentDirections.actionHomeFragmentToCountryMealsFragment(nameOfCountry);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), nameOfCountry, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendName(String nameOfIngredient) {
        HomeFragmentDirections.ActionHomeFragmentToIngredientMealsFragment action=
                HomeFragmentDirections.actionHomeFragmentToIngredientMealsFragment(nameOfIngredient);
        Navigation.findNavController(getView()).navigate(action);
        Toast.makeText(getContext(), nameOfIngredient, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMeal(List<Meal> list) {
      String image=  list.get(0).getImage();
       String name= list.get(0).getNameOfMeal();
       String id=list.get(0).getId();
        Log.i(TAG, "showMeal: "+name+image);
        Glide.with(getContext()).load(image)
                .into(imageViewSingleMeal);
        textViewNameOfSingleMeal.setText(name);
        imageViewSingleMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragmentDirections.ActionHomeFragmentToMealDetailsFragment action=
                        HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(id);
                Navigation.findNavController(getView()).navigate(action);
                Log.i(TAG, "onClick: "+id);
                Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void sendNameOfCategory(String name) {
        HomeFragmentDirections.ActionHomeFragmentToCategoryMealsFragment action=
                HomeFragmentDirections.actionHomeFragmentToCategoryMealsFragment(name);
        Navigation.findNavController(getView()).navigate(action);

        Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
    }
}