package com.example.randombgcolochanger;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;


public class MainActivity extends AppCompatActivity {

    Button btn1;
    RelativeLayout main;

    int red, green, blue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        main = findViewById(R.id.main);

        btn1.setOnClickListener(v -> {
            red = (int) (Math.random()*(256));
            green = (int) (Math.random()*(256));
            blue = (int) (Math.random()*(256));
            main.setBackgroundColor(Color.rgb(red,green,blue));
        });

    }
}