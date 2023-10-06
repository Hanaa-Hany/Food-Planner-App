package com.hanaahany.foodplannerapp.mealdetails.view;

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
import com.hanaahany.foodplannerapp.model.IngredientPojo;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    Context context;
    ArrayList<IngredientPojo> list;

    public IngredientAdapter(Context context, ArrayList<IngredientPojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IngredientPojo ingredients = list.get(position);
        holder.textViewMeasure.setText(ingredients.getIngredientMeasure());
        holder.textViewIngredient.setText(ingredients.getIngredientName());
        Glide.with(context).load(ingredients.getIngredientThumb())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageView);

    }
//    public void setList(ArrayList<IngredientPojo> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMeasure, textViewIngredient;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMeasure = itemView.findViewById(R.id.tv_measure_ingredient);
            textViewIngredient = itemView.findViewById(R.id.tv_name_ingredient);
            imageView = itemView.findViewById(R.id.image_measure_ingredient);
        }
    }
}
