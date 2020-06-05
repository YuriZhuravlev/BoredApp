package com.example.boredapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

public class SharedPreferenceHelper {
    public static final String SETTINGS_KEY = "SETTINGS_KEY";
    public static final String SETTINGS_ITEM_THEME = "SETTINGS_THEME";
    public static final String SETTINGS_ITEM_SEARCH = "SETTINGS_SEARCH";

    public static int getSettingsTheme(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SharedPreferenceHelper.SETTINGS_KEY, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(SETTINGS_ITEM_THEME, AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void setSettingsTheme(Activity activity, int theme) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(SharedPreferenceHelper.SETTINGS_KEY, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(SETTINGS_ITEM_THEME, theme).apply();
    }
}
