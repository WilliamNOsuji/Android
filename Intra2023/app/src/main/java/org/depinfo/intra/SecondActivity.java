package org.depinfo.intra;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.depinfo.intra.databinding.ActivitySecondBinding;
import org.depinfo.intra.http.RetrofitUtils;
import org.depinfo.intra.http.Service;
import org.depinfo.intra.transfer.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;

    public SecondActivity() {
    }
    private ImageView img;
    private TextView name;
    private TextView weight;
    private TextView height;
    private TextView id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Service service = RetrofitUtils.get();

        // Si vous n'arrivez pas à obtenir le Id de l'activité principale, utilisez simplement l'id 25.
        // Cela devrait correspondre au Pokémon Pikachu
        img = binding.image;
        id = binding.tvIdPokemon;
        name = binding.tvNomPokemon;
        weight = binding.tvPoids;
        height = binding.tvHauteur;

        int pokemonId = getIntent().getIntExtra("idPoke", 25); // Default value is 25 if not found
        id.setText(String.valueOf(pokemonId));

        // Vous devez utiliser la méthode assignerImage pour afficher l'image dans l'interface


        service.Pokemon(id.getText().toString()).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if(response.isSuccessful()){
                    Pokemon pokemon = response.body();
                    assignerImage(img,pokemon.getId());
                    name.setText(pokemon.getNom());
                    weight.setText(pokemon.getPoids().toString());
                    height.setText(pokemon.getHauteur().toString());
                    id.setText("#" + id.getText().toString());
                    Toast.makeText(SecondActivity.this,"C'est " + name.getText().toString() + "!!!", Toast.LENGTH_SHORT).show();
                } else {
                    int responseCode = response.code();
                    Log.e("PokemonAPI", "Failed to fetch Pokemon data. Response code: " + responseCode);
                    Toast.makeText(SecondActivity.this, "Pokemon innaccessible! Response code: " + responseCode, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Échec de la Requete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Définir l'image du Pokémon selon son id.
     *
     * @param imageView Conteneur d'image sur lequel le Pokémon doit être ajoutée.
     * @param id        Id du Pokémon.
     */
    private void assignerImage(ImageView imageView, int id) {
        int imageId = getResources().getIdentifier("@drawable/p" + id, null, getPackageName());
        Drawable res = getResources().getDrawable(imageId, getTheme());
        imageView.setImageDrawable(res);
    }
}
