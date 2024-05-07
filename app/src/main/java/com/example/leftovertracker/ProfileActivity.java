package com.example.leftovertracker;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfileActivity extends ComponentActivity {

    private Account profileInfo;
    //private AssetManager assets;

    ArrayList<LeftoverList> LeftoverLists = new ArrayList<>();
    @Override

    /*
    * this is essentially the Home Screen
    * */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.homescreen);
        //assets = getAssets();
        setupProfile();
        //setupProfile2();
        setupButtons();

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);
        setupLeftOverList();
        leftoverAdapter adapter = new leftoverAdapter(this, LeftoverLists);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

    }

    private void setupLeftOverList(){
        /* TODO
        *  create two String arrays from the itemList in memory
        *  one String array will have the item names
        *  the second String array will have the numbers of days left
        * */

        String[] leftOverNames = getLeftoverNames();
        String[] leftOverDays = getLeftoverDays();

        //String[] leftOverNames = getResources().getStringArray(R.array.leftover_foods_full_txt);
        //String[] leftOverDays = getResources().getStringArray(R.array.num_days_to_eat);

        for(int i = 0; i < leftOverNames.length; i++){
            LeftoverLists.add(new LeftoverList(leftOverNames[i], leftOverDays[i]));
        }
        //setContentView(R.layout.homescreen);
        profileInfo = null;
        //assets = getAssets();
        //setupProfile();
        //setupProfile2();
    }

    public void setupProfile(){
        /*
        * This sets up the profiles it writes
        * to the accounts.txt to be called from the
        * login screen
        * */
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
        //profileInfo = new Account(id, assets);

        Scanner scan;
        String str = "";
        String [] arr = null;
        try {
            if (f.exists()) {
                scan = new Scanner(openFileInput("accounts.txt"));
                while (scan.hasNext()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    if (Integer.parseInt(arr[0]) == id) {
                        profileInfo = new Account(id, arr[1], arr[2]);
                        break;
                    }
                }
                scan.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        if (profileInfo != null) {
            TextView name = (TextView) findViewById(R.id.textView5);
            //TextView email = (TextView) findViewById(R.id.textView1);
            name.setText(profileInfo.getName());
            //email.setText(profileInfo.getEmail());

        }
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

        // figuring out if file exists
        if (f.exists()) {
            try {
                w = new OutputStreamWriter(openFileOutput("itemsList.txt", MODE_PRIVATE));
                w.close();
                Toast.makeText(getBaseContext(), "Leftover List Cleared", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getBaseContext(), "There is no list to clear", Toast.LENGTH_SHORT).show();
        }
    }

    public String[] getLeftoverNames(){
        ArrayList<String> listOfNames = new ArrayList<String>();
        String[] leftOverNames = getResources().getStringArray(R.array.leftover_ex);
        File f = new File(getFilesDir().getAbsolutePath() + "/itemsList.txt");
        Scanner scan;
        String str;
        String[] arr;
        String[] retArray;

        // figuring out if file exists
        if (f.exists()) {
            try {
                scan = new Scanner(openFileInput("itemsList.txt"));

                // write names to ArrayList listOfNames
                while(scan.hasNextLine()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    listOfNames.add(arr[1]);
                }
                retArray = new String[listOfNames.size()];
                listOfNames.toArray(retArray);
                scan.close();
                return retArray;

                //Toast.makeText(getBaseContext(), "Leftover List Cleared", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return leftOverNames;
            }

        } else {
            //display default data
            Toast.makeText(getBaseContext(), "List Empty", Toast.LENGTH_SHORT).show();
            return leftOverNames;
        }

    }

    public String[] getLeftoverDays(){
        ArrayList<String> listOfDays = new ArrayList<String>();
        String[] leftOverDays = getResources().getStringArray(R.array.num_days_ex);
        File f = new File(getFilesDir().getAbsolutePath() + "/itemsList.txt");
        Scanner scan;
        String str;
        String[] arr;
        String[] retArray;

        // figuring out if file exists
        if (f.exists()) {
            try {
                scan = new Scanner(openFileInput("itemsList.txt"));

                // write days to ArrayList listOfDays
                while(scan.hasNextLine()) {
                    str = scan.nextLine();
                    arr = str.split(",");
                    listOfDays.add(arr[4]);
                }
                retArray = new String[listOfDays.size()];
                listOfDays.toArray(retArray);
                scan.close();
                return retArray;

                //Toast.makeText(getBaseContext(), "Leftover List Cleared", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "IOException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                return leftOverDays;
            }

        } else {
            //display default data
            Toast.makeText(getBaseContext(), "List Empty", Toast.LENGTH_SHORT).show();
            return leftOverDays;
        }

    }
}
