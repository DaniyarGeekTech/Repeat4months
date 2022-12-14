package com.example.repeat4months.ui.board

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repeat4months.R
import com.example.repeat4months.databinding.ItemBoardBinding

class BoardAdapter(private val lisner : StartLisner) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    val titleList = listOf("Заметки", "Контакты", "Конец")
    val desList = listOf("Доблавление заметок", "Доступ ко всем контактам")
    val imgList = listOf(R.raw.contact, R.raw.contact,R.raw.contact)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val binding =
        ItemBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoardViewHolder(binding)
    }
    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.inBind(position)
        holder.binding.btnItemStart.setOnClickListener {
            lisner.start()
        }
    }

    override fun getItemCount(): Int = titleList.size

inner class BoardViewHolder ( val binding: ItemBoardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun inBind(position: Int) {
        binding.imgItemBoard.setAnimation(imgList[position])
        binding.tvItemTitleBoard.text = titleList[position]
        binding.tvItemDesBoard.text = desList[position]
        if (position == 2) {
            binding.btnItemStart.visibility = View.VISIBLE
        } else {
            binding.btnItemStart.visibility = View.GONE
        }
    }
}
    interface StartLisner{
        fun start()
    }
}
