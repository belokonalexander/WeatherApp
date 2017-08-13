package com.example.alexander.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.alexander.weatherapp.data.local.WeatherLocalRepository;
import com.example.alexander.weatherapp.data.local.model.CityWeather;
import com.example.alexander.weatherapp.di.modules.WeatherModule;
import com.example.alexander.weatherapp.presentation.add_city.AddCityActivity;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NavigationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    WeatherLocalRepository weatherLocalRepository;

    private Drawer drawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        ButterKnife.bind(this);
        WeatherApplication.getAppComponent().plus(new WeatherModule()).inject(this);

        setSupportActionBar(toolbar);

        initDrawer();
        initFooter();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AddCityActivity.ADD_CITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                int cityId = data.getIntExtra(AddCityActivity.CITY_ID_KEY, 0);
                showWeatherByCityId(cityId);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void initDrawer() {
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    drawer.closeDrawer();
                    int cityId = (int) drawerItem.getIdentifier();
                    showWeatherByCityId(cityId);
                    return true;
                })
                .withStickyFooter(R.layout.layout_navigation_footer)
                .withStickyFooterShadow(false)
                .build();

        weatherLocalRepository.getAllCityWeather()
                .map(this::getDrawerItems)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(drawer::setItems);
    }

    private void initFooter() {
        NavigationView navigationView = (NavigationView) drawer.getStickyFooter();

        navigationView.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawer();
            switch (item.getItemId()) {
                case R.id.add_city:
                    AddCityActivity.startForResult(this, AddCityActivity.ADD_CITY_REQUEST);
                    break;

                case R.id.settings:
                    break;

                case R.id.about:
                    break;
            }
            return false;
        });
    }

    private List<IDrawerItem> getDrawerItems(List<? extends CityWeather> cityWeatherList) {
        List<IDrawerItem> drawerItems = new ArrayList<>();
        for (CityWeather cityWeather : cityWeatherList) {
            IDrawerItem drawerItem = new PrimaryDrawerItem()
                    .withName(cityWeather.getCityName())
                    .withIdentifier(cityWeather.getCityId())
                    .withIcon(ContextCompat.getDrawable(this, R.drawable.ic_location_city_black_24dp));
            drawerItems.add(drawerItem);
        }
        return drawerItems;
    }

    private void showWeatherByCityId(int cityId) {
        String tag = String.valueOf(cityId);
        FragmentManager fm = getSupportFragmentManager();

        if (fm.findFragmentByTag(tag) != null) {
            return;
        }

        Fragment fragment = WeatherFragment.newInstance(cityId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_content, fragment, tag)
                .commit();
    }
}
