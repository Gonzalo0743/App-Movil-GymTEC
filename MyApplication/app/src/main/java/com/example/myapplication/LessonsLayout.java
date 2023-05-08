package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.db.DbHelper;

import java.util.ArrayList;

public class LessonsLayout extends AppCompatActivity {
    EditText clientInput, lessonInput;

    RecyclerView recyclerView;

    DbHelper dbH;
    ArrayList<String> lesson_id, quotas, service_id;
    CustomAdapter customadapter;

    /**
     * Este metodo se encarga de mostrar la pestaña o apartado de la aplicacion en este caso la correspondiente al selector de
     * lecciones.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_layout);

        clientInput = findViewById(R.id.Client_id);
        lessonInput = findViewById(R.id.Lessons_id);

        recyclerView = findViewById(R.id.recyclerView);
        dbH = new DbHelper(LessonsLayout.this);
        lesson_id = new ArrayList<>();
        quotas = new ArrayList<>();
        service_id = new ArrayList<>();

//        storeDataInArrays();
        customadapter = new CustomAdapter(LessonsLayout.this, lesson_id, quotas, service_id);
        recyclerView.setAdapter(customadapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LessonsLayout.this));

        //Back Button
        Button backButton = (Button) findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de inicio
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Register Button
        Button registerButton = (Button) findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de busqueda
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                DbHelper dbh = new DbHelper(LessonsLayout.this);
                dbh.insertClientLesson(
                        clientInput.getText().toString().trim(),
                        Integer.valueOf(lessonInput.getText().toString().trim())
                );
            }
        });
    }
    void storeDataInArrays() {
        Cursor cursor = dbH.readAllLessons();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                lesson_id.add(cursor.getString(0));
                quotas.add(cursor.getString(1));
                service_id.add(cursor.getString(8));
            }
        }
    }

}



