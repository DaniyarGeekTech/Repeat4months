package com.example.repeat4months.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteModel (
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val title: String? = null,
    val description: String? = null,
    val date : String)

