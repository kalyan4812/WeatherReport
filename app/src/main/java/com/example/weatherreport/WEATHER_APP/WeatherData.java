package com.example.weatherreport.WEATHER_APP;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.weatherreport.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherData extends AppCompatActivity {


    LocationManager lm;
    double lati, longi;
    TextView tv1, tv2;
    EditText ed;
    ListView lv;
    ArrayList<String> al=new ArrayList<>();
    private Typeface mTypeface;
    ArrayAdapter<String>  mAdapter;
Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        lv = findViewById(R.id.lv);
        go=findViewById(R.id.go);
        mTypeface = Typeface.createFromAsset(getAssets(),"andika.ttf");
        go.setClickable(false);
        go.setVisibility(View.INVISIBLE);
        tv1 = findViewById(R.id.lat);
        tv2 = findViewById(R.id.lg);
       // ed = findViewById(R.id.place);
        int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1 == PackageManager.PERMISSION_GRANTED) {
            lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // to get current one.,for every onesecond it will update.

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lati = location.getLatitude();
                    longi = location.getLongitude();
                    tv1.setText("LATITUDE:" + lati);
                    tv2.setText("LONGITUDE:" + longi);
                    go.setText("  GET WEATHER IN YOUR LOCATION  ");
                    go.setVisibility(View.VISIBLE);
                    go.setClickable(true);



                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    // when gps is on it will be called.
                }

                @Override
                public void onProviderDisabled(String provider) {
                    // when gps is disabled it will be called
                }
            });

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 120);
        }
          mAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Cast the list view each item as text view
                TextView item = (TextView) super.getView(position,convertView,parent);

                // Set the typeface/font for the current item
                item.setTypeface(mTypeface);

                // Set the list view item's text color
                item.setTextColor(Color.parseColor("#03A9F4"));

                // Set the item text style to bold
                item.setTypeface(item.getTypeface(), Typeface.BOLD);

                // Change the item text size
                item.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);

                // return the view
                return item;
            }
        };


    }
    public void go(View view){
        Retrofit r = new Retrofit.Builder().baseUrl("http://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();
        WeatherInterface pi = r.create(WeatherInterface.class);

        retrofit2.Call<WeatherResponse> call = pi.getweather(lati,longi,"106a2f54ff7fcfe64f0a47467d85292d");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse w=response.body();
                al.clear();
               // Toast.makeText(getApplicationContext(),w.main.getTemp()+"",Toast.LENGTH_LONG).show();
                al.add("DESCRIPTION: "+w.getWeather().get(0).getDescription());
               al.add("TEMPERATURE: "+w.main.getTemp()+" °C");
                al.add("MINIMUM TEMPERATURE: "+w.main.getTemp_min()+" °C");
                al.add("MAXIMUM TEMPERATURE: "+w.main.getTemp_max()+" °C");
                al.add("PRESSURE: "+w.main.getPressure());
                al.add("HUMIDITY: "+w.main.getHumidity());
                al.add("WIND SPEED: "+w.getWind().getSpeed());
                al.add("VISIBILITY: "+w.getVisibility());
                al.add("PLACE: "+w.getName());
                al.add("COUNTRY: "+w.getSys().getCountry());
                al.add("SUNRISE: "+w.getSys().getSunrise());
                al.add("SUNSET: "+w.getSys().getSunset());


                lv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                lv.setVisibility(View.VISIBLE);
                go.setClickable(false);


            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 120:
                int permissioncheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
                int permissioncheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissioncheck == PackageManager.PERMISSION_GRANTED && permissioncheck1 == PackageManager.PERMISSION_GRANTED) {
                    lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                    lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            lati = location.getLatitude();
                            longi = location.getLongitude();
                            tv1.setText("LATITUDE:" + lati);
                            tv2.setText("LONGITUDE:" + longi);

                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {
                            // when gps is on it will be called.
                        }

                        @Override
                        public void onProviderDisabled(String provider) {
                            // when gps is disabled it will be called
                        }
                    });

                }


        }
    }
}
