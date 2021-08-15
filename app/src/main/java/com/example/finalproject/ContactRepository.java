package com.example.finalproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    ContactRepository(Application application){
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mAllContacts = mContactDao.getAllContacts();
    }

    LiveData<List<Contact>> getAllContacts () {
        return mAllContacts;
    }

    public void insert(Contact contact){
        new insertAsyncTask(mContactDao).execute(contact);
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void>{
        private ContactDao mAsyncTaskDao;

        insertAsyncTask(ContactDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        //What does ... do in the background
        protected Void doInBackground(Contact... contacts) {
            mAsyncTaskDao.insert(contacts[0]);
            return null;
        }
    }

}
