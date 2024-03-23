package com.example.exerciseaccessreseau.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("exam/representations/{nombre}")
    Call<String> conversionString(@Path("nombre") String nombre);
    @GET("exam/representations/{nombre}")
    Call<List<Double>> Listconversion(@Path("nombre") String nombre);
}