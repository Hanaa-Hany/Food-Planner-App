package com.hanaahany.foodplannerapp.search.category.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("idCategory")
    private String categoryId;
    @SerializedName("strCategory")
    private String categoryName;
    @SerializedName("strCategoryThumb")
    private String categoryImage;
    @SerializedName("strCategoryDescription")
    private String categoryDescription;

    public Category(String categoryId, String categoryName, String categoryImage, String categoryDescription) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
