package com.example.repeat4months.ui.fragment.note

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repeat4months.R
import com.example.repeat4months.base.BaseFragment
import com.example.repeat4months.databinding.FragmentNoteBinding
import com.example.repeat4months.model.NoteModel
import com.example.repeat4months.ui.fragment.App

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate),NoteAdapter.NoteClickInterface {
    private lateinit var adapter: NoteAdapter
    override fun setupUI() {
        adapter = NoteAdapter(this)
        binding?.rvNote?.adapter = adapter
        adapter.addNote(App.db.noteDao()?.getAllNote()as ArrayList<NoteModel>)
    }

    override fun setupObserver() {
        super.setupObserver()
        deleteNote()
        binding!!.btnAddNote.setOnClickListener{
            controller.navigate(R.id.addNoteFragment2)
        }
    }

    private fun deleteNote() {
        val simpleCallback = object :
            ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper
                    .RIGHT or ItemTouchHelper.LEFT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Удалить заметку?")
                    .setNegativeButton("Нет") { _: DialogInterface?, _: Int ->
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .setPositiveButton("Да") { _: DialogInterface?, _: Int ->
                        adapter.deleteNote(viewHolder.adapterPosition)
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .show()
            }
        }
            val itemTouchHelper = ItemTouchHelper(simpleCallback)
            itemTouchHelper.attachToRecyclerView(binding?.rvNote)
        }

    override fun noteClick(model: NoteModel) {
        val bundle = Bundle()
        bundle.putString("title",model.title)
        bundle.putString("desc",model.description)
        model.id?.let { bundle.putInt("id",it) }
        controller.navigate(R.id.addNoteFragment2,bundle)
    }
}



