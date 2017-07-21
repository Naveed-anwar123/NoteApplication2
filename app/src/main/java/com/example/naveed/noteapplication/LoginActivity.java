package com.example.naveed.noteapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button change;
    private SQLiteDatabase db;
    private Cursor c;
    private EditText email;
    private EditText password;
    private Button login;
    SharedPreferences sharedpre ;
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        change = (Button) findViewById(R.id.change);
        login = (Button) findViewById(R.id.Login);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        sharedpre = getSharedPreferences("loginhandling",MODE_PRIVATE);



        change.setOnClickListener(this);
        login.setOnClickListener(this);

        db = openOrCreateDatabase("note", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS register" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, gender TEXT , birth date);");

        String value = sharedpre.getString("email",null);
        if( value != null)
        {
            Intent i = new Intent(this,NoteActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;
            case R.id.Login:
                Toast.makeText(getBaseContext(),"Clicked",Toast.LENGTH_LONG).show();
                String Eemail= email.getText().toString();
                String Epassword= password.getText().toString();
                c = db.rawQuery("Select email , password from register where email='"+Eemail+"' and password = '"+Epassword+"' ",null);
                c.moveToFirst();
                if(c.getCount() == 1)
                {
                    SharedPreferences.Editor editor = sharedpre.edit();
                    editor.putInt("id", c.getInt(0) );
                    editor.putString("username", c.getString(1) );
                    editor.putString("email", c.getString(c.getColumnIndex("email")));
                    editor.commit();
                    Toast.makeText(getBaseContext(),c.getCount()+"", Toast.LENGTH_LONG).show();
                    Intent note = new Intent(this,NoteActivity.class);
                    startActivity(note);

                }

//                for (int val = 0; val < c.getCount(); val++) {
                    //if (email.getText().toString().equals(c.getString(c.getColumnIndex("email"))) ) {


    //                    c.moveToNext();
                    //}


                //Toast.makeText(getBaseContext(), email.getText().toString(), Toast.LENGTH_LONG).show();
                    break;
  //              }


        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


}
