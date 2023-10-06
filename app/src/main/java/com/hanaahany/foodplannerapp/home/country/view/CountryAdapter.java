package com.hanaahany.foodplannerapp.home.country.view;

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
import com.hanaahany.foodplannerapp.home.country.model.Country;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    List<Country>countryList;
    Context context;
    OnClickCountryInterface onClickCountryInterface;
    private String [] flags;

    public CountryAdapter(List<Country> countryList, Context context, OnClickCountryInterface onClickCountryInterface) {
        this.countryList = countryList;
        this.context = context;
        this.onClickCountryInterface=onClickCountryInterface;
        flags = context.getResources().getStringArray(R.array.flags);
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
        Glide.with(context).load(flags[position])
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.circleImageView);
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
        ImageView circleImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.tv_country_item);
            circleImageView=itemView.findViewById(R.id.image_country);
        }
    }
}
