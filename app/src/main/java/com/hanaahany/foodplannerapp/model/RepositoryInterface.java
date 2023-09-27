package com.hanaahany.foodplannerapp.model;

import com.hanaahany.foodplannerapp.network.NetworkCallBack;

public interface RepositoryInterface {

    //for remotely data
    void makeNetworkCall(NetworkCallBack networkCallBack);
}
