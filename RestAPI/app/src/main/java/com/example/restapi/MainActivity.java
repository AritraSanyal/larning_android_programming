package com.example.restapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Canvas;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView pokemonRecyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<Pokemon> pokemonList;

    private Button cancelButotn;
    private Button yesButton;

    CheckBox dontShowAgainCheckBox;

    private SharedPreferences appPref;

    private static final String PREF_NAME = "appPref";

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
        // Attach the ItemTouchHelper to the RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(pokemonRecyclerView);

        // Fetch the initial Pokemon list
        getPokemonList();

        // Enable Swipe-to-Delete functionality
        enableSwipeToDelete();
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            // We are not handling drag & drop, so return false
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            View dialogView = getLayoutInflater().inflate(R.layout.on_delete_dialog_box, null);
            cancelButotn = dialogView.findViewById(R.id.cancelButton);
            yesButton = dialogView.findViewById(R.id.yesButton);
            dontShowAgainCheckBox = dialogView.findViewById(R.id.dontShowAgainCheckBox);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(dialogView);

            final AlertDialog dialog = builder.create();

            yesButton.setOnClickListener(v -> {
                // Remove the swiped item from the adapter
                int position = viewHolder.getAdapterPosition();
                pokemonList.remove(position);
                pokemonAdapter.notifyItemRemoved(position);
                //DISMISS THE DIALOG
                dialog.dismiss();
            });

            cancelButotn.setOnClickListener(v -> {
                pokemonAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                dialog.dismiss();
            });

            pokemonAdapter.notifyItemChanged(viewHolder.getAdapterPosition());


        }


    };

    private void enableSwipeToDelete() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // We don't need drag & drop functionality
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the swiped item's position
                int position = viewHolder.getAdapterPosition();

                // Remove the item from the dataset
                pokemonList.remove(position);

                // Notify the adapter about the removal
                pokemonAdapter.notifyItemRemoved(position);
            }
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                // Custom drawing code for swipe (optional)
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
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