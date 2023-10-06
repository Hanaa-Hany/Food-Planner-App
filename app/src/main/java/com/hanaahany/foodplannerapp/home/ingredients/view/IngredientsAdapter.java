package com.hanaahany.foodplannerapp.home.ingredients.view;

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
import com.hanaahany.foodplannerapp.model.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    List<Ingredients> ingredientsList;
    Context context;
    OnIngredientCallListener onIngredientCallListener;
    private String [] flags;

    public IngredientsAdapter(List<Ingredients> ingredientsList, Context context, OnIngredientCallListener onIngredientCallListener) {
        this.ingredientsList = ingredientsList;
        this.context = context;
        this.onIngredientCallListener=onIngredientCallListener;
        flags = context.getResources().getStringArray(R.array.flags_ingredient);
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
        Glide.with(context).load(flags[position])
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.circleImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onIngredientCallListener.sendName(ingredients.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewIngredients;
        ImageView circleImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIngredients=itemView.findViewById(R.id.tv_country_item);
            circleImageView=itemView.findViewById(R.id.image_country);


        }
    }
}
