package com.example.alexander.weatherapp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.alexander.weatherapp.Fragments.AboutFragment;
import com.example.alexander.weatherapp.Fragments.NavigationFragment;
import com.example.alexander.weatherapp.Fragments.SettingsFragment;
import com.example.alexander.weatherapp.Fragments.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.DimenHolder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public final String NAVIGATE_POSITION = "NAVIGATE_POSITION_ID";

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
                        .withHeaderHeight(DimenHolder.fromDp(240))
                        .withTranslucentStatusBar(true)
                        .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            onDrawableItemClick(view.getId());
                            return false;
                        })
                        .withDrawerItems(customInflateMenu())
                        .build();


        initToolbar();

        if(savedInstanceState==null)
            onDrawableItemClick(R.id.weather);
        else {
            navigation.setSelection(savedInstanceState.getInt(NAVIGATE_POSITION), false);
        }

    }

    /**
     * получение пунктов меню из MENU эелментов
     * @return
     */
    private List<IDrawerItem> customInflateMenu() {

        MenuInflater menuInflater = new SupportMenuInflater(this);
        MenuBuilder menu = new MenuBuilder(this);
        menuInflater.inflate(R.menu.navigation_menu, menu);
        List<IDrawerItem> result = new ArrayList<>();
        addMenuItems(result,menu);

        return result;
    }


    /**
     * настраиваю тулбар:
     *  1) иконка навигации
     */
    private void initToolbar(){
        Drawable toolbarNavigationIcon = getBaseContext().getResources().getDrawable(R.drawable.ic_menu_black_24dp);
        toolbarNavigationIcon.setColorFilter(ContextCompat.getColor(getBaseContext(),R.color.normal_text_color_light), PorterDuff.Mode.SRC_IN);
        toolbar.setNavigationIcon(toolbarNavigationIcon);
        toolbar.setContentInsetStartWithNavigation(0);
    }


    /**
     * сохраняю позицию выбранного пункта меню
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(NAVIGATE_POSITION, (int) navigation.getCurrentSelection());
        super.onSaveInstanceState(outState);
    }

    /**
     * предоставляю фрагментам доступ к Toolbar'у
     * @return
     */
    public Toolbar getToolbar(){
        return toolbar;
    }

    /**
     * обработка клика по элементу меню
     * @param id - id элемента меню, по которому произведен клик
     */
    private void onDrawableItemClick(@IdRes int id) {

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

        //подсвечивается выбранный пункт меню
        navigation.setSelection(id, false);

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


    private void addMenuItems(List<IDrawerItem> item, Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mMenuItem = menu.getItem(i);
            IDrawerItem iDrawerItem = new PrimaryDrawerItem()
                        .withName(mMenuItem.getTitle().toString())
                        .withIcon(mMenuItem.getIcon())
                        .withIdentifier(mMenuItem.getItemId())
                        .withEnabled(mMenuItem.isEnabled())
                        .withIconTintingEnabled(true)
                        .withSelectedIconColor(ContextCompat.getColor(getBaseContext(),R.color.material_drawer_selected_icon));
                        //.withDisabledIconColor(ContextCompat.getColor(getBaseContext(),R.color.colorPrimary));
                item.add(iDrawerItem);
        }
    }


}
