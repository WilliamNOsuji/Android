package org.osuji.exerciseretrofitsimple.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.osuji.exerciseretrofitsimple.adapters.MyAdapterListGithub;
import org.osuji.exerciseretrofitsimple.databinding.ActivityThirdBinding;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtil;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtilGithub;
import org.osuji.exerciseretrofitsimple.http.Service;
import org.osuji.exerciseretrofitsimple.transfer.ModelComplex;
import org.osuji.exerciseretrofitsimple.transfer.ModelGithub;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ThirdActivity extends AppCompatActivity {
    private ActivityThirdBinding binding;

    RecyclerView recyclerView;
    MyAdapterListGithub myAdapterListGithub;
    Button btnUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Exercise Github
        recyclerView = binding.recyclerViewGit;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        InfoGitCall();
    }

    private void InfoGitCall() {
        Service service = RetrofitUtilGithub.get();
        Call<List<ModelGithub>> call = service.infoGit();

        call.enqueue(new Callback<List<ModelGithub>>() {
            @Override
            public void onResponse(Call<List<ModelGithub>> call, Response<List<ModelGithub>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ModelGithub> result = response.body();
                    myAdapterListGithub = new MyAdapterListGithub(result);
                    recyclerView.setAdapter(myAdapterListGithub);
                } else {
                    // Handle error
                    showToast("Failed to fetch data from server");
                }
            }

            @Override
            public void onFailure(Call<List<ModelGithub>> call, Throwable t) {
                // Handle failure
                showToast("Failed to make request");
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(ThirdActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
