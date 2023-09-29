package com.hanaahany.foodplannerapp.search.category.view;

import com.hanaahany.foodplannerapp.search.category.model.Category;
import com.hanaahany.foodplannerapp.search.country.model.Country;

import java.util.List;

public interface SearchViewInterface {
    void showData(List<Category>list);

    void showError(String error);
}
