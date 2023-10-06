package com.hanaahany.foodplannerapp.favmeal.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.home.category.model.Category;
import com.hanaahany.foodplannerapp.home.category.view.CategoryAdapter;
import com.hanaahany.foodplannerapp.model.Meal;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    List<Meal> mealList;
    Context context;
    OnFavClick onFavClick;
    private static final String TAG = "FavAdapter";

    public FavAdapter(List<Meal> mealList, Context context,OnFavClick onFavClick) {
        this.mealList = mealList;
        this.context = context;
        this.onFavClick=onFavClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fav_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal=mealList.get(position);
        holder.textViewTitleOfCategory.setText(meal.getNameOfMeal());
        Glide.with(context).load(meal.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageViewCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: "+meal.getId());
                onFavClick.getMealDetails(meal.getId());
              //  onCategoryCallListener.sendNameOfCategory(category.getCategoryName());
            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavClick.deleteMeal(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCategory,imageViewDelete;
        TextView textViewTitleOfCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategory=itemView.findViewById(R.id.image_fav_item);
            textViewTitleOfCategory=itemView.findViewById(R.id.title_of_fav_item);
            imageViewDelete=itemView.findViewById(R.id.delete);
        }
    }

}
