package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private Button btnNext;
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        Toast.makeText(this, "onCreate Second called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onCreate Second");

//        Bundle extras = getIntent().getExtras();
//        if (extras != null){
//            String name = extras.getString("name", "");
//        }


        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart Second called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStart Second");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this, "onPostResume Second  called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPostResume Second");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause Second called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPause Second");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop Second called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStop Second");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy Second called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onDestroy Second");
    }
}