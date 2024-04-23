package com.example.leftovertracker;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

public class RegisterActivity extends ComponentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        //Toast.makeText(this, "Hello Worlds", Toast.LENGTH_SHORT).show();
        //setupButtons();
    }
}
