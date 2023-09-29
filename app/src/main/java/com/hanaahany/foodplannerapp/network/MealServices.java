package com.hanaahany.foodplannerapp.network;

import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.search.category.model.CategoryResponse;
import com.hanaahany.foodplannerapp.search.country.model.CountryResponse;
import com.hanaahany.foodplannerapp.search.ingredients.model.IngredientsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealServices {
    @GET("random.php")
    Call<MealResponse> getSingleRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse>getAllCategories();

    @GET("list.php?a=list")
    Call<CountryResponse>getAllCountries();

    @GET("list.php?i=list")
    Call<IngredientsResponse>getAllIngredients();

}
