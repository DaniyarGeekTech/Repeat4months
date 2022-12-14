package com.example.repeat4months.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repeat4months.databinding.ItemNoteBinding
import com.example.repeat4months.model.NoteModel

class NoteAdabter(noteFragment: NoteFragment) : RecyclerView.Adapter<NoteAdabter.NoteViewHolder>() {

    private var list: ArrayList<NoteModel> = arrayListOf()
    @SuppressLint("NotifyDataSetChanged")
    fun addNote(list: ArrayList<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }
    override fun getItemCount(): Int = list.size
    inner class NoteViewHolder(private val binding:ItemNoteBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: NoteModel) {
            binding.itemTvTiyle.text = model.title
            binding.itemTvDes.text = model.description
            binding.itemTvData.text = model.data
        }
    }
}