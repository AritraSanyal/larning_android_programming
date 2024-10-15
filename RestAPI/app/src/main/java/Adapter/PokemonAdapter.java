package Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restapi.R;

import java.text.BreakIterator;
import java.util.List;

import Model.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private Context context;
    private List<Pokemon> pokemonList;

    public PokemonAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pokemon_card, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        holder.pokemonNameTextView.setText(pokemon.getName());
        holder.pokemonTypeTextView.setText(pokemon.getType());
        holder.pokemonAttributesTextView.setText(pokemon.getAbilities());
        holder.pokemonIDTextView.setText(String.valueOf(pokemon.getId()));
        holder.pokemonLocationTextView.setText(pokemon.getLocation());

        // Load the image using Glide or any other image loading library
        Glide.with(context)
                .load(pokemon.getImageURL())
                .into(holder.pokemonImageView);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {


        TextView pokemonNameTextView, pokemonTypeTextView, pokemonAttributesTextView, pokemonIDTextView,pokemonLocationTextView;
        ImageView pokemonImageView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonNameTextView = itemView.findViewById(R.id.pokemonNameTextView);
            pokemonTypeTextView = itemView.findViewById(R.id.pokemonTypeTextView);
            pokemonAttributesTextView = itemView.findViewById(R.id.pokemonAttributesTextView);
            pokemonIDTextView = itemView.findViewById(R.id.pokemonIDTextView);
            pokemonImageView = itemView.findViewById(R.id.pokemonImageView);
            pokemonLocationTextView = itemView.findViewById(R.id.pokemonLocationTextView);
        }
    }
}