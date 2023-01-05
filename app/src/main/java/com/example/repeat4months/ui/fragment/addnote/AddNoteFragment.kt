package com.example.repeat4months.ui.fragment.addnote

import com.example.repeat4months.base.BaseFragment
import com.example.repeat4months.databinding.FragmentAddNoteBinding
import com.example.repeat4months.model.NoteModel
import com.example.repeat4months.ui.fragment.App
import java.text.SimpleDateFormat
import java.util.*

class  AddNoteFragment : BaseFragment<FragmentAddNoteBinding>(FragmentAddNoteBinding::inflate) {

    override fun setupUI() {
        if (arguments != null) {
            val title = arguments?.getString("title")
            val desc = arguments?.getString("desc")
            val id = arguments?.getInt("id")
            val date = getCurrentDataTime()
            val dateInString = date.toString("yyyy/MM/dd")
            binding?.etTitle?.setText(title.toString())
            binding?.etDes?.setText(desc.toString())
            binding?.btnSaveNote?.setOnClickListener {
                App.db.noteDao()?.upDateNote(
                    NoteModel(
                        id,
                        binding?.etTitle?.text.toString(),
                        binding?.etDes?.text.toString(),
                        date = dateInString
                    )
                )

                controller.navigateUp()
            }
        } else {
            binding?.btnSaveNote?.setOnClickListener {
                controller.navigateUp()
                val title = binding!!.etTitle.text.toString()
                val description = binding!!.etDes.text.toString()
                val date = getCurrentDataTime()
                val dateInString = date.toString("yyyy/MM/dd")
                App.db.noteDao()
                    ?.addNote(NoteModel(title = title, description = description, date = dateInString))
            }
        }
    }
}
    private fun Date.toString(format: String, locale:Locale = Locale.getDefault()): String{
        val formatter = SimpleDateFormat(format,locale)
        return formatter.format(this)
    }
    private fun getCurrentDataTime():Date{
        return Calendar.getInstance().time
    }
