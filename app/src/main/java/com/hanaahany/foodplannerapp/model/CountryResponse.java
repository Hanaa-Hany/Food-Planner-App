package com.hanaahany.foodplannerapp.model;

import com.google.gson.annotations.SerializedName;
import com.hanaahany.foodplannerapp.model.Country;

import java.util.List;

public class CountryResponse {
    @SerializedName("meals")
   private List<Country> countries;

    public CountryResponse(List<Country> meals) {
        this.countries = meals;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
