package com.hanaahany.foodplannerapp.filterbyarea.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;
import com.hanaahany.foodplannerapp.filterbyarea.presenter.CountryMealsPresenter;
import com.hanaahany.foodplannerapp.filterbyarea.presenter.CountryMealsPresenterInterface;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.search.category.view.SearchFragment;

import java.util.List;


public class CountryMealsFragment extends Fragment implements CountryMealsViewInterface {

    private MeowBottomNavigation bottomNavigation;
    RecyclerView recyclerView;
    CountryMealsAdapter countryMealsAdapter;
    CountryMealsPresenterInterface countryMealsPresenterInterface;
    public static String NAME_OF_COUNTRY;
    private static final String TAG = "CountryMealsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_meals, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //getView make an error
        initViews();
        countryMealsPresenterInterface= new CountryMealsPresenter(this,Repository.getInstance(MealsClient.getInstance()));

        if (getArguments()!=null){
            NAME_OF_COUNTRY= CountryMealsFragmentArgs.fromBundle(getArguments()).getNameOfCountry();
        }
        countryMealsPresenterInterface.getCountryMeals();

    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigation.setVisibility(View.GONE);

    }

    @Override
    public void onPause() {
        super.onPause();
        bottomNavigation.setVisibility(View.VISIBLE);

    }

    @Override
    public void showData(List<CountryMeals> list) {
        countryMealsAdapter=new CountryMealsAdapter(list,getContext());
        recyclerView.setAdapter(countryMealsAdapter);
        Log.i(TAG, "showDataCountryMeals: "+list.size());
    }

    private void initViews() {
        bottomNavigation = getActivity().findViewById(R.id.bottomNavigation);
        recyclerView=getView().findViewById(R.id.recycler_country_meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));

    }
}