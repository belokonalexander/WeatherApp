package com.example.alexander.weatherapp.presentation.add_city;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.EditText;
import android.widget.Toast;

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
import io.reactivex.disposables.CompositeDisposable;

public class AddCityActivity extends MvpAppCompatActivity implements AddCityView, MaterialSearchBar.OnSearchActionListener, AddCitySuggestionsAdapter.OnPredictionClickListener {

    public static int ADD_CITY_REQUEST = 0;

    public static String CITY_ID_KEY = "CITY_ID_KEY";

    @BindView(R.id.search_bar)
    MaterialSearchBar searchBar;
    @BindView(com.mancj.materialsearchbar.R.id.mt_editText)
    EditText searchEdit;

    @InjectPresenter
    AddCityPresenter presenter;

    private CompositeDisposable disposables;

    public static void startForResult(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, AddCityActivity.class), requestCode);
    }

    @ProvidePresenter
    AddCityPresenter provideAddCityPresenter() {
        return WeatherApplication
                .getAppComponent()
                .plus(new WeatherModule())
                .provideAddCityPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        ButterKnife.bind(this);

        disposables = new CompositeDisposable();

        initSearchBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    private void initSearchBar() {
        searchBar.setOnSearchActionListener(this);
        searchBar.setCustomSuggestionAdapter(new AddCitySuggestionsAdapter(this));

        disposables.add(RxTextView
                .textChanges(searchEdit)
                .map(CharSequence::toString)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(presenter::getAutocomplete));
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
        presenter.savePlace(prediction);
        searchBar.clearSuggestions();
    }

    @Override
    public void showError(@StringRes int messageId) {
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCitySaved(int cityId) {
        setResult(RESULT_OK, new Intent().putExtra(CITY_ID_KEY, cityId));
        finish();
    }
}
