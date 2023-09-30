package com.hanaahany.foodplannerapp.network;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMealsResponse;
import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.search.category.model.CategoryResponse;
import com.hanaahany.foodplannerapp.search.country.model.CountryResponse;
import com.hanaahany.foodplannerapp.search.ingredients.model.IngredientsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealServices {
    @GET("random.php")
    Call<MealResponse> getSingleRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse>getAllCategories();

    @GET("list.php?a=list")
    Call<CountryResponse>getAllCountries();

    @GET("list.php?i=list")
    Call<IngredientsResponse>getAllIngredients();

    @GET("filter.php")
    Call <CountryMealsResponse>filterByArea(@Query("a")String country);

    @GET("filter.php")
    Call <CountryMealsResponse>filterByIngredient(@Query("i")String country);

}
