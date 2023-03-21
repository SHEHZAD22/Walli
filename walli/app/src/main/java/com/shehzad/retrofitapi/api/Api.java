package com.shehzad.retrofitapi.api;

import com.shehzad.retrofitapi.model.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String key = "api/?key=26264029-9a2c93ee1b25f590d6ba87337";

    @GET(key)
    Call<ImageModel> getImageListPerPage(@Query("q") String q, @Query("page") int page, @Query("per_page") int perPage);

    //only fetching response with query
    @GET(key)
    Call<ImageModel> getImageList(@Query("page") int page);
}
