package com.project.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dylogicapps.muslimquranpro.ui.greetingSticker.GreetingStickersActivity;
import com.dylogicapps.muslimquranpro.ui.hadithseacrh.HadithSearchActivity;
import com.dylogicapps.muslimquranpro.ui.hadithsetting.HadithSettingActivity;
import com.dylogicapps.muslimquranpro.ui.qiblaDirection.QiblaDirectionActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @BindView(R.id.title) TextView title;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
                // Passing each menu ID as a set of Ids because each
                // menu should be considered as top level destinations.
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_hadith_collection,
                        R.id.nav_setting)
                        .setDrawerLayout(drawerLayout)
                        .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_qibla_direction:
                    OnClickQiblaDirection();
                    break;
                case R.id.nav_greeting_card:
                    OnClickGreetingCard();
                    break;
            }
            return false;
        });


        try {
            trimCache(this);
            // Toast.makeText(this,"onDestroy " ,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = new File(context.getCacheDir()+"/audio");
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @OnClick(R.id.action_search)
    public void OnClickSearch(){
        startActivity(new Intent(this, HadithSearchActivity.class));
    }

    @OnClick(R.id.action_settings)
    public void OnClickSettings(){
        startActivity(new Intent(this, HadithSettingActivity.class));
    }

    public void setToolbarTitle(String name){
        title.setText(name);
    }

    public void hideToolbarTitle(boolean hide){
        if(hide)
            title.setVisibility(View.GONE);
        else
            title.setVisibility(View.VISIBLE);
    }

    public void setToolbarTitleAlpha(float value){
        title.setAlpha(value);
    }

    public void OnClickQiblaDirection(){
        startActivity(new Intent(this, QiblaDirectionActivity.class));
    }

    public void OnClickGreetingCard(){
        startActivity(new Intent(this, GreetingStickersActivity.class));
    }
}