package com.example.boredapp.ui.fragments.notes

import androidx.fragment.app.Fragment
import com.example.boredapp.R

class NotesFragment : Fragment(R.layout.fragment_notes) {

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.notes)
    }
}