package com.example.hwanginyoung.cardhelpme;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    protected Button btcardcompany, btmapview, locat;
    protected Intent intent;
    protected Context content;
    protected BroadcastReceiver batteryReceiver;
    protected MyLocationListener myLocationListener;
    protected double latitude, longitude, altitude;
    protected LocationManager locationManager;
   // protected MapView_main mapfragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mapfragment = new MapView_main();

        batteryReceiver = new BatteryBroadCastReceiver();

        btcardcompany = (Button) findViewById(R.id.btcardcompany);
        btcardcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), CardCompany.class);
                startActivity(intent);
            }
        });
        btmapview = (Button) findViewById(R.id.btmapview);
        btmapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MapView_main.class);
                startActivity(intent1);
               // getSupportFragmentManager().beginTransaction().replace(R.id.map, mapfragment).commit();
            }
        });
        //여기 밑에서부터 location 부분
        long minTime = 1000;
        float minDistance = 0;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        myLocationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, myLocationListener);
       // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, myLocationListener);
        locat = (Button)findViewById(R.id.testLocation);

        locat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocation();
            }
        });
        //여기 까지가 location 부분

    }


    private void showLocation() {

        latitude  = myLocationListener.latitude;
        longitude = myLocationListener.longitude;
        altitude = myLocationListener.altitude;

        Toast.makeText(this, "Latitude: " +latitude+", Longitude" +longitude+ ", Altitude" +altitude , Toast.LENGTH_SHORT).show();

    }


}
