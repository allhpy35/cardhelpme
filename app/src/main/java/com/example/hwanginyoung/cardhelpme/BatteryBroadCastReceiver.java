package com.example.hwanginyoung.cardhelpme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

public class BatteryBroadCastReceiver extends BroadcastReceiver {
    protected TextView bateryquantity;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
            int nowBatteryquantity = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);

        }
    }

}
