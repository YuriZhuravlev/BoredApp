package com.example.boredapp.database

import android.content.Context
import androidx.room.Room
import com.example.boredapp.MainActivity
import com.example.boredapp.database.dao.NotesDao

class Storage(val database: AppDatabase) {
    init {
        instance = this
    }
    val notesDao: NotesDao = database.notesDao()

    companion object {
        private var instance: Storage? = null
        fun getStorage(context: Context = MainActivity.getActivity().applicationContext): Storage {
            if (instance == null) {
                val db = Room.databaseBuilder(context,
                        AppDatabase::class.java, "bored")
                        .build()
                return Storage(db)
            }
            return instance!!
        }
    }
}