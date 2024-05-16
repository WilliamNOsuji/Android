package com.example.final2021;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final2021.http.RetrofitUtil;
import com.example.final2021.http.Service;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {

    private TextView txtAttente;
    private Button btnServeur;
    private ProgressBar progressIndicator;

    private TextView splash1;
    private TextView splash2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progressIndicator = findViewById(R.id.progressBar);

        btnServeur = findViewById(R.id.btnWait);
        progressIndicator.setVisibility(View.INVISIBLE);
        btnServeur.setEnabled(true);
        Service service = RetrofitUtil.get();

        splash1 = findViewById(R.id.splash1);
        splash2 = findViewById(R.id.splash2);

        splash1.setVisibility(View.INVISIBLE);
        splash2.setVisibility(View.INVISIBLE);

        btnServeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressIndicator.setVisibility(View.VISIBLE);
                btnServeur.setEnabled(false);
                splash1.setVisibility(View.VISIBLE);
                splash2.setVisibility(View.VISIBLE);

                service.attente().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        progressIndicator.setVisibility(View.GONE);
                        btnServeur.setEnabled(true);
                        splash1.setVisibility(View.GONE);
                        splash2.setVisibility(View.GONE);
                        if (response.isSuccessful()) {

                        } else {
                            String errorBodyContent = "";
                            try {
                                errorBodyContent = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(SecondActivity.this.getCurrentFocus(), errorBodyContent, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressIndicator.setVisibility(View.GONE);
                        splash1.setVisibility(View.GONE);
                        splash2.setVisibility(View.GONE);
                        btnServeur.setEnabled(true);
                        // Handle network errors
                        Snackbar.make(SecondActivity.this.getCurrentFocus(), "Requete au serveur echouer! ", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
