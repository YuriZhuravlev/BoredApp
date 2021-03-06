package com.example.boredapp.ui.fragments.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.MainActivity
import com.example.boredapp.R
import com.example.boredapp.database.Storage
import com.example.boredapp.model.NoteModel
import com.example.boredapp.ui.fragments.note.NoteFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class NotesFragment : Fragment() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: NotesAdapter
    private lateinit var mMenuDelete: MenuItem

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
        initRecyclerView()
    }

    @SuppressLint("CheckResult")
    private fun initRecyclerView() {
        val storage = Storage.getStorage()

        storage.getNotes {
            mAdapter = NotesAdapter(it, mMenuDelete)
            mRecyclerView.adapter = mAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_menu, menu)
        mMenuDelete = menu.findItem(R.id.notes_menu_item_delete)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.notes_menu_item_delete -> {
                Storage.getStorage().deleteNotes(mAdapter.mListDeleted) { mAdapter.listDeleted() }
            }
            R.id.notes_menu_item_add -> {
                MainActivity.getActivity().replaceFragment(NoteFragment(NoteModel(), true))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}