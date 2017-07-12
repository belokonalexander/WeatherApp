package com.example.alexander.weatherapp;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.alexander.weatherapp.presentation.about.AboutFragment;
import com.example.alexander.weatherapp.presentation.NavigationFragment;
import com.example.alexander.weatherapp.presentation.settings.SettingsFragment;
import com.example.alexander.weatherapp.presentation.weather.WeatherFragment;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.DimenHolder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 *  Главное активити - отвечает за навигацию и реализацию паттерна Navigation Drawer
 *  Не стал делать в стиле MVP потому что:
 *  1) Здесь есть только навигация
 *  2) Не вижу смысла бросать событие по клику на пункте меню в презентер, а оттуда возвращать то же самое во вью -
 *     проще сразу обработать все во вью без посредников
 *  3) Все процессы будут происходить во фрагментах, где уже и будет презентер
 */


public class MainActivity extends AppCompatActivity {

    public static final String NAVIGATE_POSITION = "NAVIGATE_POSITION_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //меню навигации
    private Drawer navigation;


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
                            onDrawerItemClick(view.getId());
                            return false;
                        })
                        .withDrawerItems(customInflateMenu())
                        .build();


        initToolbar();

        if(savedInstanceState==null) {
            onDrawerItemClick(R.id.settings);
        } else {
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
        super.onSaveInstanceState(outState);
        outState.putInt(NAVIGATE_POSITION, (int) navigation.getCurrentSelection());
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
    private void onDrawerItemClick(@IdRes int id) {

        Class fragmentClass = null;

            switch (id){
                case R.id.weather:
                    fragmentClass = WeatherFragment.class;
                    break;
                case R.id.settings:
                    fragmentClass = SettingsFragment.class;
                    break;
                case R.id.about:
                    fragmentClass = AboutFragment.class;
                    break;
            }

        //NPE, если забуду описать пункт меню
        String tag = fragmentClass.getSimpleName();

        FragmentManager fm = getSupportFragmentManager();

        //если такой пункт меню открыт в данный момент - игнорирую нажатие
        if(fm.findFragmentByTag(tag)!=null){
            return;
        }

        Fragment fragment = Fragment.instantiate(this,fragmentClass.getName());
        fm.beginTransaction()
                .replace(R.id.main_content,fragment,tag)
                .commit();


        //подсвечивается выбранный пункт меню
        navigation.setSelection(id, false);

    }



    @Override
    protected void onPause() {
        super.onPause();

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

    /**
     * конвертирую items в menu в IDrawerItem
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
                        .withSelectedIconColor(ContextCompat.getColor(getBaseContext(),R.color.material_drawer_selected_icon));
                item.add(iDrawerItem);
        }
    }


}
