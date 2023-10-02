package com.hanaahany.foodplannerapp.model;

import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.network.RemoteSource;

public class Repository implements RepositoryInterface{
    RemoteSource remoteSource;
    private  static Repository repository=null;

    public Repository(RemoteSource remoteSource) {
        this.remoteSource = remoteSource;
    }

    public static Repository getInstance(RemoteSource remoteSource){
        if (repository==null){
            repository=new Repository(remoteSource);
        }
        return repository;
    }



    @Override
    public void makeNetworkCall(NetworkCallBack networkCallBack, int id) {
        remoteSource.makeNetworkCallCategories(networkCallBack,id);
    }

//    @Override
//    public void makeNetworkCallCountry(NetworkCallBack networkCallBack) {
//        remoteSource.makeNetworkCallCountries(networkCallBack);
//    }
//
//    @Override
//    public void makeNetworkCallSingleMeal(NetworkCallBack networkCallBack) {
//        remoteSource.makeNetworkSingleMeal(networkCallBack);
//    }
}
