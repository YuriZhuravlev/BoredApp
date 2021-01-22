package com.example.boredapp.ui.fragments.notes

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.MainActivity
import com.example.boredapp.R
import com.example.boredapp.model.NoteModel
import com.example.boredapp.ui.fragments.note.NoteFragment

class NotesAdapter(list: List<NoteModel>, menuItem: MenuItem) : RecyclerView.Adapter<NotesViewHolder>() {
    val mList = list
    val mListDeleted = mutableListOf<NoteModel>()
    private val mMenuItem = menuItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.title.text = mList[position].title
        holder.info.text = mList[position].getInfo()
        holder.itemView.setOnClickListener {
            if (mListDeleted.isNotEmpty()) {
                deleteClick(holder, position)
            } else {
                MainActivity.getActivity().replaceFragment(NoteFragment(mList[position]))
            }
        }
        holder.itemView.setOnLongClickListener {
            deleteClick(holder, position)
            return@setOnLongClickListener true
        }
    }

    private fun deleteClick(holder: NotesViewHolder, position: Int) {
        if (holder.checkBox.visibility == View.VISIBLE) {
            holder.checkBox.visibility = View.GONE
            mListDeleted.remove(mList[position])
            listDeletedChecker()
        } else {
            holder.checkBox.visibility = View.VISIBLE
            mListDeleted.add(mList[position])
            listDeletedChecker()
        }
    }

    fun listDeleted() {
        mListDeleted.clear()
        listDeletedChecker()
    }

    private fun listDeletedChecker() {
        mMenuItem.isVisible = mListDeleted.isNotEmpty()
    }

    override fun getItemCount(): Int = mList.size
}