package com.example.boredapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.boredapp.ui.fragments.ActivityFragment;
import com.example.boredapp.utils.SharedPreferenceHelper;

public class MainActivity extends AppCompatActivity {
    int mTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mTheme = SharedPreferenceHelper.getSettingsTheme(this);
        AppCompatDelegate.setDefaultNightMode(mTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,new ActivityFragment())
                    .addToBackStack("")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_theme_change) {
            if (mTheme == AppCompatDelegate.MODE_NIGHT_NO) {
                mTheme = AppCompatDelegate.MODE_NIGHT_YES;
            } else {
                mTheme = AppCompatDelegate.MODE_NIGHT_NO;
            }
            SharedPreferenceHelper.setSettingsTheme(this, mTheme);
            AppCompatDelegate.setDefaultNightMode(mTheme);
        }
        return super.onOptionsItemSelected(item);
    }
}