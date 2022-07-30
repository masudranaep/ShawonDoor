package com.example.shawondoor.Repository;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.shawondoor.Database.NotesDao;
import com.example.shawondoor.Database.NotesDatabase;
import com.example.shawondoor.Model.NotesModel;

import java.util.List;

// note 1 set (3)
public class NotesRepository {

 public NotesDao notesDao;
 public LiveData<List<NotesModel>> getallNotes;

    public LiveData<List<NotesModel>> hightolow;
    public LiveData<List<NotesModel>> lowtohigh;

 public NotesRepository(Application application){

  NotesDatabase database = NotesDatabase.getDatabaseInstance ( application );
  notesDao = database.notesDao ();
  getallNotes = notesDao.getallNotes ();

  hightolow = notesDao.hightolow ();
  lowtohigh = notesDao.lowtohigh ();

}
public void insertNotes(NotesModel notesModel){
  notesDao.inserNodes ( notesModel );


}
public void deleteNotes( int id){

  notesDao.deleteNote (id);
}

public void updateNores(NotesModel notesModel){
  notesDao.updateNodes ( notesModel );


}


}
