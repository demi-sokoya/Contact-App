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

    public Contact(@NonNull String contactName, @NonNull String contactNumber, int id){

        mContactName = contactName;
        mContactNumber = contactNumber;
        mId = id;
    }

    public String getContactName(){
        return mContactName;
    }

    public String getContactNumber(){
        return mContactNumber;
    }

    //public int getId(){return mId;}


}
