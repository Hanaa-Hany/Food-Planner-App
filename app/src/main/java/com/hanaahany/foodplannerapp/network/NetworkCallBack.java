package com.hanaahany.foodplannerapp.network;

import java.util.List;

public interface NetworkCallBack {

    //Using Generic
    void onSuccess(List<?>list);
    void onFailure(String errorMassage);
}
