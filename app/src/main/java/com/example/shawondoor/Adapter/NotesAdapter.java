package com.example.shawondoor.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shawondoor.MainActivity;
import com.example.shawondoor.Model.NotesModel;
import com.example.shawondoor.R;
import com.example.shawondoor.UpdateActivity;

import java.util.ArrayList;
import java.util.List;
//(7)

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    MainActivity mainActivity;
    List<NotesModel> notesModels;


    List<NotesModel> allNoteItem;

    public NotesAdapter(MainActivity mainActivity, List<NotesModel> notesModels) {
        this.mainActivity =mainActivity;
        this.notesModels = notesModels;

        allNoteItem = new ArrayList<> (notesModels);
    }

    //search bar 2 set
    public  void searchNotes(List<NotesModel> filterName){

        this.notesModels = filterName;
        notifyDataSetChanged ();

    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new NotesViewHolder ( LayoutInflater.from ( mainActivity ).inflate ( R.layout.item_notes, parent, false ) );

    }

    @Override
    public void onBindViewHolder(NotesAdapter.NotesViewHolder holder, int position) {

        NotesModel note = notesModels.get ( position );
        //notepriroty (9)
        switch (note.notesPriority) {
            case "1":
                holder.notesprority.setBackgroundResource ( R.drawable.green );

                break;
            case "2":
                holder.notesprority.setBackgroundResource ( R.drawable.yellow );

                break;
            case "3":
                holder.notesprority.setBackgroundResource ( R.drawable.red );
                break;
        }

        holder.title.setText ( note.notesTitle );
        holder.subtitle.setText ( note.notesSubtitle );
        holder.notesDate.setText ( note.notesDate );

        //update 1 set (10) updateactivity ->
        holder.itemView.setOnClickListener ( v -> {
            Intent intent = new Intent (mainActivity, UpdateActivity.class );
            intent.putExtra ( "id", note.id );
            intent.putExtra ( "title", note.notesTitle );
            intent.putExtra ( "subTitle", note.notesSubtitle );
            intent.putExtra ( "priority", note.notesPriority );
            intent.putExtra ( "notes", note.notes );


            mainActivity.startActivity ( intent );

        } );


    }

    @Override
    public int getItemCount() {
        return notesModels.size ();
    }
    //item notes (8)


    public class NotesViewHolder extends RecyclerView.ViewHolder{

        TextView title, subtitle, notesDate;
        View notesprority;

        public NotesViewHolder(@NonNull View itemView) {
            super ( itemView );

            title = itemView.findViewById ( R.id.notesTitle );
            subtitle = itemView.findViewById ( R.id.notesSubTitle );
            notesDate = itemView.findViewById ( R.id.notesDate );
            notesprority = itemView.findViewById ( R.id.notesPrirority );
        }
    }
}
