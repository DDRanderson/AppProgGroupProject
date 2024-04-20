package com.example.leftovertracker;

import android.content.res.AssetManager;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Scanner;

public class Account implements Parcelable {
    private int id;
    private String name;
    private String email;

    public Account(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Account(int id, AssetManager assets){
        this.id = id;
        setupFromFile(id, assets);
    }

    protected Account(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setupFromFile(int id, AssetManager assets){
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
                    name = arr[1];
                    email = arr[2];
                    //profileInfo = new Account(id, arr[1], arr[2]);
                    break;
                }
            }
            scan.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
    }

    private void readFromParcel(Parcel in){
        id = in.readInt();
        name = in.readString();
        email = in.readString();
    }
}
