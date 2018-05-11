package com.bairesdemo.javier.bairesdemo.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by vortex a on 08/11/2017.
 */

public interface Api {
    @GET("search/repositories")
    Call<GithubResponse> getProjects(@Query("q") String q, @Query("sort") String sort, @Query("order") String order);
}
