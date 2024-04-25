package com.example.leftovertracker;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class ProfileActivity extends ComponentActivity {

    private Account profileInfo;
    private AssetManager assets;
    @Override

    /*
    * this is essentially the Home Screen
    * */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        assets = getAssets();
        //setupProfile();
        setupProfile2();
        setupButtons();
        //generateList(); hopefully this will populate the display list at homescreen creation every time
    }

    public void setupProfile(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        //profileInfo = new Account(id, assets);

        Scanner scan;
        String str = "";
        String [] arr = null;
        try {
            scan = new Scanner(assets.open("accounts.txt"));
            while(scan.hasNext()){

                //EditText t = (EditText) findViewById(R.id.inputName);

                str = scan.nextLine();
                //t.setText(str);
                arr = str.split(",");
                if(Integer.parseInt(arr[0]) == id){
                    profileInfo = new Account(id, arr[1], arr[2]);
                    break;
                }
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        TextView name = (TextView) findViewById(R.id.textView5);
        //TextView email = (TextView) findViewById(R.id.textView1);
        name.setText(profileInfo.getName());
        //email.setText(profileInfo.getEmail());
    }

    private void setupProfile2(){
        Intent intent = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            profileInfo = intent.getParcelableExtra("account",Account.class);
        }
        TextView name = (TextView) findViewById(R.id.textView5);
        //TextView email = (TextView) findViewById(R.id.textView1);
        name.setText(profileInfo.getName());
        //email.setText(profileInfo.getEmail());
    }

    private void setupButtons() {
        ImageButton buttonAddRecipe = (ImageButton) findViewById(R.id.buttonAddRecipe);
        ImageButton buttonClearList = (ImageButton) findViewById(R.id.trashButton);

        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (ProfileActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        buttonClearList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clearList();
            }
        });
    }

    public void clearList() {
        File f = new File(getFilesDir().getAbsolutePath() + "/itemsList.txt");
        OutputStreamWriter w = null;
        Scanner scan;

        // figuring out if file exists
        if (f.exists()) {
            try {
                scan = new Scanner(openFileInput("itemsList.txt"));
                w = new OutputStreamWriter(openFileOutput("itemsList.txt", MODE_PRIVATE));

                // overwrite existing data with "" characters
                while (scan.hasNextLine()) {
                    w.write("");
                }
                w.close();
                scan.close();
                Toast.makeText(getBaseContext(), "Leftover List Cleared", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getBaseContext(), "There is no list to clear", Toast.LENGTH_SHORT).show();
        }

    }
}
