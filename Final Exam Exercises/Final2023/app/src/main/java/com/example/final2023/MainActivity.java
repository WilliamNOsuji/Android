package com.example.final2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.final2023.http.RetrofitUtil;
import com.example.final2023.http.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private FloatingActionButton btnSecond;
    private TextView resultRep;
    private TextInputEditText numGiven;
    private Button btnRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = findViewById(R.id.button);
        numGiven = findViewById(R.id.txtNombre);
        resultRep = findViewById(R.id.resultat);

        Service service = RetrofitUtil.get();

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = numGiven.getText().toString();
                service.divisionNombre(Integer.parseInt(nombre)).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String result = response.body();
                            // Convert integer to String before setting it to TextView
                            resultRep.setText(result);
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

        btnSecond = findViewById(R.id.actionBtn);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}