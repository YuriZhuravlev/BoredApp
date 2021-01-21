package com.example.boredapp.ui.fragments.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.boredapp.R
import com.example.boredapp.model.NoteModel

class NoteFragment(note: NoteModel = NoteModel("Test", "TEST")) : Fragment() {
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
//        mTitle.text = mNote.getTitle()
//        mText.setText(mNote.getText())
    }
}