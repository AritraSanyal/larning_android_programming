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
import android.graphics.Canvas;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

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

        // Enable Swipe-to-Delete functionality
        enableSwipeToDelete();


        // Temporary till some other solution is found
        appPref = getSharedPreferences(PREF_NAME, 0);
        boolean isChecked = appPref.getBoolean("dontShowAgain", false); // Default to false if not set

        SharedPreferences.Editor editor = appPref.edit();
        editor.putBoolean("dontShowAgain", false);
        editor.apply();

        // Fetch the initial Pokemon list
        getPokemonList();
    }

    private void enableSwipeToDelete() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // No drag & drop functionality, return false
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Get the swiped item's position
                int position = viewHolder.getAdapterPosition();

                appPref = getSharedPreferences(PREF_NAME,0);
                boolean isDontShowAgainChecked = appPref.getBoolean("dontShowAgain", false);

                if(isDontShowAgainChecked){
                    pokemonList.remove(position); // Remove the item from the list
                    pokemonAdapter.notifyItemRemoved(position); // Notify the adapter
                } else {
                    // Show the delete confirmation dialog
                    showDeleteDialog(viewHolder, position);
                }


            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                // Optional: Add custom swipe animation or drawing here
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // Attach the ItemTouchHelper to the RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(pokemonRecyclerView);
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
                }, 1000); // 2-second delay for simulation
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
    private void showDeleteDialog(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // Inflate dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.on_delete_dialog_box, null);
        cancelButotn = dialogView.findViewById(R.id.cancelButton);
        yesButton = dialogView.findViewById(R.id.yesButton);
        dontShowAgainCheckBox = dialogView.findViewById(R.id.dontShowAgainCheckBox);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        // Make sure the background click doesn't dismiss the dialog
        dialog.setCanceledOnTouchOutside(false);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }



        dontShowAgainCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            appPref = getSharedPreferences(PREF_NAME, 0);
            SharedPreferences.Editor editor = appPref.edit();
            editor.putBoolean("dontShowAgain", isChecked);
            editor.apply();
        });

        dialog.show(); // Show the dialog

        // Set Yes button action (delete item)
        yesButton.setOnClickListener(v -> {
            pokemonList.remove(position); // Remove the item from the list
            pokemonAdapter.notifyItemRemoved(position); // Notify the adapter
            dialog.dismiss(); // Dismiss the dialog
        });

        // Set Cancel button action (restore item)
        cancelButotn.setOnClickListener(v -> {
            pokemonAdapter.notifyItemChanged(position); // Restore the item
            dialog.dismiss(); // Dismiss the dialog
        });
    }
}