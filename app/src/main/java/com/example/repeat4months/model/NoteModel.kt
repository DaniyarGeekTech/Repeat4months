package com.example.repeat4months.model

import android.provider.ContactsContract.Contacts.Data


data class NoteModel (
    val id:Int? = null,
    val title: String? = null,
    val description: String? = null,
    val data : String
)
