package com.example.naveed.noteapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Naveed on 7/9/2017.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemSelectedListener{


    private EditText name;
    private EditText email;
    private EditText password;
    private String male;
    private String female;
    private Button register;
    private Button date;
  //  private DatePicker date;
    private Spinner gender;
    private ArrayAdapter<String> genderAdapter;
    private ArrayList<String> genderList;
    private SQLiteDatabase db;

    private String birth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        date = (Button)findViewById(R.id.date);
        gender = (Spinner)findViewById(R.id.gender);
    //    date = (DatePicker) findViewById(R.id.datePicker);
       register.setOnClickListener(this);
        date.setOnClickListener(this);
        genderList = new ArrayList<String>();
        genderList.add("Male");
        genderList.add("Female");
        genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genderList);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        gender.setAdapter(genderAdapter);
        db = openOrCreateDatabase("note", MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS register" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT, gender TEXT , birth date);");


        gender.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.register) {

            String n = name.getText().toString();
            String e = email.getText().toString();
            String p = password.getText().toString();
            db.execSQL("INSERT INTO register VALUES(NULL, '"+n+"', '"+e+"', '"+p+"','"+male+"','"+birth+"');");
            Toast.makeText(getBaseContext(), n + " : " + e, Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        else if (v.getId()==R.id.date)
        {

            DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    Toast.makeText(getBaseContext(), dayOfMonth + "/" + (monthOfYear+1) + "/" + year, Toast.LENGTH_SHORT).show();
                      int day = dayOfMonth;
                     int month = monthOfYear;
                     int y = year;
                     birth = ""+day+" / " + month+" / " +y;
                    date.setText(birth);
                }
            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePicker.setTitle("Date Picker Dialog Title");
            datePicker.setCanceledOnTouchOutside(true);
            datePicker.show();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        male = genderList.get(position).toString();
        ImageView img=(ImageView)findViewById(R.id.imageView); //delete this and it will work
        if(male.equals("Male"))
        {
            img.setImageResource(R.drawable.m);

        }
        else
        {
            img.setImageResource(R.drawable.f);
        }
        Toast.makeText(getBaseContext(),male,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
