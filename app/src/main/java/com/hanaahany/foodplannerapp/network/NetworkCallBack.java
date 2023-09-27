package com.hanaahany.foodplannerapp.network;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public interface NetworkCallBack {
    void onSuccess(List<Meal>list);
    void onFailure(String errorMassage);
}
