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
import com.example.boredapp.utils.openWeb
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class NoteFragment(note: NoteModel, isCreated: Boolean = false) : Fragment() {
    lateinit var mTitle: TextView
    lateinit var mText: EditText
    lateinit var mBtnLink: ExtendedFloatingActionButton
    val mNote = note
    var mIsCreated = isCreated

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_note, container, false)
        mTitle = v.findViewById(R.id.note_title)
        mText = v.findViewById(R.id.note_edit_text)
        mBtnLink = v.findViewById(R.id.note_btn_link)
        return v
    }

    override fun onResume() {
        super.onResume()
        if (mIsCreated) {
            mTitle.requestFocus()
        }
        if (mNote.link.isNotEmpty()) {
            mBtnLink.visibility = View.VISIBLE
            mBtnLink.setOnClickListener { openWeb(mNote.link) }
        }
        mTitle.text = mNote.title
        mText.setText(mNote.text)
    }

    override fun onPause() {
        super.onPause()
        save()
    }

    @SuppressLint("CheckResult")
    private fun save() {
        if (mTitle.text.isNotEmpty()) {
            val title = mTitle.text.toString()
            val text = mText.text.toString()
            if (!mIsCreated && mNote.title.equals(title) && mNote.text.equals(text)) {
                return
            }
            mNote.title = title
            mNote.text = text
            mNote.time = getTime()
            if (mIsCreated) {
                mIsCreated = false
                Storage.getStorage().insertNote(mNote) {}
            } else {
                Storage.getStorage().updateNote(mNote) {}
            }
        }
    }
}