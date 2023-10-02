package com.hanaahany.foodplannerapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;
@Dao
public interface MealDAO {
    @Query("SELECT * FROM Meal_Table")
    LiveData<List<Meal>> getMeals();
    @Insert
    void insertMeal(Meal meal);
}
