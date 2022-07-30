package com.example.shawondoor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shawondoor.Adapter.NotesViewModels;
import com.example.shawondoor.Model.NotesModel;
import com.example.shawondoor.databinding.ActivityUpdateBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    String priority = "1";
    String  stitle, ssubtitle,snotes, spriority;
    int iid;
    NotesViewModels notesViewModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        binding = ActivityUpdateBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        // 2 set (11)
        iid = getIntent ().getIntExtra ( "id",0 );
        stitle = getIntent ().getStringExtra ( "title" );
        ssubtitle = getIntent ().getStringExtra ( "subTitle" );
        spriority = getIntent ().getStringExtra ( "priority" );
        snotes = getIntent ().getStringExtra ( "notes" );


        binding.updatenotesTitle.setText ( stitle );
        binding.updatenotesSubtitle.setText ( ssubtitle );
        binding.updateNotes.setText ( snotes );


        //priority 1 set (13)
        if(spriority.equals ( "1" )){
            binding.upgreen.setImageResource ( R.drawable.check_24 );
        }else if(spriority.equals ( "2" )){
            binding.upYollow.setImageResource ( R.drawable.check_24 );
        }else  if (spriority.equals ( "3" )){
            binding.upRed.setImageResource ( R.drawable.check_24 );
        }

        notesViewModels = ViewModelProviders.of ( this ).get ( NotesViewModels.class );

        binding.upgreen.setOnClickListener ( v -> {
            binding.upgreen.setImageResource ( R.drawable.check_24 );
            binding.upYollow.setImageResource ( 0 );
            binding.upRed.setImageResource ( 0 );
            priority = "1";
        } );
        binding.upYollow.setOnClickListener ( v -> {

            binding.upgreen.setImageResource (0 );
            binding.upYollow.setImageResource (R.drawable.check_24 );
            binding.upRed.setImageResource ( 0 );
            priority = "2";

        } );
        binding.upRed.setOnClickListener ( v -> {
            binding.upgreen.setImageResource (0 );
            binding.upYollow.setImageResource (0);
            binding.upRed.setImageResource (R.drawable.check_24  );
            priority = "3";

        } );

        //update button 3set (12)

        binding.updateNotesbtn.setOnClickListener ( v -> {

            String title = binding.updatenotesTitle.getText ().toString ();
            String subtitle = binding.updatenotesSubtitle.getText ().toString ();
            String notes = binding.updateNotes.getText ().toString ();

            UpdateNotes(title, subtitle, notes);
        } );

    }

    private void UpdateNotes(String title, String subtitle, String notes) {

        Date date = new Date ();
        CharSequence sequence = DateFormat.format ( "MMMM d, yyyy" , date.getTime ());
        NotesModel updateNotes = new NotesModel ();

        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString ();

        notesViewModels.updateNote ( updateNotes );

        Toast.makeText ( this, " Notes Update SuccesFully", Toast.LENGTH_LONG ).show ();

        finish ();
    }

    // delete menu item 1 set (14)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.delete_menu,menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId () == R.id.ic_delete){
            BottomSheetDialog sheetDialog = new BottomSheetDialog ( UpdateActivity.this, R.style.BottomSheetStyle );

            View view = LayoutInflater.from ( UpdateActivity.this )
                    .inflate ( R.layout.delete_bottom_sheet, (LinearLayout) findViewById ( R.id.bottomsheet ) );

            sheetDialog.setContentView ( view );

            TextView yes, no;

            yes = view.findViewById ( R.id.delete_yes );
            no = view.findViewById ( R.id.delete_no );

            yes.setOnClickListener ( v -> {
                notesViewModels.deleteNote ( iid );
                finish ();
            } );

            no.setOnClickListener ( v -> {

                sheetDialog.dismiss ();
            } );

            sheetDialog.show ();
        }
        return true;
    }
}