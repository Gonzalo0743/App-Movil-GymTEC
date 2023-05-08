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
import android.widget.EditText;
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

    EditText emailInput, passwordInput;
    /**
     * Este metodo se encarga de mostrar la primera pestaña o apartado de la aplicacion en este caso la correspondiente al incio de
     * sesión.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailInput = findViewById(R.id.editTextTextEmailAddress);
        passwordInput = findViewById(R.id.editTextTextPassword2);

        //Register Button
        Log.d(TAG, "onCreate: Starting");
        Button RegisterButton = (Button) findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Este metodo se encarga de activar el boton que te envia a la pestaña de regsitro de usiario
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked RegisterButton");

                Intent intent = new Intent(MainActivity.this, RegisterLayout.class);
                startActivity(intent);
            }
        });

        //Login Button
        Button LoginButton = (Button) findViewById(R.id.LoginButton);
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        LoginButton.setOnClickListener((new View.OnClickListener(){
            /**
             * Este metodo se encarga de aplicar el inicio de sesión segun los datos proporcionados
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v){
                MD5 coder = new MD5();
                String email = emailInput.getText().toString();
                String password = null;
                try {
                    password = coder.md5(passwordInput.getText().toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }


                if (email.equals("") || password.equals(""))
                    Toast.makeText(MainActivity.this, "ENTER ALL FIELDS", Toast.LENGTH_SHORT).show();
                else{
                    Boolean Login = dbHelper.LoginCheckPassword(email, password);
                    if(Login){
                        Toast.makeText(MainActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                        Intent intentt = new Intent(MainActivity.this, SearchLayout.class);
                        startActivity(intentt);
                    }else{
                        Toast.makeText(MainActivity.this, "INVALID EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }));


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