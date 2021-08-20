package com.example.finalproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    private List<Contact> mContacts;
    private static ClickListener clickListener;

    ContactListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        }



    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ContactViewHolder(itemView);


    }

    public void onBindViewHolder(ContactViewHolder holder, int position){
        if(mContacts != null) {
            Contact current = mContacts.get(position);
            holder.contactNameView.setText((current.getContactName()));
            holder.phoneNumberView.setText(current.getContactNumber());
            //Adds the first letter of the contacts name to the little circle
            holder.firstLetterView.setText(Character.toString(current.getContactName().charAt(0)).toUpperCase());
            holder.firstLetterView.getBackground().setTint(Integer.parseInt(current.getContactColor()));
        }
        else {
            holder.contactNameView.setText("Default Name");
            holder.phoneNumberView.setText("Default Number");
            holder.firstLetterView.setText("D");
        }
    }

    //updating the list of contacts
    void setContacts(List<Contact> contacts){
        mContacts = contacts;
        notifyDataSetChanged();
    }

    public int getItemCount(){
        if(mContacts != null){
            return mContacts.size();
        } else return 0;

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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public Contact getContactAtPosition (int position) {

        return mContacts.get(position);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ContactListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View view, int position);
    }
}
