package com.hanaahany.foodplannerapp.network;

import android.content.Context;

import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.model.categorymodel.CategoryResponse;

import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsClient implements RemoteSource{

    private final String  BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static MealsClient mealsClient=null;
    private MealServices mealServices;

    private MealsClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         mealServices=retrofit.create(MealServices.class);


    }
    public static MealsClient getInstance(){
        if (mealsClient==null){
            mealsClient=new MealsClient();
        }
        return mealsClient;
    }

    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack,int id) {
        switch (id){
            case 1:
                mealServices.getSingleRandomMeal().enqueue(new Callback<MealResponse>() {
                    @Override
                    public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                        networkCallBack.onSuccess(response.body().getMeals());
                    }

                    @Override
                    public void onFailure(Call<MealResponse> call, Throwable t) {
                        networkCallBack.onFailure(t.getLocalizedMessage());
                    }
                });
       break;
        case 2:

        mealServices.getAllCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                networkCallBack.onSuccess(response.body().getCategories());
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                networkCallBack.onFailure(t.getLocalizedMessage());

            }
        });
        }
    }
}
