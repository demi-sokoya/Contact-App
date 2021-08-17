package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContactActivity extends AppCompatActivity {
    public static final String NAME_REPLY = "NAME.REPLY";
    public static final String NUMBER_REPLY = "NUMBER.REPLY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText  contactName = findViewById(R.id.editTextContactName);
        if(contactName.getText().toString().equals("")){
        }
        else {
            findViewById(R.id.phoneNumberLabelTV).setVisibility(View.VISIBLE);
        }

        EditText contactNumber = findViewById(R.id.editTextPhoneNumber);
        if(contactNumber.getText().toString().equals("")){
        }
        else {
            findViewById(R.id.phoneNumberLabelTV).setVisibility(View.VISIBLE);
        }
        final FloatingActionButton saveBtn = findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = contactName.getText().toString();
                Integer number = Integer.parseInt(contactNumber.getText().toString());

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
}