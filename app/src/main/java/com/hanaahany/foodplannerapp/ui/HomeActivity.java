package com.hanaahany.foodplannerapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.hanaahany.foodplannerapp.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {

    NavController navController;
    private MeowBottomNavigation bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment_home);
        bottomNavigation=findViewById(R.id.bottomNavigation);
        bottomNavigation.show(1, true);

        // add your bottom navigation icon here
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_search));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_favorite));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_plan));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch(model.getId())
                {
                    case 1:

                        navController.navigate(R.id.action_searchFragment_to_homeFragment);
                       // navController.navigate(R.id.action_favFragment_to_homeFragment);


                        break;
                    case 2:
                        navController.navigate(R.id.action_homeFragment_to_searchFragment);
                       // navController.navigate(R.id.action_favFragment_to_searchFragment);

                        break;
                    case 3:
                        navController.navigate(R.id.action_homeFragment_to_favFragment);
                     //   navController.navigate(R.id.action_searchFragment_to_favFragment);

                        break;
                }
                return null;
            }
        });







    }
}