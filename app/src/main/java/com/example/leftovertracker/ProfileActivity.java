package com.example.leftovertracker;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.io.IOException;
import java.util.Scanner;

public class ProfileActivity extends ComponentActivity {

    private Account profileInfo;
    private AssetManager assets;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        assets = getAssets();
        //setupProfile();
        setupProfile2();
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
        TextView email = (TextView) findViewById(R.id.textView1);
        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
    }

    private void setupProfile2(){
        Intent intent = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            profileInfo = intent.getParcelableExtra("account",Account.class);
        }
        TextView name = (TextView) findViewById(R.id.textView5);
        TextView email = (TextView) findViewById(R.id.textView1);
        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
    }
}
