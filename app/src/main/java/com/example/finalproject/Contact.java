package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact_table")
public class Contact {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "contactName")
    private String mContactName;

    @ColumnInfo(name = "contactNumber")
    private Integer mContactNumber;

    public Contact(@NonNull String contactName, @NonNull Integer contactNumber){

        mContactName = contactName;
        mContactNumber = contactNumber;
    }

    public String getContactName(){
        return mContactName;
    }

    public Integer getContactNumber(){
        return mContactNumber;
    }


}
