package com.example.sqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import Data.DatabaseHandler;
import Model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DECLARING THE VIEWS
    private EditText nameEditText;
    private EditText phoneNoEditText;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // INITIALIZING THE VIEWS
        nameEditText = findViewById(R.id.nameEditText);
        phoneNoEditText = findViewById(R.id.phoneNoEditText);
        saveBtn = findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

         // CLEARING THE DATABASE
//        try (DatabaseHandler db = new DatabaseHandler(MainActivity.this)) {
//
//            db.deleteAllContacts();
//
//            Log.d(
//                    "TAG",
//                    "onCrate MainActivity DatabaseHandler Deleting All Previous Contacts"
//            );
//
//        } catch (Exception e) {
//            Log.d(
//                    "TAG",
//                    "onCreate Main Activity DatabaseHandler Exception: " + e.getMessage()
//            );
//        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // ADDING THE CLICK LISTENER TO THE SAVE BUTTON
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GETTING THE EDIT TEXTS
                String name = nameEditText.getText().toString();
                String phoneNo = phoneNoEditText.getText().toString();

                // CHECKING IF THE FIELDS ARE EMPTY IF NOT THEN SAVE THE CONTACT
                if (name.isEmpty() || phoneNo.isEmpty()) {
                    Toast.makeText(
                            MainActivity.this,
                            "Please enter all the fields",
                            Toast.LENGTH_SHORT
                    ).show();
                } else {
                    try (DatabaseHandler db = new DatabaseHandler(MainActivity.this)) {
                        db.addContact(new Contact(name, phoneNo));
                        Toast.makeText(
                                MainActivity.this,
                                "Contact saved successfully",
                                Toast.LENGTH_SHORT).show();
                        finish();

                        // AFTER ADDING SHOWING THE ALL THE CONTACTS THERE IS
//                        List<Contact> getAllContacts = db.getAllContacts();
//                        for (Contact contact : getAllContacts) {
//                            Log.d(
//                                    "TAG",
//                                    "onStart Main Activity DatabaseHandler: " + contact.getName() + " " + contact.getPhoneNo()
//                            );
////                            System.out.println(contact.getName() + " " + contact.getPhoneNo());
//                        }

                    } catch (Exception e) {
                        Log.d(
                                "TAG",
                                "onStart Main Activity DatabaseHandler Entry Exception: " + e.getMessage()
                        );
                        Toast.makeText(
                                MainActivity.this,
                                "Error saving contact to database",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }

            }


        });

    }

}