package com.example.sqlite;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Data.DatabaseHadler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        DatabaseHadler db = new DatabaseHadler(MainActivity.this);
        db.addContact(new Contact("Aritra","000"));
        db.addContact(new Contact("Aparna","111"));

        List<Contact> allContactsFromDB = db.getAllContacts();
        for (Contact c : allContactsFromDB) {
        Log.d("TAG", "onCreate: " + c);
        }
    }
}