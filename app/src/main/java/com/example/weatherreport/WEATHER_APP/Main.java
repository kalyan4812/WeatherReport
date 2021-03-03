package com.example.weatherreport.WEATHER_APP;

import com.google.gson.annotations.SerializedName;

public class Main {

        @SerializedName("temp")
        public float temp;
        @SerializedName("humidity")
        public float humidity;
        @SerializedName("pressure")
        public float pressure;
        @SerializedName("temp_min")
        public float temp_min;

        public float getTemp() {
            return temp-273;
        }

        public void setTemp(float temp) {
            this.temp = temp;
        }

        public float getHumidity() {
            return humidity;
        }

        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }

        public float getPressure() {
            return pressure;
        }

        public void setPressure(float pressure) {
            this.pressure = pressure;
        }

        public float getTemp_min() {
            return temp_min-273;
        }

        public void setTemp_min(float temp_min) {
            this.temp_min = temp_min;
        }

        public float getTemp_max() {
            return temp_max-273;
        }

        public void setTemp_max(float temp_max) {
            this.temp_max = temp_max-273;
        }

        @SerializedName("temp_max")
        public float temp_max;
    }


