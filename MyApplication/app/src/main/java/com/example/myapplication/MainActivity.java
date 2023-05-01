package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(db != null){
            Toast.makeText(MainActivity.this, "DB CREATED", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Err", Toast.LENGTH_LONG).show();
        }
    }
}