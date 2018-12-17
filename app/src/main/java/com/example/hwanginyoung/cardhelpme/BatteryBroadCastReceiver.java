package com.example.hwanginyoung.cardhelpme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryBroadCastReceiver extends BroadcastReceiver {
    protected TextView bateryquantity;
    protected MainActivity activity;
    protected BroadcastReceiver batteryReceiver;
    protected TextView tvBattery;
    protected ImageView imagbtBattery;
    @Override
    public void onReceive(Context context, Intent intent) {


            String action = intent.getAction();
            tvBattery = (TextView)activity.findViewById(R.id.tvBattery);
            imagbtBattery = (ImageView)activity.findViewById(R.id.imagbtBattery);
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
                    Toast.makeText(activity.content,"배터리가 30% 이하 입니다 주의해주세요",Toast.LENGTH_LONG).show();
                }
                else if(nowBatteryquantity <= 20){
                    imagbtBattery.setImageResource(R.drawable.baseline_battery_20_black_48dp);
                    Toast.makeText(activity.content,"배터리가 20% 이하 입니다 주의해주세요",Toast.LENGTH_LONG).show();
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


    }


