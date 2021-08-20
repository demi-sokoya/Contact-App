package com.example.finalproject;

import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//@Entity tells the database that we are declaring a new entity and it's attributes
@Entity(tableName = "contact_table")
public class Contact {
    //Declare the primary key and set it to autoincrement.
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @NonNull
    //@ColumnInfo makes sure that the database knows this is a column and we also specify the name of said column
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
    //The @Ignore in this case helps avoid any issues from the setter not existing.
    @Ignore
    private String mContactColor;




    public Contact( int id, @NonNull String contactName, @NonNull String contactNumber, String contactEmail, String contactAddress){
        //Constructor for the contact class
        Log.wtf("Constructctor", " Constructor called");
        mContactName = contactName;
        mContactNumber = contactNumber;
        mContactEmail = contactEmail;
        mContactAddress = contactAddress;
        mId = id;
        //sets the color to be a random color for each contact
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

    public void setmContactColor(String color){
        mContactColor = color;
    }

}
