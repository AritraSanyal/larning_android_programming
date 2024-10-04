package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNext;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate Main called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onCreate Main");

        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name", "Aritra");
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart Main called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStart Main");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this, "onPostResume Main  called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPostResume Main");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause Main called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPause Main");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop Main called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStop Main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy Main called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onDestroy Main");
    }
}

