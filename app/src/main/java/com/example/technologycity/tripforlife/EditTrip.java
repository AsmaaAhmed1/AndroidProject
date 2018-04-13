package com.example.technologycity.tripforlife;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTrip extends AppCompatActivity {

    Button saveTrip;
    EditText triptxt;
    TextView starttxt;
    TextView endtxt;
    TextView datetxt;
    Button cancelbtn;
    Button delete;
    TextView timeOfEvent;
    CheckBox checkBox;
    EditText note;
    Button startTime;
    Button startDate;
    Button startPlace;
    Button endPlace;


    int PLACE_CODE=1;
    int PLACE_CODE_end=2;

    LatLng start_get;
    double start_alt;
    double start_lang;
    LatLng end_get;
    double end_alt;
    double end_lang;
    Context context;


    AlarmManager alarmManage;
    PendingIntent pending_intent;

    Calendar myCalendar = Calendar.getInstance();
    Calendar onTimeCalender= (Calendar) myCalendar.clone();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);


        context=this;
        alarmManage = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent my_intent = new Intent(this.context,Alarm_receiver.class);
        final Intent my_selected_trip = this.getIntent();



        //init values for this long alt



        start_alt= Double.parseDouble(my_selected_trip.getStringExtra("start_alt"));
        start_lang=Double.parseDouble(my_selected_trip.getStringExtra("start_lang"));
        end_lang=Double.parseDouble(my_selected_trip.getStringExtra("end_lang"));
        end_alt=Double.parseDouble(my_selected_trip.getStringExtra("end_alt"));

//        intent.putExtra("start_lang",finalTripsInfo.get((int) id).getStart_lang());
//        intent.putExtra("start_alt",finalTripsInfo.get((int) id).getStrat_alt());
//        intent.putExtra("end_lang",finalTripsInfo.get((int) id).getEnd_lang());
//        intent.putExtra("end_alt",finalTripsInfo.get((int) id).getEnd_alt());



        //------------------------






        Log.i("test",my_selected_trip.getStringExtra("name"));

        saveTrip = (Button) findViewById(R.id.save);
        triptxt = (EditText) findViewById(R.id.tripName);
        starttxt = (TextView) findViewById(R.id.start);
        endtxt = (TextView) findViewById(R.id.end);
        datetxt = (TextView) findViewById(R.id.tripdate);
        cancelbtn = (Button) findViewById(R.id.cancel);
        timeOfEvent = (TextView) findViewById(R.id.Timepi);
        delete=findViewById(R.id.button2);
        checkBox=findViewById(R.id.checkBox);
        note=findViewById(R.id.note);

        startTime=(Button)findViewById(R.id.StartTime);
        startDate=(Button)findViewById(R.id.StartDate);
        startPlace=(Button)findViewById(R.id.AddStart);
        endPlace=(Button)findViewById(R.id.AddEnd);


        triptxt.setText(my_selected_trip.getStringExtra("name"));
        starttxt.setText(my_selected_trip.getStringExtra("start"));
        endtxt.setText(my_selected_trip.getStringExtra("end"));
        datetxt.setText(my_selected_trip.getStringExtra("date"));
        timeOfEvent.setText(my_selected_trip.getStringExtra("time"));
        note.setText(my_selected_trip.getStringExtra("note"));

        if(my_selected_trip.getStringExtra("roundTrip").equals("t"))
        {
            checkBox.setChecked(true);
        }


        startPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getApplicationContext());
                    startActivityForResult(intent,PLACE_CODE);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });


        endPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getApplicationContext());
                    startActivityForResult(intent,PLACE_CODE_end);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        //for date time in start time


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        startDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditTrip.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });







//set time ll event
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY,hour);
                myCalendar.set(Calendar.MINUTE,minute);
                updateTime();
            }
        };

        startTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new TimePickerDialog(EditTrip.this, time, myCalendar
                        .get(Calendar.MINUTE), myCalendar.get(Calendar.HOUR_OF_DAY),true
                ).show();
            }
        });






