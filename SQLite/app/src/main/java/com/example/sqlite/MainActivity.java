package com.example.sqlite;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Data.DatabaseHadler;
import Model.Contact;
import com.example.sqlite.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        try (DatabaseHadler db = new DatabaseHadler(MainActivity.this)) {
            db.addContact(new Contact("Aritra", "000"));
            db.addContact(new Contact("Aparna", "111"));
//            db.addContact(new Contact("Ashesh", "222"));
//            db.addContact(new Contact("Anamika", "333"));
//            db.addContact(new Contact("Arunima", "444"));
//            db.addContact(new Contact("Arindam", "555"));
//            db.addContact(new Contact("Avinash", "666"));
//            db.addContact(new Contact("Arin", "777"));
//            db.addContact(new Contact("Atrim", "888"));
//            db.addContact(new Contact("Ankita", "999"));

            List<Contact> getAllContacts = db.getAllContacts();
            for (Contact contact : getAllContacts) {
                Log.d("TAG", "onCreate Main Activity DatabaseHandler: " + contact.getName() + " " + contact.getPhoneNo());
//                System.out.println(contact.getName() + " " + contact.getPhoneNo());
            }

        } catch (Exception e) {
            Log.d("TAG", "onCreate Main Activity DatabaseHandler Exception: " + e.getMessage());
        }

    }
}