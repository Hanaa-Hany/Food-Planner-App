package com.hanaahany.foodplannerapp.network;

import android.content.Context;

import com.hanaahany.foodplannerapp.model.MealResponse;

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
    public void makeNetworkCall(NetworkCallBack networkCallBack) {
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
    }
}
