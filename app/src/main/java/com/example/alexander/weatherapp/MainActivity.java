package com.example.alexander.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alexander.weatherapp.presentation.about.AboutFragment;
import com.example.alexander.weatherapp.presentation.add_city.AddCityActivity;
import com.example.alexander.weatherapp.presentation.settings.SettingsFragment;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationManager {

    public static final String SELECTED_ITEM = "SELECTED_ITEM";
    public static final String NAVIGATION_BACKPRESS = "NAVIGATION_BACKPRESS";

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDrawer();
        initCityList();

        if (savedInstanceState == null) {
            onDrawerItemClick(R.id.weather);
        } else {
            drawer.setSelection(savedInstanceState.getLong(SELECTED_ITEM));
        }
    }

    private void initDrawer() {
        drawer = new DrawerBuilder()
                .withActivity(this)
                .withHeader(R.layout.layout_drawer_header)
                .withTranslucentStatusBar(true)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    onDrawerItemClick(view.getId());
                    return false;
                })
                .withDrawerItems(customInflateMenu())
                .build();
    }

    private void initCityList() {
        RecyclerView cityList = drawer.getHeader().findViewById(R.id.city_list);
        cityList.setLayoutManager(new LinearLayoutManager(this));
        cityList.setAdapter(new CityWeatherAdapter());
    }

    private List<IDrawerItem> customInflateMenu() {
        MenuInflater menuInflater = new SupportMenuInflater(this);
        MenuBuilder menu = new MenuBuilder(this);
        menuInflater.inflate(R.menu.navigation_menu, menu);
        List<IDrawerItem> result = new ArrayList<>();
        addMenuItems(result, menu);

        return result;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(SELECTED_ITEM, drawer.getCurrentSelection());
    }

    private void onDrawerItemClick(@IdRes int id) {

        Class fragmentClass = null;

        switch (id) {
            case R.id.weather:
                fragmentClass = WeatherFragment.class;
                break;
            case R.id.add_city:
                AddCityActivity.startForResult(this, AddCityActivity.ADD_CITY_REQUEST);
                break;
            case R.id.settings:
                fragmentClass = SettingsFragment.class;
                break;
            case R.id.about:
                fragmentClass = AboutFragment.class;
                break;
        }

        if (fragmentClass == null) {
            return;
        }

        //NPE, if will dummy menu item
        String tag = fragmentClass.getSimpleName();

        FragmentManager fm = getSupportFragmentManager();

        //if this item already selected
        if (fm.findFragmentByTag(tag) != null) {
            return;
        }

        Fragment fragment = Fragment.instantiate(this, fragmentClass.getName());
        fm.beginTransaction()
                .replace(R.id.main_content, fragment, tag)
                .commit();

        drawer.setSelection(id, false);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void addMenuItems(List<IDrawerItem> item, Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mMenuItem = menu.getItem(i);
            IDrawerItem iDrawerItem = new PrimaryDrawerItem()
                    .withName(mMenuItem.getTitle().toString())
                    .withIcon(mMenuItem.getIcon())
                    .withIdentifier(mMenuItem.getItemId())
                    .withEnabled(mMenuItem.isEnabled())
                    .withIconTintingEnabled(true)
                    .withIconColor(ContextCompat.getColor(getBaseContext(), R.color.material_drawer_icon))
                    .withSelectedIconColor(ContextCompat.getColor(getBaseContext(), R.color.material_drawer_selected_icon));
            item.add(iDrawerItem);
        }
    }


    @Override
    public void showNavigationDrawer() {
        drawer.openDrawer();
    }

    @Override
    public void setNavigationDrawerState(boolean enabled) {
        if (enabled) {
            drawer.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            drawer.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void openNavigationDrawer() {
        drawer.openDrawer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AddCityActivity.ADD_CITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                int cityId = data.getIntExtra(AddCityActivity.CITY_ID_KEY, 0);
                Log.d("MyTag", "" + cityId);
            }
        }
    }
}
