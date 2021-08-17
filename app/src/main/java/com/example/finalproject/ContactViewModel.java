package com.example.finalproject;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository mContactRepository;
    private LiveData<List<Contact>> mAllContacts;

    public ContactViewModel(Application application){
        super(application);
        mContactRepository = new ContactRepository(application);
        mAllContacts = mContactRepository.getAllContacts();
    }

    LiveData<List<Contact>> getAllContacts(){
        return mAllContacts;
    }

    public void insert(Contact contact){
        mContactRepository.insert(contact);
    }

    public void deleteContact(Contact contact) {
        mContactRepository.deleteContact(contact);
    }

    public void update(Contact contact) {
        mContactRepository.update(contact);
    }
}










