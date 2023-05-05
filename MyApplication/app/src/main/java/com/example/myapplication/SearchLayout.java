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
            @Override
            public void onClick(View v){
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });
        Button volverInicio = (Button) findViewById(R.id.button3);
        volverInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(SearchLayout.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Button StartSearch = (Button) findViewById(R.id.button5);
        StartSearch.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent (SearchLayout.this, LessonsLayout.class);
                startActivity(intent);
            }
        }));
    }

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

    private void setupSpinnerBranch(List<String> SpinnerBracnhValue){
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,SpinnerBracnhValue);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        int SelectedBranch = spinner.getSelectedItemPosition();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    private void setupSpinnerLessonTypes(List<String> SpinnerLessonTypes){
        Spinner LessonTypeSpinner = findViewById(R.id.spinner3);
        ArrayAdapter<String> lessontypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,SpinnerLessonTypes);
        lessontypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LessonTypeSpinner.setAdapter(lessontypesAdapter);
        LessonTypeSpinner.setSelection(0);
        int SelectedLesson_Type = LessonTypeSpinner.getSelectedItemPosition();
        LessonTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
