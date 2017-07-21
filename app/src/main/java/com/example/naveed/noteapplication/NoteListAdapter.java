package com.example.naveed.noteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Naveed on 7/12/2017.
 */
public class NoteListAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Note> notes;

    public NoteListAdapter(Context context, ArrayList<Note> notes) {
        super(context, R.layout.all_note_listview, notes);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listItemView = inflater.inflate(R.layout.all_note_listview, null);
        }
        else {
            listItemView = convertView;
        }
        Note obj = new Note();
        ((TextView)listItemView.findViewById(R.id.notetext)).setText(notes.get(position).n);
        ((TextView)listItemView.findViewById(R.id.date)).setText(notes.get(position).d);

        return listItemView;
    }
}