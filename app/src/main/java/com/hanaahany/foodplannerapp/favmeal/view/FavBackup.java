package com.hanaahany.foodplannerapp.favmeal.view;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.hanaahany.foodplannerapp.model.Meal;

import java.io.Serializable;
import java.util.ArrayList;

public class FavBackup implements Parcelable {

    private ArrayList<Meal> List;

    public FavBackup() {
    }

    public FavBackup(ArrayList<Meal> list) {
        List = list;
    }

    protected FavBackup(Parcel in) {
        List = in.createTypedArrayList(Meal.CREATOR);
    }

    public static final Creator<FavBackup> CREATOR = new Creator<FavBackup>() {
        @Override
        public FavBackup createFromParcel(Parcel in) {
            return new FavBackup(in);
        }

        @Override
        public FavBackup[] newArray(int size) {
            return new FavBackup[size];
        }
    };

    public ArrayList<Meal> getList() {
        return List;
    }

    public void setList(ArrayList<Meal> list) {
        List = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeTypedList(List);
    }
}
