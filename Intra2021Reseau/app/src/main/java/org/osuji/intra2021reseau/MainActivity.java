package org.osuji.intra2021reseau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import org.osuji.intra2021reseau.http.RetrofitUtil;
import org.osuji.intra2021reseau.http.Service;
import org.osuji.intra2021reseau.transfer.Fete;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Service service = RetrofitUtil.get();
        service.listFete("2001","05","10").enqueue(new Callback<List<Fete>>() {
            @Override
            public void onResponse(Call<List<Fete>> call, Response<List<Fete>> response) {
                if(response.isSuccessful()){
                    List<Fete> resultat = response.body();
                    Log.i("Retrofit", resultat.size() + "");
                } else {
                    Log.i("Retrofit", response.code()+"");
                }

            }

            @Override
            public void onFailure(Call<List<Fete>> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });

        final TextInputEditText annee = findViewById(R.id.annee);
        final TextInputEditText mois = findViewById(R.id.mois);
        final TextInputEditText jour = findViewById(R.id.jour);

        final Button btnReseau = findViewById(R.id.btnReseau);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        btnReseau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fete = annee.getText().toString()
                        + "/" + mois.getText().toString()
                        + "/" + jour.getText().toString();
                service.listFete(fete).enqueue(new Callback<List<Fete>>() {
                    @Override
                    public void onResponse(Call<List<Fete>> call, Response<List<Fete>> response) {
                        if(response.isSuccessful()){
                            recyclerView.add(response.body().jourDeLaSemaine+"");
                        }
                        else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Fete>> call, Throwable t) {

                    }
                });
            }
        });
    }
}