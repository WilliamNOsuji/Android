package org.depinfo.intra.http;

import org.depinfo.intra.transfer.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("{id}")
    Call<Pokemon> Pokemon(@Path("id") String id);
}
