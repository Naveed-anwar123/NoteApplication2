package com.example.naveed.noteapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Naveed on 7/12/2017.
 */
public class NoteNavigation extends AppCompatActivity implements View.OnClickListener{

    private EditText updatenote;
    private Button update;
    private SharedPreferences shared;
    private SQLiteDatabase db;
    private int ss;
    private Cursor cursor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        updatenote = (EditText)findViewById(R.id.updatenote);
        update= (Button)findViewById(R.id.update);
        update.setOnClickListener(this);
        db = openOrCreateDatabase("note",MODE_PRIVATE,null);
        shared = getSharedPreferences("loginhandling",MODE_PRIVATE); // need a where condition here
        ss = Integer.parseInt(shared.getString("user_Id",null));
        cursor = db.rawQuery("SELECT note , date from Allnotes where id ="+ss+" ", null);
        cursor.moveToFirst();
        updatenote.setText(cursor.getString(cursor.getColumnIndex("note")));
        String value = shared.getString("email",null);
        if( value != null)
        {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        String value = shared.getString("email",null);
        if( value != null)
        {
            Intent i = new Intent(this,LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {

        String p = updatenote.getText().toString();
        db.execSQL("UPDATE Allnotes SET note = '"+p+"' where id ='"+ss+"' ");
        Intent i = new Intent(this, NoteActivity.class);
        startActivity(i);

    }
}
