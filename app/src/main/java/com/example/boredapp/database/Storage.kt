package com.example.boredapp.database

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Room
import com.example.boredapp.MainActivity
import com.example.boredapp.database.dao.NotesDao
import com.example.boredapp.model.NoteModel
import com.example.boredapp.ui.fragments.notes.NotesAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Storage(val database: AppDatabase) {
    init {
        instance = this
    }

    val notesDao: NotesDao = database.notesDao()

    @SuppressLint("CheckResult")
    fun insertNote(note: NoteModel, function: () -> Unit) {
        notesDao.insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { function() }
    }

    @SuppressLint("CheckResult")
    fun updateNote(note: NoteModel, function: () -> Unit) {
        notesDao.updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { function() }
    }

    @SuppressLint("CheckResult")
    fun getNotes(function: (List<NoteModel>) -> Unit) {
        notesDao.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { function(it) }
    }

    fun deleteNotes(list: List<NoteModel>, function: () -> Unit) {
        list.forEach {
            notesDao.deleteNote(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { function() }
        }
    }

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