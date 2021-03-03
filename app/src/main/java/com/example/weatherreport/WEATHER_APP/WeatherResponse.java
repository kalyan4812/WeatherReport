package com.example.weatherreport.WEATHER_APP;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    @SerializedName("main")
    public Main main;

    @SerializedName("sys")

    private Sys sys;
    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;

    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;

    public Integer getVisibility() {
        return visibility;
    }

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }
}
