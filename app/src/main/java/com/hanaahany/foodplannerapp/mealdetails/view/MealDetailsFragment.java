package com.hanaahany.foodplannerapp.mealdetails.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.hanaahany.foodplannerapp.model.MealResponse;

import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface{

    private MeowBottomNavigation bottomNavigation;
    ImageView imageView;
    TextView textViewNameOfMeal,textViewNameOfArea,textViewInstruction;
    VideoView videoView;


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
    }

    private void initViews() {
        bottomNavigation = getActivity().findViewById(R.id.bottomNavigation);
        imageView=getView().findViewById(R.id.image_details_meal);
        textViewNameOfMeal=getView().findViewById(R.id.tv_name_of_detail_meal);
        textViewNameOfArea=getView().findViewById(R.id.tv_country_details_meal);
        textViewInstruction=getView().findViewById(R.id.tv_instruction_details_meal);
        videoView=getView().findViewById(R.id.video_cook_meal);
    }

    @Override
    public void showMealDetails(List<MealResponse> list) {
        String name=list.get(0).getMeals().get(0).getNameOfMeal();
        String area=list.get(0).getMeals().get(0).getArea();
        String instruction=list.get(0).getMeals().get(0).getInstructions();
        String image=list.get(0).getMeals().get(0).getImage();
        String videoUrl=list.get(0).getMeals().get(0).getYoutube();
        Glide.with(getContext()).load(image)
                .into(imageView);
        textViewInstruction.setText(instruction);
        textViewNameOfMeal.setText(name);
        textViewNameOfArea.setText(area);
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
}