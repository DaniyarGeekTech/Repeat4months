package com.example.repeat4months.ui.fragment.note

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repeat4months.base.BaseFragment
import com.example.repeat4months.databinding.FragmentNoteBinding


class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {

    private lateinit var adabter: NoteAdabter
    private lateinit var layoutManager: LinearLayoutManager
    override fun setupUI() {
        adabter = NoteAdabter(this)
        binding?.rvNote?.adapter = adabter
    }
}