package com.example.boredapp.ui.fragments.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.R
import com.example.boredapp.model.NoteModel

class NotesFragment : Fragment() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_notes, container, false)
        mRecyclerView = v.findViewById(R.id.notes_recycler_view)
        return v
    }

    override fun onResume() {
        super.onResume()
        activity?.title = getString(R.string.notes)
        mAdapter = NotesAdapter(listOf(NoteModel("Test Model", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit"),
                NoteModel("Small text", "Lorem Ipsum")))
        mRecyclerView.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notes_menu_item_add -> {
                //TODO Add note
            }
        }
        return super.onOptionsItemSelected(item)
    }
}