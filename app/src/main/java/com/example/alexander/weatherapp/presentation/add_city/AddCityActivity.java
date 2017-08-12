package com.example.alexander.weatherapp.presentation.add_city;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.alexander.weatherapp.R;
import com.example.alexander.weatherapp.WeatherApplication;
import com.example.alexander.weatherapp.data.network.models.places.Prediction;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddCityActivity extends MvpAppCompatActivity implements AddCityView, MaterialSearchBar.OnSearchActionListener, AddCitySuggestionsAdapter.OnPredictionClickListener {

    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;
    @BindView(com.mancj.materialsearchbar.R.id.mt_editText)
    EditText searchEdit;

    @InjectPresenter
    AddCityPresenter presenter;

    @ProvidePresenter
    AddCityPresenter provideAddCityPresenter() {
        return WeatherApplication
                .getAppComponent()
                .plus(new WeatherModule())
                .provideAddCityPresenter();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, AddCityActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        ButterKnife.bind(this);

        initSearchBar();
    }

    private void initSearchBar() {
        searchBar.setOnSearchActionListener(this);
        searchBar.setCustomSuggestionAdapter(new AddCitySuggestionsAdapter(this));

        RxTextView.textChanges(searchEdit)
                .map(CharSequence::toString)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(presenter::getAutocomplete);
    }

    @Override
    public void onButtonClicked(int buttonCode) {
        onBackPressed();
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        // empty
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {
        // empty
    }

    @Override
    public void updatePredictions(List<Prediction> predictions) {
        searchBar.updateLastSuggestions(predictions);
    }

    @Override
    public void onPredictionClick(Prediction prediction) {
        searchBar.clearSuggestions();
    }
}
