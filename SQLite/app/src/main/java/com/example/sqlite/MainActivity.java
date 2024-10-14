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

import Data.DatabaseHadler;
import Model.Contact;
import com.example.sqlite.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // DECLARING THE VIEWS
    private EditText nameEditText;
    private EditText phoneNoEditText;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // INITIALIZING THE VIEWS
        nameEditText = findViewById(R.id.nameEditText);
        phoneNoEditText = findViewById(R.id.phoneNoEditText);
        saveBtn = findViewById(R.id.saveBtn);

//        //CONNECTING WITH THE VIEW
//        try (DatabaseHadler db = new DatabaseHadler(MainActivity.this)) {
//            db.addContact(new Contact("Aritra", "000"));
//            db.addContact(new Contact("Aparna", "111"));
//            db.addContact(new Contact("Ashesh", "222"));
//            db.addContact(new Contact("Anamika", "333"));
//            db.addContact(new Contact("Arunima", "444"));
//            db.addContact(new Contact("Arindam", "555"));
//            db.addContact(new Contact("Avinash", "666"));
//            db.addContact(new Contact("Arin", "777"));
//            db.addContact(new Contact("Atrim", "888"));
//            db.addContact(new Contact("Ankita", "999"));
//
//            List<Contact> getAllContacts = db.getAllContacts();
//            for (Contact contact : getAllContacts) {
//                Log.d("TAG", "onCreate Main Activity DatabaseHandler: " + contact.getName() + " " + contact.getPhoneNo());
//                System.out.println(contact.getName() + " " + contact.getPhoneNo());
//            }
////            db.deleteAllContacts();
//
//        } catch (Exception e) {
//            Log.d("TAG", "onCreate Main Activity DatabaseHandler Exception: " + e.getMessage());
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phoneNo = phoneNoEditText.getText().toString();
                if (name.isEmpty() || phoneNo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    try (DatabaseHadler db = new DatabaseHadler(MainActivity.this)) {
                        db.addContact(new Contact(name, phoneNo));
                        Toast.makeText(MainActivity.this, "Contact saved successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.d("TAG", "onCreate Main Activity DatabaseHandler Entry Exception: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Error saving contact to database", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        try (DatabaseHadler db = new DatabaseHadler(MainActivity.this)) {
//            db.deleteAllContacts();
//            Toast.makeText(MainActivity.this, "All contacts deleted", Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.d("TAG", "onCreate Main Activity DatabaseHandler deleteAllContacts Exception: " + e.getMessage());
//        }
//    }


//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//
//        if(level == TRIM_MEMORY_UI_HIDDEN){
//            try (DatabaseHadler db = new DatabaseHadler(MainActivity.this)) {
//                db.deleteAllContacts();
//                Toast.makeText(MainActivity.this, "All contacts deleted", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                Log.d("TAG", "onCreate Main Activity DatabaseHandler deleteAllContacts Exception: " + e.getMessage());
//            }
//        }
//    }
}