package com.hanaahany.foodplannerapp.filterbyarea.model;

import com.google.gson.annotations.SerializedName;

public class CountryMeals {

    @SerializedName("strMeal")
    private String name;
    @SerializedName("strMealThumb")
    private String image;
    @SerializedName("idMeal")
    private String id;

    public CountryMeals(String name, String image, String id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
