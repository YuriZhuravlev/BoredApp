package com.example.boredapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.boredapp.ui.fragments.ActivityFragment;
import com.example.boredapp.ui.objects.AppDrawer;
import com.example.boredapp.utils.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity {
    private int mTheme;
    private Toolbar mToolBar;
    private AppDrawer mAppDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTheme = SharedPreferenceHelper.getSettingsTheme(this);
        AppCompatDelegate.setDefaultNightMode(mTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolBar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ActivityFragment())
                    .commit();
        }
        mAppDrawer = new AppDrawer(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }

    public void changeTheme() {
        if (mTheme == AppCompatDelegate.MODE_NIGHT_NO) {
            mTheme = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            mTheme = AppCompatDelegate.MODE_NIGHT_NO;
        }
        SharedPreferenceHelper.setSettingsTheme(this, mTheme);
        AppCompatDelegate.setDefaultNightMode(mTheme);
    }
}