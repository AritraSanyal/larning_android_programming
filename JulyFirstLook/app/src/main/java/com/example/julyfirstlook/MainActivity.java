package com.example.julyfirstlook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txt1,txt2,txt3, txt4;
    private EditText edText1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        edText1 = findViewById(R.id.edText);
        btn1 = findViewById(R.id.btn1);
        txt4 = findViewById(R.id.txt4);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt4.setVisibility(View.VISIBLE);
                if(edText1.getText().length() != 0) {
                    txt4.setText(edText1.getText());
                }
                else{
                    txt4.setText("No input in EditText");
                }
            }
        });
    }

}
