package com.hanaahany.foodplannerapp.search.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.model.categorymodel.Category;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.network.NetworkCallBack;
import com.hanaahany.foodplannerapp.search.presenter.CategoryPresenter;
import com.hanaahany.foodplannerapp.search.presenter.CategoryPresenterInterface;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment implements SearchViewInterface {

    private static final String TAG = "SearchFragment";
    RecyclerView recyclerView;
    List<Category>categories=new ArrayList<>();
    CategoryPresenterInterface categoryPresenterInterface;
    CategoryAdapter categoryAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        categoryPresenterInterface=new CategoryPresenter(this, Repository.getInstance(MealsClient.getInstance()));
       // categoryAdapter=new CategoryAdapter(getContext(),categories);
        //recyclerView.setAdapter(categoryAdapter);
        categoryPresenterInterface.getCategory();

    }

    private void initViews() {
        recyclerView=getView().findViewById(R.id.recycler_category);
    }


    @Override
    public void showData(List<Category> list) {
        categoryAdapter=new CategoryAdapter(getContext(),list);
        Log.i(TAG, "showData: "+list.size());
        recyclerView.setAdapter(categoryAdapter);
    }
}