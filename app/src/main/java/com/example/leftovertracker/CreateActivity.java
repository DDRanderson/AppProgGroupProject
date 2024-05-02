package com.example.leftovertracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

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
                int id = -1;

                EditText itemName = (EditText) findViewById(R.id.itemName);
                EditText totalCals = (EditText) findViewById(R.id.itemCalories);
                EditText servings = (EditText) findViewById(R.id.itemServings);
                EditText daysLeft = (EditText) findViewById(R.id.itemDaysLeft);

                if(validateItemInfo()){
                    id = createItem();

                    if(id > 0){
                        //finish();
                        Intent intent = new Intent(CreateActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }

                }
                else {
                    itemName.setText("");
                    totalCals.setText("");
                    servings.setText("");
                    daysLeft.setText("");
                    itemName.setError("All fields must be filled out.");
                    totalCals.setError("All fields must be filled out.");
                    servings.setError("All fields must be filled out.");
                    daysLeft.setError("All fields must be filled out.");
                }
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
        EditText nameInput = (EditText) findViewById(R.id.itemName);
        EditText calInput = (EditText) findViewById(R.id.itemCalories);
        EditText servingsInput = (EditText) findViewById(R.id.itemServings);
        EditText daysInput = (EditText) findViewById(R.id.itemDaysLeft);
        String itemName = nameInput.getText().toString();
        String itemCalories = calInput.getText().toString();
        String itemServings = servingsInput.getText().toString();
        String itemDaysLeft = daysInput.getText().toString();

        File f = new File(getFilesDir().getAbsolutePath() + "/itemsList.txt");
        OutputStreamWriter w = null;
        Scanner scan;
        int id = -1;
        String str = null;
        String[] arr;

        // checking if file doesn't exist
        if(!f.exists()) {
            id = 1;

            try {
                w = new OutputStreamWriter(openFileOutput("itemsList.txt", MODE_PRIVATE));
                w.write(id + "," + itemName + "," + itemCalories + "," + itemServings + "," + itemDaysLeft);
                w.close();
            }
            catch(IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else if(f.exists() && isItemListEmpty()){
            try {
                scan = new Scanner(openFileInput("itemsList.txt"));

                id = 1;

                scan.close();

                w = new OutputStreamWriter(openFileOutput("itemsList.txt", MODE_PRIVATE));
                w.write(id + "," + itemName + "," + itemCalories + "," + itemServings + "," + itemDaysLeft);
                w.close();
            }
            catch(IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        else {
            try {
                scan = new Scanner(openFileInput("itemsList.txt"));

                while (scan.hasNextLine()) {
                    str = scan.nextLine();
                }
                if(str != null) {
                    arr = str.split(",");
                    if(arr.length == 5) {
                        id = Integer.parseInt(arr[0]) + 1;
                    }
                }
                scan.close();

                // use append in this case to avoid overwriting existing data
                w = new OutputStreamWriter(openFileOutput("itemsList.txt", MODE_APPEND));
                w.append("\n" + id + "," + itemName + "," + itemCalories + "," + itemServings + "," + itemDaysLeft);
                w.close();

                //TODO
                //add a check to make sure we aren't accidentally adding an empty newline char at the beginning of the file

            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return id;
    }

    public boolean isItemListEmpty(){
        Scanner scan;

        try{
            scan = new Scanner(openFileInput("itemsList.txt"));

            if(!scan.hasNextLine()){
                scan.close();
                return true;
            }
        } catch(IOException e){
            Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
