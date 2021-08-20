package com.example.finalproject;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @NonNull
    @ColumnInfo(name = "contactName")
    private String mContactName;

    @NonNull
    @ColumnInfo(name = "contactNumber")
    private String mContactNumber;

    @NonNull
    @ColumnInfo(name = "contactEmail")
    private String mContactEmail;

    @NonNull
    @ColumnInfo(name = "contactAddress")
    private String mContactAddress;

    @ColumnInfo(name = "contactColor")
    @Ignore
    private String mContactColor;




    public Contact( int id, @NonNull String contactName, @NonNull String contactNumber, String contactEmail, String contactAddress){
        Log.wtf("Constructctor", " Constructor called");
        mContactName = contactName;
        mContactNumber = contactNumber;
        mContactEmail = contactEmail;
        mContactAddress = contactAddress;
        mId = id;
        mContactColor = Integer.toString(Color.argb(255, (int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
    }

    public Contact( int id, @NonNull String contactName, @NonNull String contactNumber, String contactEmail, String contactAddress, String color){
        Log.wtf("Constructctor", " Constructor called");
        mContactName = contactName;
        mContactNumber = contactNumber;
        mContactEmail = contactEmail;
        mContactAddress = contactAddress;
        mId = id;
        mContactColor = color;
    }

    public String getContactName(){
        return mContactName;
    }

    public String getContactNumber(){
        return mContactNumber;
    }

    public String getContactEmail() { return mContactEmail;}

    public String getContactAddress() { return mContactAddress;}

    public int getId(){return mId;}

    public String getContactColor () {
        return mContactColor;
    }

//    public void setmContactColor(int color){
//        mContactColor = color;
//    }

    public void setmContactColor(String color){
        mContactColor = color;
    }

}
