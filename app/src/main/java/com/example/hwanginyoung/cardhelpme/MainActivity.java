package com.example.hwanginyoung.cardhelpme;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.Image;
import android.os.BatteryManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class  MainActivity extends AppCompatActivity {
    protected Button btcardcompany, btmapview, locat;
    protected Intent intent;
    protected Context content;
    protected BroadcastReceiver batteryReceiver;
    protected MyLocationListener myLocationListener;
    protected double latitude, longitude, altitude;
    protected LocationManager locationManager;
    protected TextView tvBattery;
    protected ImageView imagbtBattery;
   // protected MapView_main mapfragment;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //batteryReceiver = new BatteryBroadCastReceiver();
       // IntentFilter filter =new  IntentFilter(Intent.ACTION_BATTERY_CHANGED);
      //  this.registerReceiver(batteryReceiver,filter);

        //mapfragment = new MapView_main();
        imagbtBattery = (ImageView)findViewById(R.id.imagbtBattery);
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                tvBattery = (TextView)findViewById(R.id.tvBattery);
                int nowBatteryquantity = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
                if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                    tvBattery.setText("Battery :"+nowBatteryquantity+"%    ");

                    if(nowBatteryquantity <= 90){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_90_black_48dp);
                    }
                    else if(nowBatteryquantity <= 80){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_80_black_48dp);
                    }
                    else if(nowBatteryquantity <= 60){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_60_black_48dp);
                    }
                    else if(nowBatteryquantity <= 50){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_50_black_48dp);

                      //  Snackbar.make(this,"안녕","5");
                        //Snackbar.make(this,"배터리가 50% 이상입니다. 주의해주세요", Snackbar.LENGTH_LONG).show();
                    }
                    else if(nowBatteryquantity <= 30){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_30_black_48dp);
                        Toast.makeText(getApplicationContext(),"배터리가 30% 이하 입니다 주의해주세요",Toast.LENGTH_LONG).show();
                    }
                    else if(nowBatteryquantity <= 20){
                        imagbtBattery.setImageResource(R.drawable.baseline_battery_20_black_48dp);
                        Toast.makeText(getApplicationContext(),"배터리가 20% 이하 입니다 주의해주세요",Toast.LENGTH_LONG).show();
                    }


                }

                int plugIn = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plugIn) {

                    case BatteryManager.BATTERY_PLUGGED_AC:
                        tvBattery.append("어뎁터 충전 중...");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        tvBattery.append("USB 충전 중...");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                        tvBattery.append("무선 충전 중...");
                        break;
                }
            }
        };






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
                Snackbar.make(view, "안녕나는스낵바야", Snackbar.LENGTH_LONG).show();
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



    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, iFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
       unregisterReceiver(batteryReceiver);
    }
}
