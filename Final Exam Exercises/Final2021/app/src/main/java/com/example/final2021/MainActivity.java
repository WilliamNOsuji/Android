package com.example.final2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.final2021.http.RetrofitUtil;
import com.example.final2021.http.Service;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtReponse;
    private Button btnEnvoye;
    private TextInputEditText txtNombre;

    private FloatingActionButton btnSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnvoye = findViewById(R.id.btnRequete);
        txtNombre = findViewById(R.id.nombreTxt);
        txtReponse = findViewById(R.id.reponse);

        Service service = RetrofitUtil.get();

        btnEnvoye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                service.divisionNombre(Integer.parseInt(nombre)).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.isSuccessful()) {
                            int result = response.body();
                            txtReponse.setText(String.valueOf(result));
                        } else {
                            // Handle error response from server
                            String errorBody = null;
                            try {
                                errorBody = response.errorBody().string();
                                Snackbar.make(MainActivity.this.getCurrentFocus(), errorBody, Snackbar.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        // Handle network errors
                        Snackbar.make(MainActivity.this.getCurrentFocus(), "Request failed: " + t.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Corrected type for btnSecondAct
        btnSecond = findViewById(R.id.btnSecondAct);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
