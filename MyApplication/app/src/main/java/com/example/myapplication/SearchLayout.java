package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchLayout extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private static final String TAG = "MainActivity";

    /**
     * Este metodo se encarga de mostrar la pestaña o apartado de la aplicacion en este caso la correspondiente a busquedas de
     * lecciones.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Log.d(TAG, "onCreate: Starting");
        List<String> spinnerBranchValues= new ArrayList<>();
        //añadir logica para meter todos los nombres de los branch
        //dentro del array list con "spinnerBranchValues.add(branch_1)"
        spinnerBranchValues.add("BCartago");
        spinnerBranchValues.add("BChepe");
        setupSpinnerBranch(spinnerBranchValues);
        List<String> spinnerLessonTypeValues = new ArrayList<>();
        //añadir logica para meter todos los nombres de los tipos de lecciones
        //dentro del array list con "spinnerLessonTypeValues.add(lesson_type_1)"
        spinnerLessonTypeValues.add("Ciclismo");
        spinnerLessonTypeValues.add("Natacion");
        setupSpinnerLessonTypes(spinnerLessonTypeValues);
        Button startdateButton = (Button) findViewById(R.id.button6);
        startdateButton.setOnClickListener(new View.OnClickListener(){
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de seleccionar las fechas
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        Button volverInicio = (Button) findViewById(R.id.button3);
        volverInicio.setOnClickListener(new View.OnClickListener(){
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de inicio
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v){
                Intent intent=new Intent(SearchLayout.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button StartSearch = (Button) findViewById(R.id.button5);
        StartSearch.setOnClickListener((new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de lecciones encontradas
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent (SearchLayout.this, LessonsLayout.class);
                startActivity(intent);
            }
        }));
    }

    /**
     * Este metodo se encarga de setear la fecha seleccionada en el date picker en el texto donde
     * antes salia el seleccionar la fecha
     * @param view the picker associated with the dialog
     * @param year the selected year
     * @param month the selected month (0-11 for compatibility with
     *              {@link Calendar#MONTH})
     * @param dayOfMonth the selected day of the month (1-31, depending on
     *                   month)
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        TextView textView = (TextView) findViewById(R.id.editTextText4);
        textView.setText(currentDateString);
    }

    /**
     * Esta es el metodo que se encarga de crear el spinner para seleccionar la branch deseada
     * @param SpinnerBracnhValue
     */
    private void setupSpinnerBranch(List<String> SpinnerBracnhValue){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,SpinnerBracnhValue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        int SelectedBranch = spinner.getSelectedItemPosition();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Este metodo se utiliza para seleccionar el campo en los spinner de las Branch
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spinner.getItemAtPosition(position);
                Toast.makeText(SearchLayout.this, selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Esta es el metodo que se encarga de crear el spinner para seleccionar el tipo de leccion deseada
     * @param SpinnerLessonTypes
     */
    private void setupSpinnerLessonTypes(List<String> SpinnerLessonTypes){
        Spinner LessonTypeSpinner = findViewById(R.id.spinner3);
        ArrayAdapter<String> lessontypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,SpinnerLessonTypes);
        lessontypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LessonTypeSpinner.setAdapter(lessontypesAdapter);
        LessonTypeSpinner.setSelection(0);
        int SelectedLesson_Type = LessonTypeSpinner.getSelectedItemPosition();
        LessonTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Este metodo se utiliza para seleccionar el campo en los spinner de los tipos de lecciones
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) LessonTypeSpinner.getItemAtPosition(position);
                Toast.makeText(SearchLayout.this, selected, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public interface OnItemSelectedListener{
        void onItemSelected(AdapterView<?> parent,View view, int position, long id);
        void onNothingSelected(AdapterView<?> parent);
    }
}
