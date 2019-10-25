package com.amk.morris.API;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://morrisfa.eu-4.evennode.com";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            return new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
