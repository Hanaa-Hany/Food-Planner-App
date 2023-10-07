package com.hanaahany.foodplannerapp.plan.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.chooseday.view.ChooseDayFragment;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.plan.presenter.PlanPresenter;
import com.hanaahany.foodplannerapp.plan.presenter.PlanPresenterInterface;

import java.util.List;


public class PlanFragment extends Fragment implements PlanViewInterface , OnCancelClick{
    private static final String TAG = "PlanFragment";
    RecyclerView recyclerViewSat, recyclerViewSun, recyclerViewMon, recyclerViewTue,
            recyclerViewWed, recyclerViewThu, recyclerViewFri;
    PlanAdapter planAdapter;
    PlanPresenterInterface planPresenterInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        Log.i(TAG, "onViewCreated: " + ChooseDayFragment.DAY);
        planPresenterInterface = new PlanPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        planPresenterInterface.getMealSat();
        planPresenterInterface.getMealSun();
        planPresenterInterface.getMealMon();
        planPresenterInterface.getMealTue();
        planPresenterInterface.getMealWed();
        planPresenterInterface.getMealThu();
        planPresenterInterface.getMealFri();
//        planPresenterInterface.getMeal("Sunday");
//        planPresenterInterface.getMeal("Monday");
//        planPresenterInterface.getMeal("Tuesday");
//        planPresenterInterface.getMeal("Wednesday");
//        planPresenterInterface.getMeal("Thursday");
//        planPresenterInterface.getMeal("Friday");


    }

    private void initViews() {
        recyclerViewSat = getView().findViewById(R.id.recycler_saturday);
        recyclerViewSun = getView().findViewById(R.id.recycler_sunday);
        recyclerViewMon = getView().findViewById(R.id.recycler_monday);
        recyclerViewTue = getView().findViewById(R.id.recycler_tuesday);
        recyclerViewWed = getView().findViewById(R.id.recycler_wednesday);
        recyclerViewThu = getView().findViewById(R.id.recycler_thursday);
        recyclerViewFri = getView().findViewById(R.id.recycler_friday);

        recyclerViewSat.setLayoutManager(new GridLayoutManager(getContext(),1,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewSun.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewMon.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTue.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewWed.setLayoutManager(new GridLayoutManager(getContext(),1,  LinearLayoutManager.HORIZONTAL, false));
        recyclerViewThu.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFri.setLayoutManager(new GridLayoutManager(getContext(),1, LinearLayoutManager.HORIZONTAL, false));


    }


    @Override
    public void showMealDetails(LiveData<List<Meal>> list) {
        list.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                String dayOfMeal="";
                for(int i=0;i<meals.size();i++){
                     dayOfMeal = meals.get(i).getDay();
                    Log.i(TAG, "showMealDetails: " + meals.size());
                    Log.i(TAG, "showMealDetails: " + dayOfMeal);

                }

                if (dayOfMeal.equals("Saturday")) {
                    Log.i(TAG, "showMealDetails: " + dayOfMeal);
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewSat.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                }
                else if (dayOfMeal.equals("Sunday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewSun.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                } else if (dayOfMeal.equals("Monday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewMon.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                } else if (dayOfMeal.equals("Tuesday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewTue.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                } else if (dayOfMeal.equals("Wednesday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewWed.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                } else if (dayOfMeal.equals("Thursday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewThu.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                } else if (dayOfMeal.equals("Friday")) {
                    planAdapter = new PlanAdapter(getContext(), meals,PlanFragment.this);
                    recyclerViewFri.setAdapter(planAdapter);
                    Log.i(TAG, "showData: "+planAdapter.list.size());
                    planAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public void showError(String massage) {
        Toast.makeText(getContext(), massage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteMeal(Meal meal) {
        planPresenterInterface.deleteMeal(meal);

    }
}