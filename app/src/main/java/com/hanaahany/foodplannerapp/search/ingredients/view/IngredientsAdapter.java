package com.hanaahany.foodplannerapp.search.ingredients.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.search.ingredients.model.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    List<Ingredients> ingredientsList;
    Context context;

    public IngredientsAdapter(List<Ingredients> ingredientsList, Context context) {
        this.ingredientsList = ingredientsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.country_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Ingredients ingredients=ingredientsList.get(position);
        holder.textViewIngredients.setText(ingredients.getName());
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIngredients;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIngredients=itemView.findViewById(R.id.tv_country_item);
        }
    }
}
