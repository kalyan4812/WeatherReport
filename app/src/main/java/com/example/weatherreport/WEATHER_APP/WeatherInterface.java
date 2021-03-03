package com.example.weatherreport.WEATHER_APP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getweather(@Query("lat") double lat, @Query("lon") double lon, @Query("APPID") String app_id);
}
