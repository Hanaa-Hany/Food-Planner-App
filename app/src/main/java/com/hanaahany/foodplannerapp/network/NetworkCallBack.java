package com.hanaahany.foodplannerapp.network;

import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.categorymodel.Category;

import java.util.List;

public interface NetworkCallBack {

    //Using Generic
    void onSuccess(List<?>list);
    void onFailure(String errorMassage);
}
