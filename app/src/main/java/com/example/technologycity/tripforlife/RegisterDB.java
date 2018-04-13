package com.example.technologycity.tripforlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TECHNOLOGY CITY on 17/02/2018.
 */

public class RegisterDB extends SQLiteOpenHelper{
    public static final String db_name="register.sql";



    public RegisterDB(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS register (id INTEGER primary key," +
                " name TEXT NOT NULL, email " +
                "" +
                "TEXT, pass TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table IF EXISTS register");
        onCreate(db);
    }
    public void insertIntoRgister(String name , String email, String pass){
        SQLiteDatabase query=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("email",email);
        values.put("pass",pass);

        query.insert("register",null,values);
        Log.i("test","row inserted");

    }
    public boolean checkLogin(String email ,String password){
        SQLiteDatabase db =this.getReadableDatabase();
        String sql="select * from register where email =? and pass=? ";
        Cursor cur=db.rawQuery(sql,new String[]{email,password});
        if(cur.getCount()>0) return  true;
        else
             return false;
    }
}
