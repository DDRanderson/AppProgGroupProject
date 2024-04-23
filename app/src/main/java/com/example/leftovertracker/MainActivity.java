package com.example.leftovertracker;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    //private Button button;
    private AssetManager assets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        assets = getAssets();
        Toast.makeText(this, "Hello Worlds", Toast.LENGTH_SHORT).show();
        setupButtons();
    }

    private void setupButtons() {
        Button button1 = (Button) findViewById(R.id.login);
        Button button2 = (Button) findViewById(R.id.register);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userText = (EditText) findViewById (R.id.inputName);
                EditText passText = (EditText) findViewById(R.id.inputPassword);
                //int id = authenticate(userText.getText().toString(),passText.getText().toString());
                Account account = authenticate(userText.getText().toString(),passText.getText().toString());
                if(account != null){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    //intent.putExtra("id",id);
                    intent.putExtra("account", account);
                    startActivity(intent);
                }
                else{
                    userText.setText("");
                    passText.setText("");
                    userText.setError("Incorrect username");
                    passText.setError("Incorrect password");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //intent.putExtra("id",id);
                //intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }
    /*private int authenticate(String username, String password){
        Scanner scan;
        String str = "";
        String [] arr = null;
        boolean authenticated = false;
        int id = -1;

        try {
            scan = new Scanner(assets.open("login.txt"));
            while(scan.hasNext()){

                //EditText t = (EditText) findViewById(R.id.inputName);

                str = scan.nextLine();
                //t.setText(str);
                arr = str.split(",");
                if(username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])){
                    id = Integer.parseInt(arr[0]);
                    authenticated = true;
                    break;
                }
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }


        return id;
    }*/

    private Account authenticate(String username, String password){
        Scanner scan;
        String str = "";
        String [] arr = null;
        boolean authenticated = false;
        int id = -1;
        Account account;

        try {
            scan = new Scanner(assets.open("login.txt"));
            while(scan.hasNext()){

                //EditText t = (EditText) findViewById(R.id.inputName);

                str = scan.nextLine();
                //t.setText(str);
                arr = str.split(",");
                if(username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])){
                    id = Integer.parseInt(arr[0]);
                    authenticated = true;
                    break;
                }
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        if(id > 0){
            try {
                scan = new Scanner(assets.open("accounts.txt"));
                while(scan.hasNext()){

                    //EditText t = (EditText) findViewById(R.id.inputName);

                    str = scan.nextLine();
                    //t.setText(str);
                    arr = str.split(",");
                    if(Integer.parseInt(arr[0]) == id){
                        account = new Account(id, arr[1], arr[2]);
                        return account;
                    }
                }
                scan.close();
            }
            catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }

        return null;
    }


}
