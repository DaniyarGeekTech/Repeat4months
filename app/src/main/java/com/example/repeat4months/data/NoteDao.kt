package com.example.repeat4months.data

import androidx.room.*
import com.example.repeat4months.model.NoteModel


@Dao
interface NoteDao {
    @Query("SELECT * FROM notemodel ")
    fun getAllNote(): List<NoteModel>

    @Insert
    fun addNote(model: NoteModel)

    @Update
    fun upDateNote(model: NoteModel)

    @Delete
    fun deleteNote(model: NoteModel)

}