package com.hanaahany.foodplannerapp.network;

import android.util.Log;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMealsResponse;
import com.hanaahany.foodplannerapp.filterbyarea.view.CountryMealsFragment;
import com.hanaahany.foodplannerapp.filterbycategory.view.CategoryMealsFragment;
import com.hanaahany.foodplannerapp.filterbyingredient.view.IngredientMealsFragment;
import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.home.category.model.CategoryResponse;
import com.hanaahany.foodplannerapp.home.country.model.CountryResponse;
import com.hanaahany.foodplannerapp.home.ingredients.model.IngredientsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsClient implements RemoteSource {

    private final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsClient mealsClient = null;
    private MealServices mealServices;
    private static final String TAG = "MealsClient";
    private MealsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mealServices = retrofit.create(MealServices.class);


    }

    public static MealsClient getInstance() {
        if (mealsClient == null) {
            mealsClient = new MealsClient();
        }
        return mealsClient;
    }


    @Override
    public void makeNetworkCallCategories(NetworkCallBack networkCallBack, int id) {
        switch (id) {
            case 1:
                mealServices.getSingleRandomMeal().enqueue(new Callback<MealResponse>() {
                    @Override
                    public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                        if (response.body() != null) {
                            networkCallBack.onSuccess(response.body().getMeals());
                        }
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
                        if (response.body() != null) {
                            networkCallBack.onSuccess(response.body().getCategories());
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {
                        networkCallBack.onFailure(t.getLocalizedMessage());

                    }
                });
                break;
            case 3:
                mealServices.getAllCountries().enqueue(new Callback<CountryResponse>() {
                    @Override
                    public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                        if (response.body() != null) {
                            networkCallBack.onSuccess(response.body().getCountries());
                        }

                    }

                    @Override
                    public void onFailure(Call<CountryResponse> call, Throwable t) {
                        networkCallBack.onFailure(t.getLocalizedMessage());

                    }
                });
                break;
            case 4:
                mealServices.getAllIngredients().enqueue(new Callback<IngredientsResponse>() {
                    @Override
                    public void onResponse(Call<IngredientsResponse> call, Response<IngredientsResponse> response) {
                        networkCallBack.onSuccess(response.body().getIngrediants());
                    }

                    @Override
                    public void onFailure(Call<IngredientsResponse> call, Throwable t) {
                        networkCallBack.onFailure(t.getLocalizedMessage());
                    }
                });
                break;
            case 5:
                Log.i(TAG, "makeNetworkCallCategories: "+CountryMealsFragment.NAME_OF_COUNTRY);
                mealServices.filterByArea(CountryMealsFragment.NAME_OF_COUNTRY).enqueue(new Callback<CountryMealsResponse>() {
                    @Override
                    public void onResponse(Call<CountryMealsResponse> call, Response<CountryMealsResponse> response) {
                        networkCallBack.onSuccess(response.body().getCountryMealsList());

                    }

                    @Override
                    public void onFailure(Call<CountryMealsResponse> call, Throwable t) {

                    }
                });

            case 6:
                Log.i(TAG, "makeNetworkCallCategories: "+CountryMealsFragment.NAME_OF_COUNTRY);
                mealServices.filterByIngredient(IngredientMealsFragment.NAME_OF_Ingredient).enqueue(new Callback<CountryMealsResponse>() {
                    @Override
                    public void onResponse(Call<CountryMealsResponse> call, Response<CountryMealsResponse> response) {
                        networkCallBack.onSuccess(response.body().getCountryMealsList());

                    }

                    @Override
                    public void onFailure(Call<CountryMealsResponse> call, Throwable t) {

                    }
                });
                break;
            case 7:
                Log.i(TAG, "makeNetworkCallCategories: "+CountryMealsFragment.NAME_OF_COUNTRY);
                mealServices.filterByCategory(CategoryMealsFragment.NAME_OF_CATEGORY).enqueue(new Callback<CountryMealsResponse>() {
                    @Override
                    public void onResponse(Call<CountryMealsResponse> call, Response<CountryMealsResponse> response) {
                        networkCallBack.onSuccess(response.body().getCountryMealsList());

                    }

                    @Override
                    public void onFailure(Call<CountryMealsResponse> call, Throwable t) {

                    }
                });
                break;

        }

    }
}

//    @Override
//    public void makeNetworkCallCategories(NetworkCallBack networkCallBack) {
//        mealServices.getAllCategories().enqueue(new Callback<CategoryResponse>() {
//            @Override
//            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
//                networkCallBack.onSuccess(response.body().getCategories());
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponse> call, Throwable t) {
//                networkCallBack.onFailure(t.getLocalizedMessage());
//
//            }
//        });
//    }
//
//
//    @Override
//    public void makeNetworkCallCountries(NetworkCallBack networkCallBack) {
//
//        mealServices.getAllCountries().enqueue(new Callback<CountryResponse>() {
//            @Override
//            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
//                networkCallBack.onSuccess(response.body().getCountries());
//
//            }
//
//            @Override
//            public void onFailure(Call<CountryResponse> call, Throwable t) {
//                networkCallBack.onFailure(t.getLocalizedMessage());
//
//            }
//        });
//    }
//
//    @Override
//    public void makeNetworkSingleMeal(NetworkCallBack networkCallBack) {
//
//        mealServices.getSingleRandomMeal().enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                networkCallBack.onSuccess(response.body().getMeals());
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallBack.onFailure(t.getLocalizedMessage());
//            }
//        });
//    }
//}
