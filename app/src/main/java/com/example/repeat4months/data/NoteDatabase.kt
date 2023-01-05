package com.example.repeat4months.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.repeat4months.model.NoteModel

@Database(entities = [NoteModel::class], version = 1, exportSchema = true)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao?

    companion object {

        private lateinit var INSTANCE: NoteDatabase

        fun getDbInstance(context: Context): NoteDatabase {

            INSTANCE = Room.databaseBuilder(
                context,
                NoteDatabase::class.java,
                "DB NAME"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

            return INSTANCE
        }
    }}