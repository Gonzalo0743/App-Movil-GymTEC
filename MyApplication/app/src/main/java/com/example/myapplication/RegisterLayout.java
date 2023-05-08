package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.DbHelper;

public class RegisterLayout extends AppCompatActivity {

    private static final String TAG = "RegisterLayout";

    EditText nameInput, lname1Input, lname2Input, idInput, bdateInput, weightInput, bmiInput, addressInput, emailInput, passwordInput;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        nameInput = findViewById(R.id.RegName);
        lname1Input = findViewById(R.id.RegLName1);
        lname2Input = findViewById(R.id.RegLName2);
        idInput = findViewById(R.id.RegID);

        bdateInput = findViewById(R.id.RegBD);
        weightInput = findViewById(R.id.RegWeight);
        bmiInput = findViewById(R.id.RegBMI);
        addressInput = findViewById(R.id.RegAddress);
        emailInput = findViewById(R.id.RegEmail);
        passwordInput = findViewById(R.id.RegPassword);

        //Back Button
        Log.d(TAG, "onCreate: Starting");
        Button BackButton = (Button) findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked BackButton");

                Intent intent = new Intent(RegisterLayout.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Register Button
        Log.d(TAG, "onCreate: Starting");
        Button registerButton = (Button) findViewById(R.id.RegisterButton2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbh = new DbHelper(RegisterLayout.this);
                MD5 coder = new MD5();
                try {
                    dbh.insertClient(

                            idInput.getText().toString().trim(),
                            addressInput.getText().toString().trim(),
                            Integer.valueOf(weightInput.getText().toString().trim()),
                            Integer.valueOf(bmiInput.getText().toString().trim()),
                            nameInput.getText().toString().trim(),
                            lname1Input.getText().toString().trim(),
                            lname2Input.getText().toString().trim(),
                            coder.md5(passwordInput.getText().toString().trim()),
                            bdateInput.getText().toString().trim(),
                            emailInput.getText().toString().trim()
                    );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
