package com.hanaahany.foodplannerapp.auth.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenter;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenterInterface;
import com.hanaahany.foodplannerapp.favmeal.view.FavAdapter;
import com.hanaahany.foodplannerapp.favmeal.view.FavBackup;
import com.hanaahany.foodplannerapp.favmeal.view.FavFragment;
import com.hanaahany.foodplannerapp.favmeal.view.FavViewInterface;
import com.hanaahany.foodplannerapp.favmeal.view.OnFavClick;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealDetailsPresenter;
import com.hanaahany.foodplannerapp.mealdetails.presenter.MealPresenterInterface;
import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsFragment;
import com.hanaahany.foodplannerapp.mealdetails.view.MealDetailsViewInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;
import com.hanaahany.foodplannerapp.search.view.SearchFragmentDirections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SettingFragment extends Fragment implements FavViewInterface , OnFavClick {

    TextView textViewBackupFav,textViewBackupPlan,textViewRetrieveFav,getTextViewRetrievePlan;
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    LottieAnimationView lottieAnimationViewLoading;
    FavPresenterInterface favPresenterInterface;
    List<Meal>backupList=new ArrayList<>();
    FavAdapter favAdapter;
    RecyclerView recyclerView;
    public static List<Meal>listBack;
    private static final String TAG = "SettingFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        favPresenterInterface = new FavPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        favPresenterInterface.getFavMeal();
        onClicks();
    }

    private void onClicks() {
        textViewBackupFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lottieAnimationViewLoading.setVisibility(View.VISIBLE);
                backupFav(backupList);
            }
        });
        textViewRetrieveFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFavFromBackup();
            }
        });
    }

    private void initViews() {
        textViewBackupFav=getView().findViewById(R.id.tv_backup_fav);
        textViewBackupPlan=getView().findViewById(R.id.tv_backup_plan);
        textViewRetrieveFav=getView().findViewById(R.id.tv_retrieve_fav);
        getTextViewRetrievePlan=getView().findViewById(R.id.tv_retrieve_plan);
        lottieAnimationViewLoading=getView().findViewById(R.id.lotti_settings);


    }
    private void backupFav(List mealFav) {
        //
        Map<String, List<Meal>> docData = new HashMap<>();
        docData.put("List", mealFav);
        firestore.collection("FavMeal")
                .document(firebaseAuth.getCurrentUser().getUid())
                .set(docData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Backup Fav Successfully", Toast.LENGTH_SHORT).show();
                            lottieAnimationViewLoading.setVisibility(View.INVISIBLE);
                        } else
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void getFavFromBackup() {
        firestore.collection("FavMeal")
                .document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            FavBackup favList = document.toObject(FavBackup.class);
                            Log.i(TAG, "onComplete: "+favList.getList().size());
                            Toast.makeText(getContext(), "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
                            listBack=favList.getList();
//                            SettingFragmentDirections.ActionSettingFragmentToFavFragment action=
//                                    SettingFragmentDirections.actionSettingFragmentToFavFragment(favList);
//                            Navigation.findNavController(getView()).navigate(action);


                        }


                    }
                });
    }



    @Override
    public void showData(LiveData<List<Meal>> list) {
        list.observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
               // List<Meal> meal = meals;
                backupList=meals;

            }
        });
    }

    @Override
    public void getMealDetails(String id) {

    }

    @Override
    public void deleteMeal(Meal meal) {
        favPresenterInterface.deleteMeal(meal);
    }
}