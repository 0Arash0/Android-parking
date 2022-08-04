package com.example.parking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ParkDatabase extends SQLiteOpenHelper{

    public static String DB_NAME = "park.db";
    public static int Version = 1;

    public ParkDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table tbl_parking(id Integer PRIMARY KEY autoincrement, vehicle varchar(100), " + "entryTime Text)" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}