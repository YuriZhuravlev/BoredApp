package com.example.boredapp.database.dao

import androidx.room.*
import com.example.boredapp.model.NoteModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteModel): Completable

    @Update
    fun updateNote(note: NoteModel): Completable

    @Delete
    fun deleteNote(note: NoteModel): Completable

    @Query("SELECT * FROM note WHERE id == :id")
    fun getNoteById(id: Int): Flowable<List<NoteModel>>

    @Query("SELECT * FROM note ORDER BY note.time DESC")
    fun getNotes(): Flowable<List<NoteModel>>
}