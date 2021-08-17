package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
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



    public Contact( int id, @NonNull String contactName, @NonNull String contactNumber, String contactEmail, String contactAddress){

        mContactName = contactName;
        mContactNumber = contactNumber;
        mContactEmail = contactEmail;
        mContactAddress = contactAddress;
        mId = id;
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


}
