package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private ContactViewModel mContactViewModel;
    public static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_CONTACT_ACTIVITY_REQUEST_CODE = 2;

    public static final String UPDATE_CONTACT_NAME = "contact_name_to_be_updated";
    public static final String UPDATE_CONTACT_NUMBER = "contact_number_to_be_updated";
    public static final String UPDATE_CONTACT_EMAIL = "contact_email_to_be_updated";
    public static final String UPDATE_CONTACT_ADDRESS = "contact_address_to_be_updated";

    public static final String EXTRA_DATA_ID = "extra_data_id";
    public static final String EXTRA_DATA_COLOR = "extra_data_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.toolbar);
        //set up the ContactViewModel
        mContactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        //Set up the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactListAdapter adapter = new ContactListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Get all the contacts from the database and add them to the adapter
        mContactViewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                //update the list of contacts
                adapter.setContacts(contacts);
            }
        });

        //Set up for the floating action button
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
            }
        });



        //Added the ability to swipe items to delete them from the list
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        Contact currentContact = adapter.getContactAtPosition(position);
                        Toast.makeText(MainActivity.this, getString(R.string.Deleting) + " " +currentContact.getContactName(),Toast.LENGTH_SHORT).show();

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Do you want to Delete " + currentContact.getContactName() + " from your contact list");

                        builder.setPositiveButton("YES \uD83D\uDDD1️", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mContactViewModel.deleteContact(currentContact);
                            }
                        });

                        //builder.setPositiveButtonIcon(getDrawable(R.drawable.ic_baseline_delete_forever_24));



                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Contact not deleted.", Toast.LENGTH_SHORT);
                                adapter.notifyItemChanged(position);
                            }

                        });

                        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.notifyItemChanged(position);
                            }
                        });

                        AlertDialog dialog = builder.create();

                        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                            @Override
                            public void onShow(DialogInterface d) {
                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.BaseBrickDark));
                                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(randomColor());
                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(getResources().getColor(R.color.BaseBrickLight));
                            }
                        });

                        dialog.show();

                        //delete the contact
                       // mContactViewModel.deleteContact(currentContact);
                    }
                });
        helper.attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new ContactListAdapter.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Contact contact = adapter.getContactAtPosition(position);
                launchUpdateContactActivity(contact);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE
        && resultCode == RESULT_OK){
            String name = data.getStringExtra(AddContactActivity.NAME_REPLY);
            String number = data.getStringExtra((AddContactActivity.NUMBER_REPLY));
            String email = data.getStringExtra((AddContactActivity.EMAIL_REPLY));
            String address = data.getStringExtra((AddContactActivity.ADDRESS_REPLY));
            Integer id = data.getIntExtra(EXTRA_DATA_ID, 0);



            Contact contact = new Contact(id, name, number, email, address);
            //save the data to the database
            mContactViewModel.insert(contact);

        } else if (requestCode == UPDATE_CONTACT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String name_data = data.getStringExtra(AddContactActivity.NAME_REPLY);
            String number_data = data.getStringExtra(AddContactActivity.NUMBER_REPLY);
            String email_data = data.getStringExtra(AddContactActivity.EMAIL_REPLY);
            String address_data = data.getStringExtra(AddContactActivity.ADDRESS_REPLY);
            int color_data = data.getIntExtra(AddContactActivity.COLOR_REPLY, randomColor()); //finish
            int id = data.getIntExtra(AddContactActivity.ID_REPLY, -1);

            if(id != -1) {
                mContactViewModel.update(new Contact(id, name_data, number_data, email_data, address_data, color_data));
            } else {
                Toast.makeText(this, "Unable to update",
                        Toast.LENGTH_LONG).show();
            }

        }
    }

    private void launchUpdateContactActivity(Contact contact) {
        Intent intent = new Intent(this, AddContactActivity.class);
        intent.putExtra(UPDATE_CONTACT_NAME,contact.getContactName());
        intent.putExtra(UPDATE_CONTACT_EMAIL,contact.getContactEmail());
        intent.putExtra(UPDATE_CONTACT_NUMBER,contact.getContactNumber());
        intent.putExtra(UPDATE_CONTACT_ADDRESS,contact.getContactAddress());
        intent.putExtra(EXTRA_DATA_ID, contact.getId());
        intent.putExtra(EXTRA_DATA_COLOR, contact.getContactColor());
        startActivityForResult(intent, UPDATE_CONTACT_ACTIVITY_REQUEST_CODE);

    }

    public int randomColor (){
        return Color.argb(255, (int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
    }
}