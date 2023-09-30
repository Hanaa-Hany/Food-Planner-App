package com.hanaahany.foodplannerapp.filterbyarea.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryMealsResponse {
    @SerializedName("meals")
    private List<CountryMeals>countryMealsList;

    public CountryMealsResponse(List<CountryMeals> countryMealsList) {
        this.countryMealsList = countryMealsList;
    }

    public List<CountryMeals> getCountryMealsList() {
        return countryMealsList;
    }

    public void setCountryMealsList(List<CountryMeals> countryMealsList) {
        this.countryMealsList = countryMealsList;
    }
}
