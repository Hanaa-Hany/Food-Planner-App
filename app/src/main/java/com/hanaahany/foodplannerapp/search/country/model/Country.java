package com.hanaahany.foodplannerapp.search.country.model;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("strArea")
    private String nameOfCountry;

    public Country(String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }

    public void setNameOfCountry(String nameOfCountry) {
        this.nameOfCountry = nameOfCountry;
    }
}
