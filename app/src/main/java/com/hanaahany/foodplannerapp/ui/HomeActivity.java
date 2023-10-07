package com.hanaahany.foodplannerapp.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.firebase.auth.FirebaseAuth;
import com.hanaahany.foodplannerapp.R;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {

    NavController navController;
    private MeowBottomNavigation bottomNavigation;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_home);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.show(1, true);

        // add your bottom navigation icon here
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_search));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_favorite));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_plan));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_account));

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                switch (model.getId()) {
                    case 1:
                        navController.navigate(R.id.homeFragment);
                        break;
                    case 2:
                        navController.navigate(R.id.searchFragment);
                        break;
                    case 3:
                        if (firebaseAuth.getCurrentUser().isAnonymous()) {
                            Toast.makeText(HomeActivity.this, "You should Signup", Toast.LENGTH_SHORT).show();
                        } else {
                            navController.navigate(R.id.favFragment);
                        }
                        break;
                    case 4:
                        if (firebaseAuth.getCurrentUser().isAnonymous()) {
                            Toast.makeText(HomeActivity.this, "You should Signup", Toast.LENGTH_SHORT).show();
                        } else {
                            navController.navigate(R.id.planFragment);
                        }
                        break;
                    case 5:
                        navController.navigate(R.id.accountFragment);
                        break;
                }
                return null;
            }
        });
    }
}