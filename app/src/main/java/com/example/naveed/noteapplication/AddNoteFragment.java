package com.example.naveed.noteapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.database.sqlite.SQLiteDatabase.openDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class AddNoteFragment extends Fragment implements View.OnClickListener{

    private Button add;
    private EditText addnote;
    private String email;
    private String note;
    private SQLiteDatabase db;
    private SharedPreferences shared;
    private Context c;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.add_note_fragment, container, false);
         c = container.getContext();
        db = c.openOrCreateDatabase("note",c.MODE_PRIVATE,null);
        shared = c.getSharedPreferences("loginhandling",c.MODE_PRIVATE);

        db.execSQL("CREATE TABLE IF NOT EXISTS Allnotes" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT, email TEXT , date date);");

        add = (Button)v.findViewById(R.id.add);
        addnote = (EditText)v.findViewById(R.id.addnote);

        add.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        email = shared.getString("email","");
        note= addnote.getText().toString();
        db.execSQL("INSERT INTO Allnotes VALUES(NULL, '"+note+"', '"+email+"','"+date+"');");
        Toast.makeText(c,note + email,Toast.LENGTH_LONG).show();

    }
}