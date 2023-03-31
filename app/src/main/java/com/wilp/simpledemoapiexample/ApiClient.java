package com.wilp.simpledemoapiexample;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://staging.greytrunk.cloudzmall.com/api/v1/";

    public static void getLauncherApiData(String contentType, String token, String deviceType, String apiKey, Callback<LauncherApi> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();

        LauncherApiService api = retrofit.create(LauncherApiService.class);

        Call<LauncherApi> call = api.getLauncherApiData(contentType, token, deviceType, apiKey);

        call.enqueue(callback);
    }
}
