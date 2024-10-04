package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";
    private Button btnNext3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);

        Toast.makeText(this, "onCreate Third called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onCreate Third");

        btnNext3 = findViewById(R.id.btnNext3);

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_return_main = new Intent(ThirdActivity.this, MainActivity.class);
                startActivity(intent_return_main);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this, "onStart Third called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStart Third");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(this, "onPostResume Third  called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPostResume Third");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Toast.makeText(this, "onPause Third called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onPause Third");

    }

    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "onStop Third called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onStop Third");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy Third called...", Toast.LENGTH_LONG).show();
        Log.d(TAG, " onDestroy Third");
    }
}