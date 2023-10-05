package com.hanaahany.foodplannerapp.plan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.model.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    Context context;
    List<Meal> list;
    OnCancelClick onCancelClick;

    public PlanAdapter(Context context, List<Meal> list,OnCancelClick onCancelClick) {
        this.context = context;
        this.list = list;
        this.onCancelClick=onCancelClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.day_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal=list.get(position);
        holder.textViewTitle.setText(meal.getNameOfMeal());
        holder.textViewCategory.setText(meal.getCategory());
        Picasso.get().load(meal.getImage()).into(holder.imageViewMeal);
        holder.imageViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCancelClick.deleteMeal(meal);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMeal,imageViewCancel;
        TextView textViewTitle,textViewCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMeal=itemView.findViewById(R.id.image_day_item);
            imageViewCancel=itemView.findViewById(R.id.cancel_icon);
            textViewTitle=itemView.findViewById(R.id.title_of_day_item);
            textViewCategory=itemView.findViewById(R.id.category_of_day_item);
        }
    }
}
