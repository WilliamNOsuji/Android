package org.osuji.exerciseretrofitsimple.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.osuji.exerciseretrofitsimple.databinding.ActivityMainBinding;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtil;
import org.osuji.exerciseretrofitsimple.http.Service;
import org.osuji.exerciseretrofitsimple.transfer.ModelComplex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Button scdActivity;
    Button thrdActivity;

    /**
     *
     *  EXERCISE SIMPLE
     *
     */
    Button btnDouble;
    TextView txtDouble;

    /**
     *
     *  EXERCISE COMPLEX
     *
     */
    Button btnComplex;
    TextView txtNom;
    TextView txtA;
    TextView txtB;
    TextView txtC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Exercise SIMPLE
        btnDouble = binding.btnDouble;
        txtDouble = binding.txtNombre;

        btnDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NombreADoublerCall();
            }
        });

        // Exercise COMPLEX
        btnComplex = binding.btnComplex;
        txtNom = binding.txtNom;
        txtA = binding.txtA;
        txtB = binding.txtB;
        txtC = binding.txtC;

        btnComplex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectComplexCall();
            }
        });


        scdActivity = binding.button3;
        scdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        thrdActivity = binding.btnThird;
        thrdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     *
     *  EXERCISE SIMPLE METHODE
     *
     */
    private void NombreADoublerCall() {
        Service service = RetrofitUtil.get();
        String nombreString = txtDouble.getText().toString();
        Call<String> call = service.doubleOfNum(nombreString);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body();
                    // Show result as Toast
                    showToast("Result: " + result);
                } else {
                    // Handle error
                    showToast("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle failure
                showToast("Failed to make request");
            }
        });
    }

    /**
     *
     *  EXERCISE COMPLEX METHODE
     *
     */
    private void ObjectComplexCall() {
        Service service = RetrofitUtil.get();
        String nomString = txtNom.getText().toString();
        Call<ModelComplex> call = service.objetComplex(nomString);

        call.enqueue(new Callback<ModelComplex>() {
            @Override
            public void onResponse(Call<ModelComplex> call, Response<ModelComplex> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ModelComplex result = response.body();
                    txtA.setText(String.valueOf(result.a));
                    txtB.setText(result.b);
                    txtC.setText(result.c.toString());
                } else {
                    // Handle error
                    showToast("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<ModelComplex> call, Throwable t) {
                // Handle failure
                showToast("Failed to make request");
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}