package com.example.alexander.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.di.modules.WeatherModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CityWeatherAdapter extends RecyclerView.Adapter<CityWeatherAdapter.CityWeatherViewHolder> {

    @Inject
    Context context;
    @Inject
    WeatherLocalRepository weatherLocalRepository;

    @NonNull
    private final OnCityWeatherClickListener listener;

    private List<? extends CityWeather> cityWeatherList;

    public CityWeatherAdapter(@NonNull OnCityWeatherClickListener listener) {
        this.listener = listener;

        WeatherApplication.getAppComponent().plus(new WeatherModule()).inject(this);

        weatherLocalRepository.getAllCityWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeatherList -> {
                    this.cityWeatherList = cityWeatherList;
                    notifyDataSetChanged();
                });
    }

    @Override
    public CityWeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_drawer_item_primary, parent, false);
        return new CityWeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityWeatherViewHolder holder, int position) {
        holder.bind(cityWeatherList.get(position));
    }

    @Override
    public int getItemCount() {
        if (cityWeatherList == null) {
            return 0;
        }
        return cityWeatherList.size();
    }

    class CityWeatherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.material_drawer_icon)
        ImageView icon;

        @BindView(R.id.material_drawer_name)
        TextView cityName;

        CityWeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.findViewById(R.id.material_drawer_description).setVisibility(View.GONE);
            itemView.findViewById(R.id.material_drawer_badge_container).setVisibility(View.GONE);

            icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_location_city_black_24dp));
            icon.setColorFilter(ContextCompat.getColor(context, R.color.material_drawer_icon));

            cityName.setTextColor(ContextCompat.getColorStateList(context, R.color.material_drawer_primary_text));
        }

        void bind(CityWeather cityWeather) {
            cityName.setText(cityWeather.getCityName());
            itemView.setOnClickListener(view -> listener.onCityWeatherClick(cityWeather.getCityId()));
        }
    }

    interface OnCityWeatherClickListener {

        void onCityWeatherClick(int cityId);
    }
}
