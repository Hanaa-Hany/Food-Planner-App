package com.hanaahany.foodplannerapp.favmeal.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenter;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenterInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import java.util.List;


public class FavFragment extends Fragment implements FavViewInterface,OnFavClick{
    RecyclerView recyclerView;
    FavPresenterInterface favPresenterInterface;
    FavAdapter favAdapter;
    private static final String TAG = "FavFragmentRes";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        favPresenterInterface=new FavPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        favPresenterInterface.getFavMeal();

    }

    private void initViews() {
        recyclerView=getView().findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }


    @Override
    public void showData(LiveData<List<Meal>> list) {
        list.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                Log.i(TAG, "onChanged: "+meals.size());
                favAdapter=new FavAdapter(meals,getContext(),FavFragment.this);
                recyclerView.setAdapter(favAdapter);
                favAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void getMealDetails(String id) {
        FavFragmentDirections.ActionFavFragmentToMealDetailsFragment action=
                FavFragmentDirections.actionFavFragmentToMealDetailsFragment(id);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void deleteMeal(Meal meal) {
favPresenterInterface.deleteMeal(meal);
    }
}