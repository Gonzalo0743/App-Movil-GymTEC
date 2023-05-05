package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterLayout extends AppCompatActivity {

    private static final String TAG = "RegisterLayout";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

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
    }
}
