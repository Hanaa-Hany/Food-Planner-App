package com.hanaahany.foodplannerapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;
@Dao
public interface MealDAO {
//    @Query("SELECT * FROM Meal_Table")
//    LiveData<List<Meal>> getMeals();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMeal(Meal meal);
    @Delete
    void deleteMeal(Meal meal);
    @Query("SELECT * From Meal_Table WHERE day = :day")
    LiveData<List<Meal>> getMealsOfDay(String day);

    @Query("UPDATE Meal_Table SET day = :day WHERE id = :id")
    void updateColumnDay(String id, String day);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMealPlan(Meal meal);

//    @Query("SELECT * FROM Meal_Table")
//    LiveData<List<Meal>> getMealsPlan();
//    @Insert
//    void insertMealPlan(Meal meal);
//    @Delete
//    void deleteMealPlan(Meal meal, String day);

}
