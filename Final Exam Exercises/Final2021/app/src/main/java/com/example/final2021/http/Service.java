package com.example.final2021.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("api/final/1/{nombre}")
    Call<Integer> divisionNombre(@Path("nombre") int nombre);

    @GET("api/final/0")
    Call<String> attente();
}