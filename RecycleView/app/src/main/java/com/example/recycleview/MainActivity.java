package com.example.recycleview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.MyAdapter;
import Models.ListItems;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcView;
    private MyAdapter adapter;

    private List<ListItems> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        rcView = findViewById(R.id.rcView);
        rcView.setHasFixedSize(true);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        for(int i = 0; i <= 10; i++){
            ListItems items = new ListItems("Title " + i+1, "description"+)
        }

        adapter = new MyAdapter(arrayList);
        rcView.setAdapter(adapter);


    }
}