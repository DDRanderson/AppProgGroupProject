package com.example.leftovertracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;

public class CreateActivity extends ComponentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createleftover);

        setupButtons();
    }


    private void setupButtons() {
        Button createButton = (Button) findViewById(R.id.createDone);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText itemName = (EditText) findViewById(R.id.itemName);
                EditText totalCals = (EditText) findViewById(R.id.itemCalories);
                EditText servings = (EditText) findViewById(R.id.itemServings);
                EditText daysLeft = (EditText) findViewById(R.id.itemDaysLeft);
            }
        });
    }

    private boolean validateItemInfo(){
        EditText itemName = (EditText) findViewById(R.id.itemName);
        EditText totalCals = (EditText) findViewById(R.id.itemCalories);
        EditText servings = (EditText) findViewById(R.id.itemServings);
        EditText daysLeft = (EditText) findViewById(R.id.itemDaysLeft);

        // checking to see if fields are NOT empty
        return !itemName.getText().toString().equals("") && !totalCals.getText().toString().equals("") && !servings.getText().toString().equals("") && !daysLeft.getText().toString().equals("");
    }

    private int createItem() {
        // placeholder return value for now while we come up with the logic for this portion
        return 0;
    }
}
