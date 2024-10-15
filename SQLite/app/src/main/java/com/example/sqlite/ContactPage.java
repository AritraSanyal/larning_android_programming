package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.ContactAdapter;
import Data.DatabaseHandler;
import Model.Contact;

public class ContactPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private Button addContactButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_page);


        addContactButton = findViewById(R.id.addContactButton);
        recyclerView = findViewById(R.id.recyclerView);
        addContactButton = findViewById(R.id.addContactButton);
        deleteButton = findViewById(R.id.deleteButton);


        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactPage.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();

        // Initialize DatabaseHandler
        DatabaseHandler db = new DatabaseHandler(this);
        List<Contact> contactList = db.getAllContacts();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);

        // Handle delete button click
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int contactCount = contactList.size();
                db.deleteAllContacts();
            }
        });


    }
}