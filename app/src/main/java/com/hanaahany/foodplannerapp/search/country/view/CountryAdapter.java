package com.hanaahany.foodplannerapp.search.country.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.hanaahany.foodplannerapp.HomeActivity;
import com.hanaahany.foodplannerapp.R;
import com.hanaahany.foodplannerapp.search.country.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    List<Country>countryList;
    Context context;
    OnClickCountryInterface onClickCountryInterface;

    public CountryAdapter(List<Country> countryList, Context context, OnClickCountryInterface onClickCountryInterface) {
        this.countryList = countryList;
        this.context = context;
        this.onClickCountryInterface=onClickCountryInterface;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickCountryInterface.onItemClicked(country.getNameOfCountry());
//                NavController navController=Navigation.findNavController((Activity) context,R.id.nav_host_fragment_home);
//                navController.navigate(R.id.action_searchFragment_to_countryMealsFragment);
            }
        });
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
