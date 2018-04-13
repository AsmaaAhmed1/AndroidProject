package com.example.technologycity.tripforlife;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Calendar;

public class DetailsOfEvent extends AppCompatActivity {
    boolean check_change=false;

    TextView name;
    TextView start;
    TextView end;
    TextView date;
    TextView time;
    Button unsetAlarm;
    Button snooze;
    Button Startbtn;
    Button lol;
    NotificationCompat.Builder builder;

    Dialog dialog;

    Context context;

    AlarmManager alarmManage;
    PendingIntent pending_intent;
    PendingIntent pending_intent_send_Again;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_event);

        this.context=this;



        alarmManage = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent my_intent = this.getIntent();

        final Intent alarm_intent = new Intent(this.context,Alarm_receiver.class);

        final Intent alarm_intent_again = new Intent(this.context,Alarm_receiver.class);
        final Calendar calendar = Calendar.getInstance();







        dialog = new Dialog(DetailsOfEvent.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);
        dialog.setTitle("dialog Custom");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

//        lol = dialog.findViewById(R.id.startTrip);
        name=dialog.findViewById(R.id.name);
        start=dialog.findViewById(R.id.start);
        end =dialog.findViewById(R.id.end);
        date=dialog.findViewById(R.id.date);
        time =dialog. findViewById(R.id.time);
        unsetAlarm=dialog.findViewById(R.id.endBtn);
        snooze=dialog.findViewById(R.id.snooze);
        Startbtn=dialog.findViewById(R.id.startBtn);







//        lol.setEnabled(true);
        name.setEnabled(true);
        start.setEnabled(true);
        end.setEnabled(true);
        date.setEnabled(true);
        time.setEnabled(true);
        unsetAlarm.setEnabled(true);
        snooze.setEnabled(true);
        Startbtn.setEnabled(true);


//        lol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext() , "sssss" , Toast.LENGTH_LONG).show();
//            }
//        });

//        dialog.show();




        //dh al custom dialog





        ///////////////////////////////////////////


        dbConnection db=new dbConnection(this);

        //mohm


        Log.i("laaaeezssaas", (String) my_intent.getExtras().get("id"));

        final TripInfo tpi = db.getTripById((String)my_intent.getExtras().get("id"));

        name.setText(tpi.getName());
        date.setText(tpi.getDate());
        start.setText(tpi.getStart());
        end.setText(tpi.getEnd());
        time.setText(tpi.getNote());

        final  String x = (String) my_intent.getExtras().get("id");

        final int pending_id = Integer.parseInt((String) my_intent.getExtras().get("id"));


        pending_intent=pending_intent.getBroadcast(DetailsOfEvent.this,pending_id
                ,alarm_intent,PendingIntent.FLAG_UPDATE_CURRENT);





        Startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_change=true;

                dbConnection db=new dbConnection(DetailsOfEvent.this);
                db.UpdatePastTrips(Integer.parseInt(x));

                db.close();

                alarm_intent.putExtra("Ex","off");

                alarmManage.cancel(pending_intent);

                sendBroadcast(alarm_intent);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+tpi.getStrat_alt()+
                        ","+tpi.getStart_lang()+"&daddr="+tpi.getEnd_alt()+", "+tpi.getEnd_lang()+""));
                startActivity(intent);
            }
        });



       final NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);


        unsetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_change=true;

                dbConnection db=new dbConnection(DetailsOfEvent.this);
                db.UpdatePastTrips(Integer.parseInt(x));
                db.close();


        notificationManager.cancelAll();


                alarm_intent.putExtra("Ex","off");

                alarmManage.cancel(pending_intent);

                sendBroadcast(alarm_intent);

                finish();


            }
        });

        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                check_change=true;


            //the intent to go to main activirty
            Intent intent_notify = new Intent(context,DetailsOfEvent.class);

                intent_notify.putExtra("id",(String)my_intent.getExtras().get("id"));

                Log.i("de al id w ana b3mlo", " "+(String)my_intent.getExtras().get("id"));

                intent_notify.putExtra("name",(String)my_intent.getExtras().get("name"));
                intent_notify.putExtra("end",(String)my_intent.getExtras().get("end"));
                intent_notify.putExtra("start",(String)my_intent.getExtras().get("start"));
                intent_notify.putExtra("date",(String)my_intent.getExtras().get("date"));
                intent_notify.putExtra("time",(String)my_intent.getExtras().get("time"));

                intent_notify.putExtra("start_lang",(String)my_intent.getExtras().get("start_lang"));
                intent_notify.putExtra("start_alt",(String)my_intent.getExtras().get("start_alt"));
                intent_notify.putExtra("end_lang",(String)my_intent.getExtras().get("end_lang"));
                intent_notify.putExtra("end_alt",(String)my_intent.getExtras().get("end_alt"));


