package com.example.final2022.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("exam2022/motdepasse/{passe}")
    Call<String> motDePasse(@Path("passe") String passe);
}
