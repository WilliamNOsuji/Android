package com.example.final2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.final2022.http.RetrofitUtil;
import com.example.final2022.http.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnSecond;
    private TextView txtResultat;
    private TextInputEditText mdpGiven;
    private Button buttonRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        btnSecond = findViewById(R.id.fltAcBtn);
        txtResultat = findViewById(R.id.resultatTxt);
        mdpGiven = findViewById(R.id.txtMdp);
        buttonRequest = findViewById(R.id.button);

        Service service = RetrofitUtil.get();

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mdpGivenTxt = mdpGiven.getText().toString();
                service.motDePasse(mdpGivenTxt).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String result = response.body();
                            // Convert integer to String before setting it to TextView
                            txtResultat.setText(result);
                        } else {
                            String errorBodyContent = "";
                            try {
                                errorBodyContent = response.errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Snackbar.make(MainActivity.this.getCurrentFocus(), errorBodyContent, Snackbar.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        // Handle network errors
                        Snackbar.make(MainActivity.this.getCurrentFocus(), "Requete au serveur echouer! ", Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}