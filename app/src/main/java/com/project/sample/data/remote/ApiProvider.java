package com.project.sample.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiProvider {
    public static final String BASE_URL = "https://localhost/";


    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .build())
            .build();


    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
