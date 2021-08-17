package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.BreakIterator;

public class AddContactActivity extends AppCompatActivity implements TextWatcher {
    public static final String NAME_REPLY = "NAME.REPLY";
    public static final String NUMBER_REPLY = "NUMBER.REPLY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText  contactName = findViewById(R.id.editTextContactName);


        EditText contactNumber = findViewById(R.id.editTextPhoneNumber);
        contactNumber.addTextChangedListener(this);

        final FloatingActionButton saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = contactName.getText().toString();
                String number =contactNumber.getText().toString();

                Intent replyIntent = new Intent();
                if(name.isEmpty() || number.equals(0)){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    replyIntent.putExtra(NAME_REPLY, name);
                    replyIntent.putExtra(NUMBER_REPLY, number);
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

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}