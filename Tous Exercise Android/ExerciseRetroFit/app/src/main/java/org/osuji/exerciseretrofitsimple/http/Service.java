package org.osuji.exerciseretrofitsimple.http;

import org.osuji.exerciseretrofitsimple.transfer.ModelComplex;
import org.osuji.exerciseretrofitsimple.transfer.ModelGithub;
import org.osuji.exerciseretrofitsimple.transfer.ModelList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {
    @GET("exos/long/double/{nombre}")
    Call<String> doubleOfNum(@Path("nombre") String nombre);

    @GET("exos/truc/complexe")
    Call<ModelComplex> objetComplex(@Query("name") String nom);

    @GET("exos/long/list")
    Call<List<Long>> longList();

    @GET("exos/truc/list")
    Call<List<ModelList>> objectList();

    @GET("users/departement-info-cem/repos")
    Call<List<ModelGithub>> infoGit();
}
