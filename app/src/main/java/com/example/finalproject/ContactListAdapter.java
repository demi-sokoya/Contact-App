package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    private List<Contact> mContacts;

    ContactListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        }



    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View item = mInflater.inflate(R.layout.recycler_view_item, parent, false);
    return null;
    }

    public void onBindViewHolder(ContactViewHolder holder, int position){
        if(mContacts != null) {
            Contact current = mContacts.get(position);
            holder.contactNameView.setText((current.getContactName()));
            holder.phoneNumberView.setText(current.getContactNumber());
            holder.firstLetterView.setText(current.getContactName().charAt(0));
        }
        else {
            holder.contactNameView.setText("Default Name");
            holder.phoneNumberView.setText("Default Number");
            holder.firstLetterView.setText("D");
        }
    }

    void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    public int getItemCount(){
        if(mContacts != null){
            return mContacts.size();
        } else {
            return 0;
        }
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactNameView;
        private final TextView phoneNumberView;
        private final TextView firstLetterView;

        private ContactViewHolder(View itemView){
            super(itemView);
            contactNameView = itemView.findViewById(R.id.contact_name);
            phoneNumberView = itemView.findViewById(R.id.phone_number);
            firstLetterView = itemView.findViewById(R.id.contact_first_letter);
        }
    }
}
