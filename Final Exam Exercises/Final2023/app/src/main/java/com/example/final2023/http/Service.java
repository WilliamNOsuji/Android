package com.example.final2023.http;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {

    @GET("Exam2023/{nombre}")
    Call<String> divisionNombre(@Path("nombre") int nombre);
}