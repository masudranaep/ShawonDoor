package com.example.shawondoor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.shawondoor.Adapter.NotesViewModels;
import com.example.shawondoor.Model.NotesModel;
import com.example.shawondoor.databinding.ActivityInsertBinding;

import java.util.Date;

public class InsertActivity extends AppCompatActivity {


    ActivityInsertBinding binding;
    String title, subtitle, notes;
    NotesViewModels notesViewModel;
    String prirority = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        binding = ActivityInsertBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        notesViewModel = ViewModelProviders.of ( this ).get ( NotesViewModels.class );

        //priority (4)
        binding.green.setOnClickListener ( v->{

            binding.green.setImageResource ( R.drawable.check_24 );
            binding.yollow.setImageResource ( 0 );
            binding.red.setImageResource ( 0 );

            prirority = "1";
        } );

        binding.yollow.setOnClickListener ( v->{

            binding.green.setImageResource ( 0 );
            binding.yollow.setImageResource ( R.drawable.check_24 );
            binding.red.setImageResource ( 0 );

            prirority = "2";
        } );

        binding.red.setOnClickListener ( v->{

            binding.green.setImageResource ( 0 );
            binding.yollow.setImageResource ( 0 );
            binding.red.setImageResource ( R.drawable.check_24 );
            prirority = "3";
        } );



        // 1 set (1)
       binding.DoneNotebtn.setOnClickListener ( v -> {
           title = binding.insertNotestitle.getText ().toString ();
           subtitle = binding.insertNotesSubtitle.getText ().toString ();
           notes = binding.insertNotesData.getText ().toString ();

           CreateNotes(title, subtitle, notes);




       } );
    }

    //notes create 1 set (2)-> mainActivity
    private void CreateNotes(String title, String subtitle, String notes) {

        //date 1 set (3)
        Date date = new Date ();
        CharSequence sequence = DateFormat.format ( "MMMM d, yyyy", date.getTime () );



        NotesModel notesModel1 = new NotesModel ();
        notesModel1.notesTitle = title;
        notesModel1.notesSubtitle = subtitle;
        notesModel1.notes = notes;
        notesModel1.notesPriority = prirority;
        notesModel1.notesDate = sequence.toString ();
        notesViewModel.insertNote ( notesModel1 );
        Toast.makeText ( this, "Notes Create Successfull", Toast.LENGTH_LONG ).show ();
        finish ();
    }
}