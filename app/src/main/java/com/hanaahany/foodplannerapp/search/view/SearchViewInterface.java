package com.hanaahany.foodplannerapp.search.view;

import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.home.category.model.Category;
import com.hanaahany.foodplannerapp.home.country.model.Country;
import com.hanaahany.foodplannerapp.model.Ingredients;

import java.util.List;

public interface SearchViewInterface {
    void searchCategory(List<Category>list);
    void searchCountry(List<Country>list);
    void searchIngredient(List<Ingredients>list);


}
