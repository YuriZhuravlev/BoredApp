package com.example.boredapp;

import android.app.Application;

import androidx.room.Room;

import com.example.boredapp.database.AppDatabase;
import com.example.boredapp.database.Storage;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Storage.Companion.getStorage(this);
    }
}
