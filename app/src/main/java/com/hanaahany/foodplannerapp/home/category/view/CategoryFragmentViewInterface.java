package com.hanaahany.foodplannerapp.home.category.view;

import com.hanaahany.foodplannerapp.model.Category;

import java.util.List;

public interface CategoryFragmentViewInterface {
    void showData(List<Category> list);

    void showError(String error);
}
