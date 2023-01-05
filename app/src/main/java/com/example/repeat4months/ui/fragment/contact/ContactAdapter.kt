package com.example.repeat4months.ui.fragment.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.repeat4months.databinding.ItemContactBinding
import com.example.repeat4months.model.ContactModel

class ContactAdapter(private val listener: ShareListener) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var list = listOf<ContactModel>()

    fun setList(list: List<ContactModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(list[position])
        holder.binding.btnShare.setOnClickListener {
            listener.share(list[position].contact,true)
        }
        holder.binding.btnShareWatsApp.setOnClickListener {
            listener.share(list[position].contact,false)
        }
    }

    override fun getItemCount() = list.size

    class ContactViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(model: ContactModel) {
            binding.itemTvName.text = model.name
            binding.itemTvNum.text = model.contact
        }

    }

    interface ShareListener {
        fun share(number: String, shareSwitch: Boolean)
    }

}