package com.example.finalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll ();

    @Query("SELECT * FROM contact_table ORDER BY contactName ASC")
    LiveData<List<Contact>> getAllContacts();

}
