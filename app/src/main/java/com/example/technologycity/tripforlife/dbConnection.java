package com.example.technologycity.tripforlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class dbConnection extends SQLiteOpenHelper{
    TripInfo trips;
    public static final String db_name="trip.sql";
    public dbConnection(Context context) {
        super(context, db_name, null, 1);
    }

//    , trip_Longt TEXT, trip_lat TEXT

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS trip (id INTEGER primary key," +
                " trip_name TEXT NOT NULL, trip_start TEXT, trip_end TEXT,trip_time TEXT," +
                " date  DATETIME DEFAULT CURRENT_TIMESTAMP,start_lat TEXT,start_alt TEXT,end_lat TEXT" +
                ",end_alt TEXT,Past TEXT,roundTrip TEXT,note TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table IF EXISTS trip");
        onCreate(db);
    }
  public String getDateTime() {
        TripInfo t=new TripInfo();
    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
    String d=(sdf.format(t.getDate()));
      return  d;
   }
//   ,String longt , String lat
    public void insertIntoTrip(String name , String start, String end,
                               String tripdate,String time,String start_lat,
                               String start_alt,String end_lat,String end_alt,
                               String past,String rounTrip , String note){
        SQLiteDatabase query=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        values.put("trip_name",name);
        values.put("trip_time",time);
        values.put("trip_start",start);
        values.put("trip_end",end);
        values.put("date",tripdate);
        values.put("past",past);
        values.put("start_lat",start_lat);
        values.put("start_alt",start_alt);
        values.put("end_lat",end_lat);
        values.put("end_alt",end_alt);
        values.put("note",note);
        values.put("roundTrip",rounTrip);


        query.insert("trip",null,values);
        Log.i("test","row inserted");

    }
    public ArrayList<String> readFromTripRecent(){
         //trips=new TripInfo();
        ArrayList<String> tripsNames=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="select * from trip where past=?";
        String past="f";
        Cursor result=db.rawQuery(query,new String[]{past});

        result.moveToFirst();
        int i =0;
        String tripName ;
        while (result.isAfterLast()==false){
        tripName =result.getString(result.getColumnIndex("trip_name"));
        tripsNames.add(tripName);
            result.moveToNext();
            i++;
        };
        return  tripsNames;
    }

    public ArrayList<String> readFromTripPast(){
        //trips=new TripInfo();
        ArrayList<String> tripsNames=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="select * from trip where past=?";
        String past="t";
        Cursor result=db.rawQuery(query,new String[]{past});

        result.moveToFirst();
        int i =0;
        String tripName ;
        while (result.isAfterLast()==false){
            tripName =result.getString(result.getColumnIndex("trip_name"));
            tripsNames.add(tripName);
            result.moveToNext();
            i++;
        };
        return  tripsNames;
    }

    public ArrayList<TripInfo> getAllTripsObjRecent() {
        ArrayList<TripInfo> trips=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="select * from trip where past=?";
        String past="f";
        Cursor result=db.rawQuery(query,new String[]{past});
//        Cursor result=db.rawQuery("select * from trip  ",null);
        //result.moveToFirst();
        while (result.moveToNext()){
            TripInfo ti = new TripInfo();
            ti.setId(result.getInt(result.getColumnIndex("id")));
            ti.setName(result.getString(result.getColumnIndex("trip_name")));
            ti.setEnd(result.getString(result.getColumnIndex("trip_end")));
            ti.setStart(result.getString(result.getColumnIndex("trip_start")));
            ti.setDate(result.getString(result.getColumnIndex("date")));

            ti.setTime(result.getString(result.getColumnIndex("trip_time")));

            ti.setStart_lang(result.getString(result.getColumnIndex("start_lat")));

            ti.setStrat_alt(result.getString(result.getColumnIndex("start_alt")));

            ti.setEnd_lang(result.getString(result.getColumnIndex("end_lat")));

            ti.setEnd_alt(result.getString(result.getColumnIndex("end_alt")));

            ti.setPast(result.getString(result.getColumnIndex("Past")));
            ti.setNote(result.getString(result.getColumnIndex("note")));
            ti.setRoundTrip(result.getString(result.getColumnIndex("roundTrip")));


            trips.add(ti);

        }
        return trips;

    }
    public ArrayList<TripInfo> getAllTripsObjPast() {
        ArrayList<TripInfo> trips=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        String query ="select * from trip where past=?";
        String past="t";
        Cursor result=db.rawQuery(query,new String[]{past});
//        Cursor result=db.rawQuery("select * from trip  ",null);
        //result.moveToFirst();
        while (result.moveToNext()){
            TripInfo ti = new TripInfo();
            ti.setId(result.getInt(result.getColumnIndex("id")));
            ti.setName(result.getString(result.getColumnIndex("trip_name")));
            ti.setEnd(result.getString(result.getColumnIndex("trip_end")));
            ti.setStart(result.getString(result.getColumnIndex("trip_start")));
            ti.setDate(result.getString(result.getColumnIndex("date")));

            ti.setTime(result.getString(result.getColumnIndex("trip_time")));

            ti.setStart_lang(result.getString(result.getColumnIndex("start_lat")));

            ti.setStrat_alt(result.getString(result.getColumnIndex("start_alt")));

            ti.setEnd_lang(result.getString(result.getColumnIndex("end_lat")));

            ti.setEnd_alt(result.getString(result.getColumnIndex("end_alt")));

            ti.setPast(result.getString(result.getColumnIndex("Past")));
            ti.setNote(result.getString(result.getColumnIndex("note")));
            ti.setRoundTrip(result.getString(result.getColumnIndex("roundTrip")));


            trips.add(ti);

        }
        return trips;

    }


    public void updateTrip(int id,String name , String start, String end, String tripdate,
                           String time,String start_lat,String start_alt,String end_lat,
                           String end_alt,String note,String roundTrip) {
        SQLiteDatabase query=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        values.put("id",id);
        values.put("trip_name",name);
        values.put("trip_start",start);
        values.put("trip_end",end);
        values.put("date",tripdate);
        values.put("trip_time",time);
        values.put("start_lat",start_lat);
        values.put("start_alt",start_alt);
        values.put("end_lat",end_lat);
        values.put("end_alt",end_alt);
        values.put("roundTrip",roundTrip);
        values.put("note",note);


        query.update("trip",values,"id="+id,null);
        Log.i("test","row upated");

    }

    public String getFromTable(String name, String start, String end, String tripdate ,String tripTime) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "select * from trip where trip_name= ? and trip_start=? and date=? and trip_time=?";
