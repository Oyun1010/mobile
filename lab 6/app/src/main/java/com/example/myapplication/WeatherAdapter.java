package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private LinkedList<WeatherList> weatherLists;
    private final LayoutInflater inflater;

    public WeatherAdapter(@NonNull Context context, @NonNull LinkedList<WeatherList> weatherLists) {
        inflater = LayoutInflater.from(context);
        this.weatherLists = weatherLists;
    }

    @NonNull
    @Override
    public WeatherAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.item, parent, false);
        return new WeatherViewHolder(mItemView, this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.WeatherViewHolder holder, int position) {
        holder.tmp.setText(String.valueOf(weatherLists.get(position).getTmp() - 273.15).substring(0, 4));
        holder.desc.setText(weatherLists.get(position).getDesc());
        holder.dateTime.setText(weatherLists.get(position).getDateTime());

    }

    @Override
    public int getItemCount() {
        return weatherLists.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        public final TextView tmp;
        public final TextView desc;
        public final TextView dateTime;
        final WeatherAdapter mAdapter;

        public WeatherViewHolder(@NonNull View itemView, WeatherAdapter weatherAdapter) {
            super(itemView);
            tmp = itemView.findViewById(R.id.tmp);
            desc = itemView.findViewById(R.id.desc);
            dateTime = itemView.findViewById(R.id.dateTime);
            this.mAdapter = weatherAdapter;

        }
    }
}
