package com.example.hwanginyoung.cardhelpme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    protected Button cardcompany, mapview;
    protected Intent intent;
    protected Context content;
    protected BroadcastReceiver batteryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryReceiver = new BatteryBroadCastReceiver();


        cardcompany = (Button)findViewById(R.id.cardcompany);
        cardcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), CardCompany.class );
                startActivity(intent);
            }
        });
        mapview = (Button)findViewById(R.id.mapview);
        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


}
