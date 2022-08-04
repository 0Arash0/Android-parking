package com.example.parking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parking.model.Vehcle;


public class ParkDBAdopter extends ParkDatabase{

    public ParkDBAdopter(@Nullable Context context) {
        super(context);
    }

    public long addVehcle (Vehcle vehcle){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ID",vehcle.getId());
        contentValues.put("Vehcle",vehcle.getVehicle());
        contentValues.put("enterTime",vehcle.getEntryTime());


       return db.insert("tbl_parking",null,contentValues);


    }
}
