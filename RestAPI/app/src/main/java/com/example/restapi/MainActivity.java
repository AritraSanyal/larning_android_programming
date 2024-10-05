package com.example.restapi;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        getPokemonList();
    }
    public void getPokemonList(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://dummyapi.online/api/pokemon", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    Log.d("TAG","onResponse: try block"+ response);

                } catch (Exception e){
                    Log.d("TAG","onResponse: catch block"+ e);
                }

            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","onErrorResponse: "+ error);

            }

        });
        requestQueue.add(jsonArrayRequest);
    }
}