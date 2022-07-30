package com.example.shawondoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.shawondoor.Adapter.NotesAdapter;
import com.example.shawondoor.Adapter.NotesViewModels;
import com.example.shawondoor.Model.NotesModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton newaddButton;
    NotesViewModels notesViewModels;
    RecyclerView recyclerView;
    NotesAdapter adapter;
    TextView nofilter, hightolow, lowtohigh;

    List<NotesModel> filternotesorfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        newaddButton = findViewById ( R.id.newNotesbtn );
        recyclerView = findViewById ( R.id.notesRecyclerView );

        nofilter = findViewById ( R.id.nofilter );
        hightolow = findViewById ( R.id.hightolow );
        lowtohigh = findViewById ( R.id.lowtohigh );

        //filter 1 set

        nofilter.setBackgroundResource ( R.drawable.filter_select );

        nofilter.setOnClickListener ( v -> {
            loadData(0);
            hightolow.setBackgroundResource ( 0 );
            lowtohigh.setBackgroundResource ( 0 );
            nofilter.setBackgroundResource ( R.drawable.filter_select );
        } );

        hightolow.setOnClickListener ( v -> {
            loadData(0);
            hightolow.setBackgroundResource ( R.drawable.filter_select );
            lowtohigh.setBackgroundResource ( 0 );
            nofilter.setBackgroundResource ( 0 );
        } );

        lowtohigh.setOnClickListener ( v -> {
            loadData(0);
            hightolow.setBackgroundResource ( 0 );
            lowtohigh.setBackgroundResource ( R.drawable.filter_select );
            nofilter.setBackgroundResource ( 0 );
        } );


        notesViewModels = ViewModelProviders.of ( this ).get ( NotesViewModels.class );

        newaddButton.setOnClickListener ( v -> {

            startActivity ( new Intent ( MainActivity.this, InsertActivity.class ) );
        } );


        // (6)

        notesViewModels.getallNotes.observe ( this, new Observer<List<NotesModel>> () {
            @Override
            public void onChanged(List<NotesModel> notesModels) {
                setAdapter ( notesModels );
                filternotesorfilter = notesModels;
            }
        } );

    }

    //filte 4 set
    private void loadData(int i) {
        if(i == 0){
            notesViewModels.getallNotes.observe ( this, new Observer<List<NotesModel>> () {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter ( notesModels );
                    filternotesorfilter = notesModels;
                }
            } );

        }else if(i == 1){

            notesViewModels.hightolow.observe ( this, new Observer<List<NotesModel>> () {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter ( notesModels );
                    filternotesorfilter = notesModels;

                }
            } );
        }else if(i == 2){

            notesViewModels.lowtohigh.observe ( this, new Observer<List<NotesModel>> () {
                @Override
                public void onChanged(List<NotesModel> notesModels) {
                    setAdapter ( notesModels );
                    filternotesorfilter = notesModels;

                }
            } );
        }

    }

    public void setAdapter(List<NotesModel> notesModels){
        recyclerView.setLayoutManager ( new StaggeredGridLayoutManager ( 2, StaggeredGridLayoutManager.VERTICAL ) );
        adapter = new NotesAdapter (MainActivity.this, notesModels);
        recyclerView.setAdapter ( adapter );

    }
    //search bar 1 set  -> adapter


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater ().inflate ( R.menu.search_view, menu );
        MenuItem menuItem = menu.findItem ( R.id.app_bar_search );

        SearchView searchView = (SearchView) menuItem.getActionView ();
        searchView.setQueryHint ( "Search Notes here...." );


        searchView.setOnQueryTextListener ( new SearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                NotesFilter(newText);
                return false;
            }
        } );
        return super.onCreateOptionsMenu ( menu );
    }

    private void NotesFilter(String newText) {

        ArrayList<NotesModel> filterName = new ArrayList<> ();

        for (NotesModel notesModel : this.filternotesorfilter){

            if(notesModel.notesTitle.contains ( newText ) || notesModel.notesSubtitle.contains ( newText )){

                filterName.add ( notesModel );
            }
        }
        this.adapter.searchNotes ( filterName );
    }
}