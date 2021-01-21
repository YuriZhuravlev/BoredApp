package com.example.boredapp.ui.fragments.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.MainActivity
import com.example.boredapp.R
import com.example.boredapp.model.NoteModel
import com.example.boredapp.ui.fragments.note.NoteFragment

class NotesAdapter(list: List<NoteModel>) : RecyclerView.Adapter<NotesViewHolder>() {
    val mList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.title.text = mList[position].title
        holder.info.text = mList[position].getInfo()
        holder.itemView.setOnClickListener {
            MainActivity.getActivity().replaceFragment(NoteFragment(mList[position]))
        }
    }

    override fun getItemCount(): Int = mList.size
}