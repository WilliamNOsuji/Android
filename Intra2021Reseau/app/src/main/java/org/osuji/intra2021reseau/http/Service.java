package org.osuji.intra2021reseau.http;

import org.osuji.intra2021reseau.transfer.Fete;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("{annee}/{mois}/{jour}")
    Call<List<Fete>> listFete(@Path("annee") String annee,
                              @Path("mois") String mois,
                              @Path("jour") String jour);
}
