package com.example.repeat4months.ui.fragment

import android.app.Application
import android.content.SharedPreferences
import com.example.repeat4months.data.NoteDatabase
import com.example.repeat4months.ui.fragment.utils.Prefs

class App : Application() {
    private lateinit var preferences: SharedPreferences

    companion object {
        lateinit var db:NoteDatabase
        lateinit var prefs: Prefs
    }
    override fun onCreate(){
        super.onCreate()
        db = NoteDatabase.getDbInstance(this)
        preferences = getSharedPreferences("settings", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}