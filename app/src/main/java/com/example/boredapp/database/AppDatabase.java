package com.example.boredapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.boredapp.database.dao.NotesDao;
import com.example.boredapp.model.NoteModel;

@Database(entities = {NoteModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
}
