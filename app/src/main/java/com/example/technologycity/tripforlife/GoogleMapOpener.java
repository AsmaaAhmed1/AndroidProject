package com.example.technologycity.tripforlife;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by TECHNOLOGY CITY on 14/02/2018.
 */

public class GoogleMapOpener extends AppCompatActivity {
    PendingIntent pending_intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.no);

        Log.i("hna ana gwa al activity", "hna hayft7 google map ");

//        AlarmManager alarmManage;
//        alarmManage = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//        final Intent alarm_intent = new Intent(this,Alarm_receiver.class);
//        Intent my_intent=this.getIntent();
//        final int pending_id = Integer.parseInt((String) my_intent.getExtras().get("id"));
//
//        pending_intent=pending_intent.getBroadcast(GoogleMapOpener.this,pending_id
//                ,alarm_intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        dbConnection db=new dbConnection(GoogleMapOpener.this);
//
//        db.UpdatePastTrips(Integer.parseInt((String) my_intent.getExtras().get("id")));
//
//
//        alarm_intent.putExtra("Ex","off");
//
//        alarmManage.cancel(pending_intent);
//
//        sendBroadcast(alarm_intent);
//        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+(String) my_intent.getExtras().get("start_alt")+
//                ","+(String) my_intent.getExtras().get("start_lang")+"&daddr="+(String) my_intent.getExtras().get("end_alt")+", "+(String) my_intent.getExtras().get("end_lang")+""));
//        startActivity(intent);

        }
    }

