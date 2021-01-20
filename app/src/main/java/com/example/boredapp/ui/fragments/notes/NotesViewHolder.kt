package com.example.boredapp.ui.fragments.notes

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.R

class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.item_note_title)
    val info: TextView = itemView.findViewById(R.id.item_note_info)
}