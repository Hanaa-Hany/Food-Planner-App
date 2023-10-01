package com.hanaahany.foodplannerapp.mealdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.button.MaterialButton;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.filterbycategory.view.CategoryMealsFragmentArgs;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealDetailsPresenter;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealPresenterInterface;
import com.hanaahany.foodplannerapp.model.IngredientPojo;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.MealResponse;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface{

    private MeowBottomNavigation bottomNavigation;
    ImageView imageView;
    TextView textViewNameOfMeal,textViewNameOfArea,textViewInstruction;
    VideoView videoView;
    public static String ID_OF_MEAL;
    IngredientAdapter ingredientAdapter;
    MealPresenterInterface mealPresenterInterface;
    RecyclerView recyclerViewIngredients;
    private static final String TAG = "MealDetailsFragmentRes";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        if (getArguments()!=null){
            ID_OF_MEAL= MealDetailsFragmentArgs.fromBundle(getArguments()).getDetailsOfMeal();
            Log.i(TAG, "onViewCreated: "+ID_OF_MEAL);
        }
        mealPresenterInterface=new MealDetailsPresenter(this, Repository.getInstance(MealsClient.getInstance()));
        mealPresenterInterface.getMeal();
    }

    private void initViews() {
        bottomNavigation = getActivity().findViewById(R.id.bottomNavigation);
        imageView=getView().findViewById(R.id.image_details_meal);
        textViewNameOfMeal=getView().findViewById(R.id.tv_name_of_detail_meal);
        textViewNameOfArea=getView().findViewById(R.id.tv_country_details_meal);
        textViewInstruction=getView().findViewById(R.id.tv_instruction_details_meal);
        videoView=getView().findViewById(R.id.video_cook_meal);
        recyclerViewIngredients=getView().findViewById(R.id.recycler_ingredient_meal_details);

        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


    }

    @Override
    public void showMealDetails(List<Meal> list) {
        String name=list.get(0).getNameOfMeal();
        String area=list.get(0).getArea();
        String instruction=list.get(0).getInstructions();
        String image=list.get(0).getImage();
        String videoUrl=list.get(0).getYoutube();
        Log.i(TAG, "showMealDetails: "+name+area+instruction+image+videoUrl);
        Glide.with(getContext()).load(image)
                .into(imageView);
        textViewInstruction.setText(instruction);
        textViewNameOfMeal.setText(name);
        textViewNameOfArea.setText(area);
        //set Ingredient with image
        ArrayList<IngredientPojo> ingredientPojos = getIngredientPojoList(list.get(0));
        ingredientAdapter=new IngredientAdapter(getContext(),ingredientPojos);
        Log.i(TAG, "showMealDetails: "+ingredientPojos.size());
        ingredientAdapter.notifyDataSetChanged();
        recyclerViewIngredients.setAdapter(ingredientAdapter);

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
    private ArrayList<IngredientPojo> getIngredientPojoList(Meal meal) {
        ArrayList<IngredientPojo> myIngredientList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            String ingredient = null;
            try {
                ingredient = (String) meal.getClass().getMethod("getIngredient" + i).invoke(meal);

                String measure = (String) meal.getClass().getMethod("getMeasure" + i).invoke(meal);

                if (ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty()) {
                    String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";
                    myIngredientList.add(new IngredientPojo(ingredient, measure, imageUrl));
                    Log.i(TAG, "getIngredientPojoList: "+myIngredientList.size());
                }
            } catch (IllegalAccessException e) {
                //throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                //throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                //throw new RuntimeException(e);
            }
        }

        return myIngredientList;
    }
}