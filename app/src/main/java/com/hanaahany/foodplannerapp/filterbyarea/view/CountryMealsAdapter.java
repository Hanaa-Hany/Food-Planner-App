package com.hanaahany.foodplannerapp.filterbyarea.view;

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
import com.hanaahany.foodplannerapp.filterbyarea.model.CountryMeals;

import java.util.List;

public class CountryMealsAdapter extends RecyclerView.Adapter<CountryMealsAdapter.ViewHolder> {

    List<CountryMeals>countryMealsList;
    Context context;
    OnItemClicked onItemClicked;

    public CountryMealsAdapter(List<CountryMeals> countryMealsList, Context context,OnItemClicked onItemClicked) {
        this.countryMealsList = countryMealsList;
        this.context = context;
        this.onItemClicked=onItemClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CountryMeals countryMeals=countryMealsList.get(position);
        holder.textViewTitleOfCategory.setText(countryMeals.getName());
        Glide.with(context).load(countryMeals.getImage())
                .into(holder.imageViewCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClicked.sendId(countryMeals.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryMealsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCategory;
        TextView textViewTitleOfCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategory=itemView.findViewById(R.id.image_category_item);
            textViewTitleOfCategory=itemView.findViewById(R.id.title_of_category_item);
        }
    }
}
