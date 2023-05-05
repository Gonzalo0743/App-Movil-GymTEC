package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.DbHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //Register Button
        Log.d(TAG, "onCreate: Starting");
        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked RegisterButton");

                Intent intent = new Intent(MainActivity.this, RegisterLayout.class);
                startActivity(intent);
            }
        });
        Button LoginButton = (Button) findViewById(R.id.LoginButton);
        LoginButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG, "onClick: Clicked RegisterButton");

                Intent intentt = new Intent(MainActivity.this, SearchLayout.class);
                startActivity(intentt);
            }
        }));

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "DB CREATED", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Err", Toast.LENGTH_LONG).show();
        }
        List<String> spinnerBranchValues= new ArrayList<>();
        }
}