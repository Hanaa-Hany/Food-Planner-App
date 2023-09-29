package com.hanaahany.foodplannerapp.search.ingredients.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientsResponse {
    @SerializedName("meals")
    private List<Ingredients> ingrediants;

    public IngredientsResponse(List<Ingredients> meals) {
        this.ingrediants = meals;
    }

    public List<Ingredients> getIngrediants() {
        return ingrediants;
    }

    public void setIngrediants(List<Ingredients> ingrediants) {
        this.ingrediants = ingrediants;
    }
}
