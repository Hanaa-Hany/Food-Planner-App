package com.hanaahany.foodplannerapp.home.category.view;

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
import com.hanaahany.foodplannerapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<Category>arrayList;
    OnCategoryCallListener onCategoryCallListener;
    private String [] flags;

    public CategoryAdapter(Context context, List<Category> arrayList,OnCategoryCallListener onCategoryCallListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.onCategoryCallListener=onCategoryCallListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category=arrayList.get(position);
        holder.textViewTitleOfCategory.setText(category.getCategoryName());
        Glide.with(context).load(category.getCategoryImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imageViewCategory);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCategoryCallListener.sendNameOfCategory(category.getCategoryName());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
