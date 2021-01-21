package com.example.boredapp.ui.fragments.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.boredapp.R
import com.example.boredapp.database.Storage
import com.example.boredapp.model.NoteModel
import com.example.boredapp.utils.getTime
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteFragment(note: NoteModel) : Fragment() {
    lateinit var mTitle: TextView
    lateinit var mText: EditText
    val mNote = note

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_note, container, false)
        mTitle = v.findViewById(R.id.note_title)
        mText = v.findViewById(R.id.note_edit_text)
        return v
    }

    override fun onResume() {
        super.onResume()
        mTitle.text = mNote.title
        mText.setText(mNote.text)
    }

    override fun onPause() {
        super.onPause()
        save()
    }

    @SuppressLint("CheckResult")
    private fun save() {
        mNote.text = mText.text.toString()
        mNote.time = getTime()
        Storage.getStorage().notesDao.updateNote(mNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {  }
    }
}