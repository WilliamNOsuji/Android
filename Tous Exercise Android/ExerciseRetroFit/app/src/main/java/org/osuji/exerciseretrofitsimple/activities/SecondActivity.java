package org.osuji.exerciseretrofitsimple.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.osuji.exerciseretrofitsimple.adapters.MyAdapterListLong;
import org.osuji.exerciseretrofitsimple.adapters.MyAdapterListObject;
import org.osuji.exerciseretrofitsimple.databinding.ActivitySecondBinding;
import org.osuji.exerciseretrofitsimple.http.RetrofitUtil;
import org.osuji.exerciseretrofitsimple.http.Service;
import org.osuji.exerciseretrofitsimple.transfer.ModelList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    private RecyclerView recyclerViewLong;
    private MyAdapterListLong adapterListLong;

    private RecyclerView recyclerViewObject;

    private MyAdapterListObject adapterListObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Exercise LIST
        recyclerViewLong = binding.recyclerViewLong;
        recyclerViewLong.setLayoutManager(new LinearLayoutManager(this));
        LongListCall();

        recyclerViewObject = binding.recyclerViewObject;
        recyclerViewObject.setLayoutManager(new LinearLayoutManager(this));
        ObjectListCall();
    }

    private void LongListCall() {
        // Create Retrofit service instance
        Service service = RetrofitUtil.get();

        // Make network call to fetch list of long numbers
        Call<List<Long>> call = service.longList();
        call.enqueue(new Callback<List<Long>>() {
            @Override
            public void onResponse(Call<List<Long>> call, Response<List<Long>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update RecyclerView adapter with retrieved data
                    List<Long> dataList = response.body();
                    adapterListLong = new MyAdapterListLong(dataList);
                    recyclerViewLong.setAdapter(adapterListLong);
                } else {
                    // Handle error
                    // You can show a toast message or handle the error in another way
                    showToast("Failed to fetch data from server");
                }
            }

            @Override
            public void onFailure(Call<List<Long>> call, Throwable t) {
                // Handle failure
                // You can show a toast message or handle the failure in another way
                showToast("Failed to make network request");
            }
        });
    }

    private void ObjectListCall() {
        // Create Retrofit service instance
        Service service = RetrofitUtil.get();

        // Make network call to fetch list of long numbers
        Call<List<ModelList>> call = service.objectList();
        call.enqueue(new Callback<List<ModelList>>() {
            @Override
            public void onResponse(Call<List<ModelList>> call, Response<List<ModelList>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update RecyclerView adapter with retrieved data
                    List<ModelList> dataList = response.body();
                    adapterListObject = new MyAdapterListObject(dataList);
                    recyclerViewObject.setAdapter(adapterListObject);
                } else {
                    // Handle error
                    // You can show a toast message or handle the error in another way
                    showToast("Failed to fetch data from server");
                }
            }

            @Override
            public void onFailure(Call<List<ModelList>> call, Throwable t) {
                // Handle failure
                // You can show a toast message or handle the failure in another way
                showToast("Failed to make network request");
            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(SecondActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
