package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LessonsLayout extends AppCompatActivity {
    /**
     * Este metodo se encarga de mostrar la pestaña o apartado de la aplicacion en este caso la correspondiente al selector de
     * lecciones.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lessons_layout);

        Button volverInicio = (Button) findViewById(R.id.button);
        volverInicio.setOnClickListener(new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de inicio
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button VolverBusqueda = (Button) findViewById(R.id.button2);
        VolverBusqueda.setOnClickListener(new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de busqueda
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LessonsLayout.this, SearchLayout.class);
                startActivity(intent);
            }
        });
    }

}
