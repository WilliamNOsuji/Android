package com.example.intra2022reseau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.intra2022reseau.http.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.intra2022reseau.http.Service;
import com.example.intra2022reseau.transfer.Conversion;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // appeler un service de liste et afficher dans le log
        Service service = RetrofitUtil.get();

        service.Listconversion("200").enqueue(new Callback<List<Conversion>>() {
            @Override
            public void onResponse(Call<List<Conversion>> call, Response<List<Conversion>> response) {
                if (response.isSuccessful()) {
                    List<Conversion> resultat = response.body();
                    Log.i("Retrofit", resultat.size() + "");
                } else {
                    Log.i("Retrofit", response.code() + "");
                }

            }

            @Override
            public void onFailure(Call<List<Conversion>> call, Throwable t) {
                Log.i("Retrofit", t.getMessage());
            }
        });



        // appeler un service et mettre l'interface graphique
        final TextInputEditText et = findViewById(R.id.txtInput);
        final Button btn = findViewById(R.id.btn);

        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final MyAdapter adapter = new MyAdapter(new ArrayList<Conversion>());
        recyclerView.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = et.getText().toString();

                service.Listconversion(nombre).enqueue(new Callback<List<Conversion>>() {
                    @Override
                    public void onResponse(Call<List<Conversion>> call, Response<List<Conversion>> response) {
                        if (response.isSuccessful()) {
                            List<Conversion> conversionList = response.body();
                            adapter.setData(conversionList);
                        } else {
                            Log.i("RETROFIT", response.code()+"");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Conversion>> call, Throwable t) {
                        Log.i("RETROFIT", t.getMessage());
                    }
                });
            }
        });

    }
}