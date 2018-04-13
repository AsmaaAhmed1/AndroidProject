package com.example.technologycity.tripforlife;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowPastDetails extends AppCompatActivity {
    TextView name;
    TextView start;
    TextView end;
    TextView date;
    TextView time;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_past_details);

        name=findViewById(R.id.Name);
        start=findViewById(R.id.startPoint);
        end=findViewById(R.id.endPoint);
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        delete=findViewById(R.id.delete);

        final Intent my_selected_trip = this.getIntent();

        name.setText(my_selected_trip.getStringExtra("name"));
        start.setText(my_selected_trip.getStringExtra("start"));
        end.setText(my_selected_trip.getStringExtra("end"));
        date.setText(my_selected_trip.getStringExtra("date"));
        time.setText(my_selected_trip.getStringExtra("time"));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbConnection db=new dbConnection(ShowPastDetails.this);
                db.deleteFromDB(my_selected_trip.getStringExtra("id"));

                PendingIntent pending_intent = null;
                AlarmManager alarmManage;
                final Intent alarm_intent = new Intent(getApplicationContext(),Alarm_receiver.class);
                final int pending_id = Integer.parseInt((String) my_selected_trip.getStringExtra("id"));


                alarmManage = (AlarmManager) getSystemService(ALARM_SERVICE);
                pending_intent=pending_intent.getBroadcast(getApplicationContext(),pending_id
                        ,alarm_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_intent.putExtra("Ex","off");

                alarmManage.cancel(pending_intent);

                sendBroadcast(alarm_intent);


                finish();
                Intent intent=new Intent(ShowPastDetails.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
