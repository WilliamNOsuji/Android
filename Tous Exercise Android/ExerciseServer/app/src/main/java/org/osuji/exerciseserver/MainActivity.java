package org.osuji.exerciseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.osuji.exerciseserver.http.RetrofitUtil;
import org.osuji.exerciseserver.http.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnRand;
    TextView txtRand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRand = findViewById(R.id.btnRand);
        txtRand = findViewById(R.id.txtNum);
        btnRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandCall();
            }
        });
    }

    private void RandCall() {
        Service service = RetrofitUtil.get();
        Call<String> call = service.randomNum();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String result = response.body();
                    txtRand.setText(String.valueOf(result));
                } else {
                    // Handle error
                    showToast("Failed to fetch data from server");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // Handle failure
                showToast("Failed to make request");
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}