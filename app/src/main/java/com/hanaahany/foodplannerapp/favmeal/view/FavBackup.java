package com.hanaahany.foodplannerapp.favmeal.view;

import com.hanaahany.foodplannerapp.model.Meal;

import java.util.ArrayList;

public class FavBackup {

    private ArrayList<Meal> List;

    public FavBackup() {
    }

    public FavBackup(ArrayList<Meal> list) {
        List = list;
    }

    public ArrayList<Meal> getList() {
        return List;
    }

    public void setList(ArrayList<Meal> list) {
        List = list;
    }
}
