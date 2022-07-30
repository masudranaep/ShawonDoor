package com.example.shawondoor.Database;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shawondoor.Model.NotesModel;

import java.util.List;

//dao 1 set(1)
@androidx.room.Dao
public interface NotesDao {

    @Query ( "SELECT * FROM Notes_Database" )
    LiveData<List<NotesModel>> getallNotes();

    @Query ( "SELECT * FROM Notes_Database ORDER BY notes_priority DESC" )
    LiveData<List<NotesModel>> hightolow();

    @Query ( "SELECT * FROM Notes_Database ORDER BY notes_priority ASC" )
    LiveData<List<NotesModel>> lowtohigh();


    @Insert
    void inserNodes(NotesModel... notesModels);

    @Query ("DELETE FROM Notes_Database WHERE id = :id" )
    void deleteNote( int id);

    @Update
    void updateNodes(NotesModel notesModel);

}
