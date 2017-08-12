package com.example.alexander.weatherapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alexander.weatherapp.presentation.about.AboutFragment;
import com.example.alexander.weatherapp.presentation.add_city.AddCityActivity;
import com.example.alexander.weatherapp.presentation.settings.SettingsFragment;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.DimenHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationManager {

    public static final String NAVIGATE_POSITION = "NAVIGATE_POSITION_ID";
    public static final String NAVIGATION_BACKPRESS = "NAVIGATION_BACKPRESS";

    private Drawer navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation = new DrawerBuilder().withActivity(this)

                .withHeader(R.layout.navigation_drawable_header)
                .withHeaderHeight(DimenHolder.fromDp(240))
                .withTranslucentStatusBar(true)
                .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                    onDrawerItemClick(view.getId());
                    return false;
                })
                .withDrawerItems(customInflateMenu())
                .build();


        if (savedInstanceState == null) {
            onDrawerItemClick(R.id.weather);
        } else {
            navigation.setSelection(savedInstanceState.getInt(NAVIGATE_POSITION), false);
        }

    }

    /**
     * gets menu items from menu.xml
     *
     * @return
     */
    private List<IDrawerItem> customInflateMenu() {

        MenuInflater menuInflater = new SupportMenuInflater(this);
        MenuBuilder menu = new MenuBuilder(this);
        menuInflater.inflate(R.menu.navigation_menu, menu);
        List<IDrawerItem> result = new ArrayList<>();
        addMenuItems(result, menu);

        return result;
    }


    /**
     * saving current select position
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NAVIGATE_POSITION, (int) navigation.getCurrentSelection());
    }


    /**
     * item click handling
     *
     * @param id - id of selected item
     */
    private void onDrawerItemClick(@IdRes int id) {

        Class fragmentClass = null;

        switch (id) {
            case R.id.weather:
                fragmentClass = WeatherFragment.class;
                break;
            case R.id.add_city:
                AddCityActivity.start(this);
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

        navigation.setSelection(id, false);
    }

    @Override
    public void onBackPressed() {
        if (navigation.isDrawerOpen()) {
            navigation.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * convert items menu in IDrawerItem
     *
     * @param item
     * @param menu
     */
    private void addMenuItems(List<IDrawerItem> item, Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mMenuItem = menu.getItem(i);
            IDrawerItem iDrawerItem = new PrimaryDrawerItem()
                    .withName(mMenuItem.getTitle().toString())
                    .withIcon(mMenuItem.getIcon())
                    .withIdentifier(mMenuItem.getItemId())
                    .withEnabled(mMenuItem.isEnabled())
                    .withIconTintingEnabled(true)
                    .withSelectedIconColor(ContextCompat.getColor(getBaseContext(), R.color.material_drawer_selected_icon));
            item.add(iDrawerItem);
        }
    }


    @Override
    public void showNavigationDrawer() {
        navigation.openDrawer();
    }

    @Override
    public void setNavigationDrawerState(boolean enabled) {
        if (enabled) {
            navigation.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            navigation.getDrawerLayout().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    public void openNavigationDrawer() {
        navigation.openDrawer();
    }
}
