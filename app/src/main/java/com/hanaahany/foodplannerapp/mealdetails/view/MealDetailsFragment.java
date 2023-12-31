package com.hanaahany.foodplannerapp.mealdetails.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.chooseday.view.ChooseDayFragment;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealDetailsPresenter;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealPresenterInterface;
import com.hanaahany.foodplannerapp.model.IngredientPojo;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MealDetailsFragment extends Fragment implements MealDetailsViewInterface{

    private MeowBottomNavigation bottomNavigation;
    ImageView imageView,imageViewCalender;
    TextView textViewNameOfMeal,textViewNameOfArea,textViewInstruction;
    YouTubePlayerView youTubePlayerView;
    public static String ID_OF_MEAL;
    IngredientAdapter ingredientAdapter;
    MealPresenterInterface mealPresenterInterface;
    RecyclerView recyclerViewIngredients;
    private static final String TAG = "MealDetailsFragmentRes";
    MaterialButton materialButtonFav,materialButtonPlan;
    private static Meal meal;
    String id;
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    public static List<Meal>meals=new ArrayList<>();


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
        onClick();
        if (getArguments()!=null){
            ID_OF_MEAL= MealDetailsFragmentArgs.fromBundle(getArguments()).getDetailsOfMeal();
            Log.i(TAG, "onViewCreated: "+ID_OF_MEAL);
        }
            if (ChooseDayFragment.DAY != null) {
//                meal.setDay(ChooseDayFragment.DAY);
//                mealPresenterInterface.insertMealToFavourite(meal);
//                Toast.makeText(getContext(), "Saved to Plan", Toast.LENGTH_SHORT).show();
//                ChooseDayFragment.DAY = null;

        }
        mealPresenterInterface=new MealDetailsPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        mealPresenterInterface.getMeal();

    }

    private void onClick() {
        materialButtonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // meals.add(meal);
                //backupFav(meals);
               // Log.i(TAG, "onClick: "+meals.size());
                mealPresenterInterface.insertMealToFavourite(meal);


                Toast.makeText(getContext(), "Saved to fav", Toast.LENGTH_SHORT).show();
            }
        });
        materialButtonPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+ID_OF_MEAL);
                //mealPresenterInterface.insertMealToFavourite(meal);
                MealDetailsFragmentDirections.ActionMealDetailsFragmentToChooseDayFragment action = MealDetailsFragmentDirections.actionMealDetailsFragmentToChooseDayFragment(meal);
                Navigation.findNavController(getView()).navigate(action);
               // Navigation.findNavController(getView()).navigate(R.id.action_mealDetailsFragment_to_chooseDayFragment);



            }
        });
        imageViewCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToMobileCalender();
            }
        });
    }

    private void initViews() {
        bottomNavigation = getActivity().findViewById(R.id.bottomNavigation);
        materialButtonPlan=getView().findViewById(R.id.btn_add_plan_details_meal);
        imageView=getView().findViewById(R.id.image_details_meal);
        textViewNameOfMeal=getView().findViewById(R.id.tv_name_of_detail_meal);
        textViewNameOfArea=getView().findViewById(R.id.tv_country_details_meal);
        textViewInstruction=getView().findViewById(R.id.tv_instruction_details_meal);
        youTubePlayerView=getView().findViewById(R.id.youtube_player_view);
        recyclerViewIngredients=getView().findViewById(R.id.recycler_ingredient_meal_details);
        materialButtonFav=getView().findViewById(R.id.btn_add_fav_details_meal);
        recyclerViewIngredients.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        imageViewCalender=getView().findViewById(R.id.image_calender);

    }

    @Override
    public void showMealDetails(List<Meal> list) {
         meal=list.get(0);
         id= meal.getId();
        String name=list.get(0).getNameOfMeal();
        String area=list.get(0).getArea();
        String instruction=list.get(0).getInstructions();
        String image=list.get(0).getImage();
        String videoUrl=list.get(0).getYoutube();
        String videoId = extractVideoId(videoUrl);
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

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                if (videoId!=null) {
                    youTubePlayer.loadVideo(videoId, 0);
                    youTubePlayer.pause();
                }
            }
        });

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
    private String extractVideoId(String videoUrl) {
        String videoId = null;
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|\\/v%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(videoUrl); //url is youtube url for which you want to extract video id.
        if (matcher.find()) {
            videoId = matcher.group();
        }
        return videoId;
    }

    private void addToMobileCalender() {
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, meal.getNameOfMeal())
                .putExtra(CalendarContract.Events.DESCRIPTION, "Enjoy a delicious " + meal.getNameOfMeal() + " for dinner!")//for description the meal in calender
                .putExtra(CalendarContract.Events.EVENT_LOCATION, "Home")//my location
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())//start time now
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + (60 * 60 * 1000)); // End time is 1 hour after start time
        startActivity(intent);
    }


}