package com.example.leftovertracker;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    //private Button button;
    //private AssetManager assets;

    /*
     * opens program, loads "login.xml"
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //assets = getAssets();
        //Toast.makeText(this, "Hello Worlds", Toast.LENGTH_SHORT).show();
        setupButtons();
    }


    /*
    * sets up two buttons so they do stuff
    *
    * loginButton checks "login.txt" in assets
    *   if account info is found, will load "ProfileActivity"
    *   else will display error text for incorrect username/password
    *
    * registerButton will load "RegisterActivity"
    *
    * */
    private void setupButtons() {
        Button loginButton = (Button) findViewById(R.id.login);
        Button registerButton = (Button) findViewById(R.id.register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText userText = (EditText) findViewById (R.id.inputName);
                EditText passText = (EditText) findViewById(R.id.inputPassword);
                int id = authenticate(userText.getText().toString(),passText.getText().toString());
                //Account account = authenticate(userText.getText().toString(),passText.getText().toString());
               /* if(account != null){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    //intent.putExtra("id",id);
                    intent.putExtra("account", account);
                    startActivity(intent);
                }*/
                if(id > 0){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("id",id);
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
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //intent.putExtra("id",id);
                //intent.putExtra("account", account);
                startActivity(intent);
            }
        });
    }
    private int authenticate(String username, String password){
        Scanner scan;
        String str = "";
        String [] arr = null;
        //boolean authenticated = false;
        int id = -1;
        File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");

        try {
            if(f.exists()){
//            scan = new Scanner(assets.open("login.txt"));
                scan = new Scanner(openFileInput("login.txt"));
                while (scan.hasNext()) {

                    //EditText t = (EditText) findViewById(R.id.inputName);

                    str = scan.nextLine();
                    //t.setText(str);
                    arr = str.split(",");
                    if (username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])) {
                        id = Integer.parseInt(arr[0]);
                        //authenticated = true;
                        break;
                    }
                }
                scan.close();
            }
        }
        catch(IOException e){
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return id;
         /*   if(id > 0){
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

            return null;*/
    }
/*

    /*
    * method that checks if username and password are correct
    *   looks in "login.txt" found in assets folder
    * */
    /*
    private Account authenticate(String username, String password){
        Scanner scan;
        String str = "";
        String [] arr = null;
        boolean authenticated = false;
        int id = -1;
        Account account;
        File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");

        try {
            if(f.exists()) {
                scan = new Scanner(openFileInput("login.txt"));
                while (scan.hasNext()) {

                    //EditText t = (EditText) findViewById(R.id.inputName);

                    str = scan.nextLine();
                    //t.setText(str);
                    arr = str.split(",");
                    if (username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])) {
                        id = Integer.parseInt(arr[0]);
                        authenticated = true;
                        break;
                    }
                }
                scan.close();
            }
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

 */

}
