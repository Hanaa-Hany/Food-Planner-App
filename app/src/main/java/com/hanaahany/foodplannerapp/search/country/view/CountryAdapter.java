package com.hanaahany.foodplannerapp.search.country.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.search.country.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    List<Country>countryList;
    Context context;

    public CountryAdapter(List<Country> countryList, Context context) {
        this.countryList = countryList;
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

        Country country=countryList.get(position);
        holder.textViewName.setText(country.getNameOfCountry());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.tv_country_item);
        }
    }
}
