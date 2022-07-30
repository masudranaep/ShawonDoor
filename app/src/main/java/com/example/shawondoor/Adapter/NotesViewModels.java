package com.example.shawondoor.Adapter;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.shawondoor.Model.NotesModel;
import com.example.shawondoor.Repository.NotesRepository;

import java.util.List;
//noteviewModel 1 set (5)
public class NotesViewModels extends AndroidViewModel {

    public NotesRepository repository;
    public LiveData<List<NotesModel>> getallNotes;

    public LiveData<List<NotesModel>> hightolow;
    public LiveData<List<NotesModel>> lowtohigh;

    public NotesViewModels( Application application) {
        super ( application );


        repository = new NotesRepository ( application );
        getallNotes = repository.getallNotes;
        hightolow = repository.hightolow;
        lowtohigh = repository.lowtohigh;

    }

    public void insertNote(NotesModel notesModel){

        repository.insertNotes ( notesModel );
    }
    public void deleteNote(final int id){
        repository.deleteNotes ( id );
    }
    public void updateNote(NotesModel notesModel){
        repository.updateNores ( notesModel );
    }
}
