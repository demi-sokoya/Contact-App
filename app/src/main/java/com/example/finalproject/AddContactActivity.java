package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.finalproject.MainActivity.EXTRA_DATA_ID;
import static com.example.finalproject.MainActivity.UPDATE_CONTACT_NAME;
import static com.example.finalproject.MainActivity.UPDATE_CONTACT_EMAIL;
import static com.example.finalproject.MainActivity.UPDATE_CONTACT_NUMBER;
import static com.example.finalproject.MainActivity.UPDATE_CONTACT_ADDRESS;


public class AddContactActivity extends AppCompatActivity implements TextWatcher {
    public static final String NAME_REPLY = "NAME.REPLY";
    public static final String NUMBER_REPLY = "NUMBER.REPLY";
    public static final String EMAIL_REPLY = "EMAIL.REPLY";
    public static final String ADDRESS_REPLY = "ADDRESS.REPLY";
    public static final String ID_REPLY = "ID.REPLY";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText  contactName = findViewById(R.id.editTextContactName);
        EditText contactNumber = findViewById(R.id.editTextPhoneNumber);
        EditText contactEmail = findViewById(R.id.editTextContactEmail);
        EditText contactAddress = findViewById(R.id.editTextContactAddress);

        int id = -1;

        final Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String name = extras.getString(UPDATE_CONTACT_NAME, "");
            String number = extras.getString(UPDATE_CONTACT_NUMBER, "");
            String email = extras.getString(UPDATE_CONTACT_EMAIL, "");
            String address = extras.getString(UPDATE_CONTACT_ADDRESS, "");
            if(!name.isEmpty() || !number.isEmpty() || email.isEmpty() || address.isEmpty()) {
                contactName.setText(name);
                contactNumber.setText(number);
                contactEmail.setText(email);
                contactAddress.setText(address);
            }

        }

        contactNumber.addTextChangedListener(this);
        contactName.addTextChangedListener(this);
        contactEmail.addTextChangedListener(this);
        contactAddress.addTextChangedListener(this);

        final FloatingActionButton saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(contactName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {

                    String name = contactName.getText().toString();
                    String number =contactNumber.getText().toString();
                    String email = contactEmail.getText().toString();
                    String address = contactAddress.getText().toString();

                    replyIntent.putExtra(NAME_REPLY, name);
                    replyIntent.putExtra(NUMBER_REPLY, number);
                    replyIntent.putExtra(EMAIL_REPLY, email);
                    replyIntent.putExtra(ADDRESS_REPLY, address);


                    if (extras != null && extras.containsKey(EXTRA_DATA_ID)){
                        int id = extras.getInt(EXTRA_DATA_ID, -1);
                        if(id != -1) {
                            replyIntent.putExtra(ID_REPLY, id);
                        }
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        EditText contactName = findViewById(R.id.editTextContactName);
        if(contactName.getText().toString().isEmpty()){
            findViewById(R.id.contactNameLabelTV).setVisibility(View.INVISIBLE);
        }
        else {
            TextView view = findViewById(R.id.contactNameLabelTV);
            view.setVisibility(View.VISIBLE);

        }

        EditText contactNumber = findViewById(R.id.editTextPhoneNumber);
        if(contactNumber.getText().toString().isEmpty()){
            findViewById(R.id.phoneNumberLabelTV).setVisibility(View.INVISIBLE);

        }
        else {
            findViewById(R.id.phoneNumberLabelTV).setVisibility(View.VISIBLE);
        }

        EditText contactEmail = findViewById(R.id.editTextContactEmail);
        if(contactEmail.getText().toString().isEmpty()){
            findViewById(R.id.emailLabelTV).setVisibility(View.INVISIBLE);

        }
        else {
            findViewById(R.id.emailLabelTV).setVisibility(View.VISIBLE);
        }
        EditText contactAddress = findViewById(R.id.editTextContactAddress);
        if(contactAddress.getText().toString().isEmpty()){
            findViewById(R.id.addressLabelTV).setVisibility(View.INVISIBLE);

        }
        else {
            findViewById(R.id.addressLabelTV).setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}