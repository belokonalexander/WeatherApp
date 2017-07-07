package com.example.alexander.weatherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.alexander.weatherapp.Fragments.AboutFragment;
import com.example.alexander.weatherapp.Fragments.NavigationFragment;
import com.example.alexander.weatherapp.Fragments.SettingsFragment;
import com.example.alexander.weatherapp.Fragments.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer navigation;

    SparseArray<NavigationFragment> fragmentStore = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        
        navigation = new DrawerBuilder().withActivity(this)
                        .withToolbar(toolbar)
                        .withHeader(R.layout.navigation_drawable_header)
                        .withTranslucentStatusBar(true)
                        .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            onDrawableItemClick(view);
                            return false;
                        })
                        .inflateMenu(R.menu.navigation_menu)
                        .build();

    }


    private void onDrawableItemClick(View view) {

        int id = view.getId();

        NavigationFragment fragment = fragmentStore.get(id);

        if(fragment==null)
            switch (id){
                case R.id.weather:
                    fragment = new WeatherFragment();
                    fragmentStore.put(id,fragment);
                    break;
                case R.id.settings:
                    fragment = new SettingsFragment();
                    fragmentStore.put(id,fragment);
                    break;
                case R.id.about:
                    fragment = new AboutFragment();
                    fragmentStore.put(id,fragment);
                    break;
            }


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_content, fragment, null);
        ft.commit();


    }



    @Override
    public void onBackPressed(){
        if(navigation.isDrawerOpen()){
            navigation.closeDrawer();
        }
        else{
            super.onBackPressed();
        }
    }
}
