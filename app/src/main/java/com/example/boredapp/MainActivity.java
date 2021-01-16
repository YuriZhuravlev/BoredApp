package com.example.boredapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

import com.example.boredapp.ui.fragments.ActivityFragment;
import com.example.boredapp.utils.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity {
    private int mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTheme = SharedPreferenceHelper.getSettingsTheme(this);
        AppCompatDelegate.setDefaultNightMode(mTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ActivityFragment())
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public ActionBar getToolBar() {
        return getSupportActionBar();
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