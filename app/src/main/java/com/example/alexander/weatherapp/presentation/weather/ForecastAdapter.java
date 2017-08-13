package com.example.alexander.weatherapp.presentation.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.network.models.weather.Forecast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<Forecast> forecastList;

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {
        holder.bind(forecastList.get(position));

    }

    @Override
    public int getItemCount() {
        if (forecastList == null) {
            return 0;
        }
        return forecastList.size();
    }

    public void setForecastList(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.forecast_icon)
        ImageView forecastIcon;

        @BindView(R.id.temperature)
        TextView temperature;

        ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Forecast forecast) {

        }
    }
}
