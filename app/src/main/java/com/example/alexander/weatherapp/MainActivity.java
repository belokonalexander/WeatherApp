package com.example.alexander.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Drawer navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation = new DrawerBuilder().withActivity(this)
                        .withToolbar(toolbar)
                        .withHeader(R.layout.navigation_drawable_header)
                        .withTranslucentStatusBar(true)
                        .addDrawerItems(getMenuItems())
                        .withOnDrawerItemClickListener((view, position, drawerItem) -> {
                            onDrawableItemClick(position);
                            return false;
                        })
                        .build();
    }

    private IDrawerItem[] getMenuItems(){
        String[] names = getBaseContext().getResources().getStringArray(R.array.navigation_items);
        List<IDrawerItem> items = new ArrayList<>();
        for(int i =0; i < names.length; i++){
            items.add(new PrimaryDrawerItem().withIdentifier(i).withName(names[i]));
        }
        return items.toArray(new IDrawerItem[items.size()]);
    }

    private void onDrawableItemClick(int position) {
        LogUtils.write(position);
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
