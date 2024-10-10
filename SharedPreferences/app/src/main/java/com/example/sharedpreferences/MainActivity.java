package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText stdId, fName, lName, spec;
    private TextView stdIdTxtView, fNameTxtView, lNameTxtView, specTxtView;
    private Button btn;

    private SharedPreferences myPref;
    private static final String  PREF_NAME = "myPref", saved = "Saved !!!!!. Now quit and open";
    private String message;

    //TODO: add srudent related data, Studnet Id, First Name, Last Name, Specialization.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        stdId = findViewById(R.id.stdId);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        spec = findViewById(R.id.spec);
        stdIdTxtView = findViewById(R.id.stdIdTxtView);
        fNameTxtView = findViewById(R.id.fNameTxtView);
        lNameTxtView = findViewById(R.id.lNameTxtView);
        specTxtView = findViewById(R.id.specTxtView);
        btn = findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPref = getSharedPreferences(PREF_NAME, 0);

                //write on db/ shared pref
                SharedPreferences.Editor  editor = myPref.edit();
                // how to write
                editor.putString("stdId", stdId.getText().toString());
                editor.putString("fName", fName.getText().toString());
                editor.putString("lName", lName.getText().toString());
                editor.putString("spec", spec.getText().toString());
                // save the data into the shared preference.
                editor.commit();
                stdIdTxtView.setVisibility(View.VISIBLE);
                stdIdTxtView.setText(saved);
            }
        });

        //fetch the data from shared preference
        SharedPreferences pref = getSharedPreferences(PREF_NAME,0);
        if(pref.contains("stdId")){
            message = pref.getString("stdId", "No value found.");
            stdIdTxtView.setVisibility(View.VISIBLE);
            stdIdTxtView.setText(message);
        }
        if(pref.contains("fName")){
            message = pref.getString("fName", "No value found.");
            fNameTxtView.setVisibility(View.VISIBLE);
            fNameTxtView.setText(message);
        }

        if(pref.contains("lName")){
            message = pref.getString("lName", "No value found.");
            lNameTxtView.setVisibility(View.VISIBLE);
            lNameTxtView.setText(message);
        }

        if(pref.contains("spec")){
            message = pref.getString("spec", "No value found.");
            specTxtView.setVisibility(View.VISIBLE);
            specTxtView.setText(message);
        }


    }
}