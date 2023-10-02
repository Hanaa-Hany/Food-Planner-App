package com.hanaahany.foodplannerapp.search.presenter;

public interface SearchPresenterInterface {
    void getCategory();
    void getCountry();
    void getIngredient();
    void searchByName(String meal);
}
