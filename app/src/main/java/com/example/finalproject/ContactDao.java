package com.example.finalproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll ();

    @Delete
    void deleteContact(Contact contact);

    @Query("SELECT * FROM contact_table ORDER BY contactName ASC")
    LiveData<List<Contact>> getAllContacts();

    @Update
    void update(Contact... contact);

}