//                NotificationManager notificationManager = (NotificationManager)
//                    getSystemService(NOTIFICATION_SERVICE);
//                notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
//                RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
//
//                remoteViews.setTextViewText(R.id.textView,(String)my_intent.getExtras().get("name"));
//                remoteViews.setProgressBar(R.id.progressBar,100,20,true);
//
//                //Intent stop = new Intent();
////                Intent intent_notify = new Intent(this.getApplicationContext(),MainActivity.class);
//                int not_id = (int) System.currentTimeMillis();
//                intent_notify.putExtra("id",not_id);
//
//                PendingIntent pendingIntent =  PendingIntent.getActivity(getApplicationContext(),Integer.valueOf((String) my_intent.getExtras().get("id")),intent_notify,0);
//
//                remoteViews.setOnClickPendingIntent(R.id.button,pendingIntent);
//
//
//                builder=new NotificationCompat.Builder(context);
//                builder.setSmallIcon(R.drawable.depositphotos)
//                        .setAutoCancel(true).
//                        setCustomContentView(remoteViews).setContentIntent(pendingIntent)
//                ;
//
//                notificationManager.notify(not_id,builder.build());


            PendingIntent pendingIntent =  PendingIntent.getActivity(context,0,intent_notify,0);

            Notification notification = new Notification.Builder(context)
                    .setContentTitle("You Have Snoozed event Check it !!")
                    .setContentText("Open activity ")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.depositphotos)
                    .setAutoCancel(true)
                    .build();

            notification.flags=Notification.FLAG_AUTO_CANCEL;

            // set notification to notification manager
            notificationManager.notify(0,notification);
// ----------------------------------------------








                alarm_intent.putExtra("Ex","off");

                alarmManage.cancel(pending_intent);

                sendBroadcast(alarm_intent);


                ////////////////////////////////////////////////////




                calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+1);



                alarm_intent_again.putExtra("Ex","on");
                alarm_intent_again.putExtra("id",String.valueOf(pending_id));


                //  update_text.setText("alarm set hour   "+hour_starting+":"+minute_starting+"::Day"+day+"mo"+month+"ddd"+year);

                Log.i("id", "dh al id aly ray7 ll intent"+pending_id);

                pending_intent_send_Again=pending_intent_send_Again.getBroadcast(DetailsOfEvent.this,pending_id
                        ,alarm_intent_again,PendingIntent.FLAG_UPDATE_CURRENT);



                alarmManage.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending_intent_send_Again);

                finish();

            }
        });


        dialog.show();

    }


//    public void MyCustomDialog()
//    {
//        dialog = new Dialog(DetailsOfEvent.this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.customdialog);
//        dialog.setTitle("dialog Custom");
//        lol = dialog.findViewById(R.id.startTrip);
//        lol.setEnabled(true);
//        lol.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext() , "sssss" , Toast.LENGTH_LONG).show();
//            }
//        });
//
//    dialog.show();
//
//    }

    @Override
    protected void onPause() {
        super.onPause();

//        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final Intent my_intent = this.getIntent();
        final  String x = (String) my_intent.getExtras().get("id");

        if(check_change==false)

        {
            Log.i("hna lo gwa al if", x);
            final NotificationManager notificationManager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            final Intent alarm_intent = new Intent(this.context,Alarm_receiver.class);
            dbConnection db=new dbConnection(DetailsOfEvent.this);
            db.UpdatePastTrips(Integer.parseInt(x));
            db.close();


            notificationManager.cancelAll();


            alarm_intent.putExtra("Ex","off");

            alarmManage.cancel(pending_intent);

            sendBroadcast(alarm_intent);

            finish();




        }
    }
}