//        Cursor c = db.rawQuery(query, new String[] {textValue, dateValue});
        Cursor result=db.rawQuery(query,new String[]{name,start,tripdate,tripTime});
        result.moveToFirst();
        String tripId =result.getString(result.getColumnIndex("id"));
        return tripId;
    }

    public TripInfo getTripById(String id) {
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "select * from trip where id =?";
        Cursor result=db.rawQuery(query,new String[]{(String) id});
        result.moveToFirst();
        TripInfo ti = new TripInfo();
//        ti.setId(Integer.parseInt(result.getString(result.getColumnIndex("id"))));
        ti.setName(result.getString(result.getColumnIndex("trip_name")));
        ti.setEnd(result.getString(result.getColumnIndex("trip_end")));
        ti.setStart(result.getString(result.getColumnIndex("trip_end")));
        ti.setTime(result.getString(result.getColumnIndex("trip_time")));
        ti.setDate(result.getString(result.getColumnIndex("date")));

        ti.setStart_lang(result.getString(result.getColumnIndex("start_lat")));

        ti.setStrat_alt(result.getString(result.getColumnIndex("start_alt")));

        ti.setEnd_lang(result.getString(result.getColumnIndex("end_lat")));

        ti.setEnd_alt(result.getString(result.getColumnIndex("end_alt")));

        ti.setPast(result.getString(result.getColumnIndex("Past")));
        ti.setNote(result.getString(result.getColumnIndex("note")));
        ti.setRoundTrip(result.getString(result.getColumnIndex("roundTrip")));

//        start_lat TEXT,start_alt TEXT,end_lat TEXT,end_alt TEXT
        return ti;

    }

    public void deleteFromDB(String id) {
        SQLiteDatabase query=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        Log.i("kkkkkkkk", "dh al id aly haytms7 "+id);
        values.put("id",id);

        query.delete("trip","id=?",new String[]{id});

    }

    public void UpdatePastTrips(int id)
    {
        SQLiteDatabase query=this.getWritableDatabase();
        ContentValues  values=new ContentValues();
        values.put("past","t");
        query.update("trip",values,"id="+id,null);
        Log.i("trip"+id,"updated to be past (t)");
    }
}

