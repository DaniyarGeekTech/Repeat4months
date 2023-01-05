package com.example.repeat4months.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ContactModel (
    @PrimaryKey(autoGenerate = true)
    val name: String,
    val contact: String

)