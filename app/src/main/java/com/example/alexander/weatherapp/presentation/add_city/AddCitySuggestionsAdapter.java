package com.example.alexander.weatherapp.presentation.add_city;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCitySuggestionsAdapter extends SuggestionsAdapter<Prediction, AddCitySuggestionsAdapter.CitySuggestionViewHolder> {

    @NonNull
    private final OnPredictionClickListener listener;

    public AddCitySuggestionsAdapter(@NonNull OnPredictionClickListener listener) {
        super(null);
        this.listener = listener;
    }

    @Override
    public void onBindSuggestionHolder(Prediction prediction, CitySuggestionViewHolder holder, int position) {
        holder.bind(prediction);
    }

    @Override
    public int getSingleViewHeight() {
        return 48;
    }

    @Override
    public CitySuggestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_suggestion, parent, false);
        return new CitySuggestionViewHolder(view);
    }

    class CitySuggestionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.suggestion)
        TextView suggestion;

        CitySuggestionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Prediction prediction) {
            suggestion.setText(prediction.getDescription());
            itemView.setOnClickListener(view -> listener.onPredictionClick(prediction));
        }
    }

    interface OnPredictionClickListener {

        void onPredictionClick(Prediction Prediction);
    }
}
