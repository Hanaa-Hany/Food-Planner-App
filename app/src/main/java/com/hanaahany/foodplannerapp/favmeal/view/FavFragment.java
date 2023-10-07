package com.hanaahany.foodplannerapp.favmeal.view;

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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.db.ConcreteLocalSource;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenter;
import com.hanaahany.foodplannerapp.favmeal.presenter.FavPresenterInterface;
import com.hanaahany.foodplannerapp.model.Meal;
import com.hanaahany.foodplannerapp.model.Repository;
import com.hanaahany.foodplannerapp.network.MealsClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FavFragment extends Fragment implements FavViewInterface, OnFavClick {
    RecyclerView recyclerView;
    FavPresenterInterface favPresenterInterface;
    FavAdapter favAdapter;
    private static final String TAG = "FavFragmentRes";
    private final StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    ArrayList<Meal>list1=new ArrayList<>();

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
        favPresenterInterface = new FavPresenter(this, Repository.getInstance(MealsClient.getInstance(), ConcreteLocalSource.getInstance(getContext())));
        favPresenterInterface.getFavMeal();

    }

    private void initViews() {
        recyclerView = getView().findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }


    @Override
    public void showData(LiveData<List<Meal>> list) {
        list.observe(getViewLifecycleOwner(), new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if (meals.isEmpty()) {
                    getFavFromBackup();
                    Log.i(TAG, "onChanged: " + meals.size());
                } else {
                    //list1.addAll(meals);
                    Log.i(TAG, "onChanged: " + meals.size());
                    //backupFav(meals);
                    //getFavFromBackup();
                    favAdapter = new FavAdapter(meals, getContext(), FavFragment.this);
                    recyclerView.setAdapter(favAdapter);
                    favAdapter.notifyDataSetChanged();
                }
            }


        });


    }

    @Override
    public void getMealDetails(String id) {
        FavFragmentDirections.ActionFavFragmentToMealDetailsFragment action =
                FavFragmentDirections.actionFavFragmentToMealDetailsFragment(id);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void deleteMeal(Meal meal) {
        favPresenterInterface.deleteMeal(meal);
    }

    private void backupFav(List mealFav) {
        //
        Map<String, List<Meal>> docData = new HashMap<>();
        docData.put("List", mealFav);
        firebaseFirestore.collection("FavMeal")
                .document(firebaseAuth.getCurrentUser().getUid())
                .set(docData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Backup Fav Successfully", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getFavFromBackup() {
        firebaseFirestore.collection("FavMeal")
                .document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                             FavBackup h = document.toObject(FavBackup.class);
                            Log.i(TAG, "onComplete: "+h.getList().size());


                            favAdapter = new FavAdapter(h.getList(), getContext(), FavFragment.this);
                            recyclerView.setAdapter(favAdapter);
                            favAdapter.notifyDataSetChanged();
                            Log.i(TAG, "onComplete:Adapter "+favAdapter.getItemCount());
                        }


                    }
                });
    }
}