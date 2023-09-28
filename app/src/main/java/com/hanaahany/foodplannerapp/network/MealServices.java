package com.hanaahany.foodplannerapp.network;

import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.model.categorymodel.CategoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealServices {
    @GET("random.php")
    Call<MealResponse> getSingleRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse>getAllCategories();
}
