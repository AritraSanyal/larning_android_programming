package com.example.restapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Adapter.PokemonAdapter;
import Model.Pokemon;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView pokemonRecyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the Volley RequestQueue
        requestQueue = Volley.newRequestQueue(MainActivity.this);

        // Initialize the SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        // Initialize RecyclerView and list
        pokemonRecyclerView = findViewById(R.id.pokemonListRecycleView);
        pokemonList = new ArrayList<>(); // Initialize the Pokemon list

        // Set LayoutManager for RecyclerView
        pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter and set it to RecyclerView
        pokemonAdapter = new PokemonAdapter(this, pokemonList);
        pokemonRecyclerView.setAdapter(pokemonAdapter); // Set the adapter

        // Fetch the initial Pokemon list
        getPokemonList();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set up Swipe-to-Refresh listener
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getPokemonList();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 4000); // 4-second delay for simulation
            }
        });
    }

    // Method to fetch the Pokemon list
    public void getPokemonList() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://dummyapi.online/api/pokemon",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            pokemonList.clear(); // Clear previous data
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                int id = jsonObject.getInt("id");
                                String name = jsonObject.getString("pokemon");
                                String type = jsonObject.getString("type");

                                // Parse the abilities array and convert it to a comma-separated string
                                JSONArray abilitiesArray = jsonObject.getJSONArray("abilities");
                                StringBuilder abilitiesBuilder = new StringBuilder();
                                for (int j = 0; j < abilitiesArray.length(); j++) {
                                    abilitiesBuilder.append(abilitiesArray.getString(j));
                                    if (j < abilitiesArray.length() - 1) {
                                        abilitiesBuilder.append(", ");
                                    }
                                }
                                String abilities = abilitiesBuilder.toString();

                                String imageURL = jsonObject.getString("image_url");
                                String location = jsonObject.getString("location");

                                // Create a Pokemon object and add it to the list
                                Pokemon pokemon = new Pokemon(id, name, type, abilities, imageURL, location);
                                pokemonList.add(pokemon);
                            }

                            // Notify the adapter that data has changed
                            pokemonAdapter.notifyDataSetChanged(); // Notify adapter for data change

                        } catch (Exception e) {
                            Log.d("TAG", "Exception in response handling: " + e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAG", "Error in response: " + error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest); // Add the request to the queue
    }
}