//        datetxt.setText(my_selected_trip.getStringExtra("date"));


        //al save almfrod y3dl f aldatabase
        saveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=triptxt.getText().toString();
                dbConnection db=new dbConnection(EditTrip.this);
                if(name.matches("")||starttxt.getText().toString().matches("")||endtxt.getText().toString().matches("")||datetxt.toString().matches("")){
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(EditTrip.this, "YOU MUST ENTER ALL DATA", duration);
                    toast.show();
                }
                else {
                    if (myCalendar.compareTo(onTimeCalender) <= 0) {

                        Toast toast = Toast.makeText(EditTrip.this, "YOU MUST MAKE EVENT ON UP COMMING TIME", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        String checked = "f";
                        if (checkBox.isChecked()) {
                            checked = "t";
                        }

                        db.updateTrip(Integer.parseInt(my_selected_trip.getStringExtra("id"))
                                , triptxt.getText().toString(), starttxt.getText().toString(), endtxt.getText().toString()
                                , datetxt.getText().toString(), timeOfEvent.getText().toString(), String.valueOf(start_lang), String.valueOf(start_alt)
                                , String.valueOf(end_lang), String.valueOf(end_alt), note.getText().toString(), checked);
                        db.close();;

//                    Intent intent =new Intent(EditTrip.this,MainActivity.class);


                        int id = Integer.parseInt(db.getFromTable(triptxt.getText().toString(), starttxt.getText().toString(), endtxt.getText().toString(), datetxt.getText().toString(), timeOfEvent.getText().toString()));

//                    Toast toast = Toast.makeText(AddTrips.this, "id is    "+id+"Calender hour"+myCalendar.get(Calendar.HOUR_OF_DAY),Toast.LENGTH_LONG);
//                    toast.show();
                        ///////////////////////////////////////////////////////////////


//setting alarm service


                        my_intent.putExtra("Ex", "on");
                        my_intent.putExtra("id", String.valueOf(id));

//                    update_text.setText("alarm set hour   "+hour_starting+":"+minute_starting+"::Day"+day+"mo"+month+"ddd"+year);

                        pending_intent = pending_intent.getBroadcast(EditTrip.this, id
                                , my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


                        alarmManage.setExact(AlarmManager.RTC_WAKEUP, myCalendar.getTimeInMillis(), pending_intent);


                        triptxt.setText("");
                        starttxt.setText("");
                        endtxt.setText("");
                        datetxt.setText("");
                        finish();

                        Intent intent = new Intent(EditTrip.this, MainActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });





//cancel byrg3 mn 8er may3ml update
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditTrip.this,MainActivity.class);
                startActivity(intent);
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbConnection db=new dbConnection(EditTrip.this);
                db.deleteFromDB(my_selected_trip.getStringExtra("id"));

                Toast toast = Toast.makeText(EditTrip.this, "YOU MUST MAKE EVENT ON UP COMMING TIME"+my_selected_trip.getStringExtra("id"), Toast.LENGTH_LONG);
                toast.show();
                db.close();

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



                Intent intent=new Intent(EditTrip.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==PLACE_CODE)
        {
            if(resultCode==RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data,context);
                String address = String.format("%s",place.getAddress());
                starttxt.setText(address);
                start_get=place.getLatLng();
                start_alt=start_get.latitude;
                start_lang=start_get.longitude;

            }


        }else  if(requestCode==PLACE_CODE_end)
        {
            if(resultCode==RESULT_OK)
            {
                Place place = PlacePicker.getPlace(data,context);
                String address = String.format("%s",place.getAddress());
                endtxt.setText(address);
                end_get=place.getLatLng();
                end_lang=end_get.longitude;
                end_alt=end_get.latitude;
            }


        }
}
    private void updateTime() {
        timeOfEvent.setText(myCalendar.get(Calendar.HOUR_OF_DAY)+":"+myCalendar.get(Calendar.MINUTE));
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        datetxt.setText(sdf.format(myCalendar.getTime()));
    }
}
