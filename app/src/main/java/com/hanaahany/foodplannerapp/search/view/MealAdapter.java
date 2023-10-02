package com.hanaahany.foodplannerapp.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    List<Meal>arrayList;
    Context context;

    public MealAdapter(List<Meal> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal=arrayList.get(position);
        holder.textViewTitleOfName.setText(meal.getNameOfMeal());
        Glide.with(context).load(meal.getImage())
                .into(holder.imageViewMeal);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMeal;
        TextView textViewTitleOfName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMeal=itemView.findViewById(R.id.image_category_item);
            textViewTitleOfName=itemView.findViewById(R.id.title_of_category_item);
        }
    }
}
