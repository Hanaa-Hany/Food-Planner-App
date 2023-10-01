package com.hanaahany.foodplannerapp.home.category.model;

import com.hanaahany.foodplannerapp.home.category.model.Category;

import java.util.List;

public class CategoryResponse {
    private List<Category>categories;

    public CategoryResponse(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}