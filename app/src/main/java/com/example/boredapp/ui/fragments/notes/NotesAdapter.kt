package com.example.boredapp.ui.fragments.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.R
import com.example.boredapp.model.NoteModel

class NotesAdapter(list: List<NoteModel>) : RecyclerView.Adapter<NotesViewHolder>() {
    val mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.title.text = mList[position].getTitle()
        holder.info.text = mList[position].getInfo()
    }

    override fun getItemCount(): Int = mList.size
}