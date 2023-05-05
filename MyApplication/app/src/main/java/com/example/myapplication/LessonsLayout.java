package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LessonsLayout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_layout);

        Button volverInicio = (Button) findViewById(R.id.button);
        volverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button VolverBusqueda = (Button) findViewById(R.id.button2);
        VolverBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsLayout.this, SearchLayout.class);
                startActivity(intent);
            }
        });
    }

}
