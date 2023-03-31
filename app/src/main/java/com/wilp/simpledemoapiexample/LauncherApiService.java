package com.wilp.simpledemoapiexample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LauncherApiService {
    @GET("launcherApi")
    Call<LauncherApi> getLauncherApiData(
            @Header("Content-Type") String contentType,
            @Header("Token") String token,
            @Header("Device-Type") String deviceType,
            @Header("api-key") String apiKey
    );
}
