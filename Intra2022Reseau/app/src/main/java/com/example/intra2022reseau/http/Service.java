package com.example.intra2022reseau.http;
import com.example.intra2022reseau.transfer.Conversion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("{nombre}/{nombre2}")
    Call<String> conversionString(@Path("nombre") String nombre,@Path("nombre2") String nombre);
    @GET("{nombre}")
    Call<List<Conversion>> Listconversion(@Path("nombre") String nombre);
}
