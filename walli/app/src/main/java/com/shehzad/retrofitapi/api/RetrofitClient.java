package com.shehzad.retrofitapi.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient retrofitInstance = null;
    private Api api;
    private static final String base_url = "https://pixabay.com/";

    private RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if(retrofitInstance == null) retrofitInstance = new RetrofitClient();
        return retrofitInstance;
    }

    public Api getApi(){
        return api;
    }
}
