package com.hanaahany.foodplannerapp.model;

import com.hanaahany.foodplannerapp.network.NetworkCallBack;

public interface RepositoryInterface {

    //for remotely data
    void makeNetworkCallCategory(NetworkCallBack networkCallBack,int id);
//    void makeNetworkCallCountry(NetworkCallBack networkCallBack);
//    void makeNetworkCallSingleMeal(NetworkCallBack networkCallBack);
}
