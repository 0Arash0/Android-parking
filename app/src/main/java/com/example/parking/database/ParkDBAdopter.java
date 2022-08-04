package com.example.parking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.parking.model.Vehcle;

import java.util.ArrayList;
import java.util.List;


public class ParkDBAdopter extends ParkDatabase{

    public ParkDBAdopter(@Nullable Context context) {
        super(context);
    }

    public long addVehicle (Vehcle vehicle){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //contentValues.put("id",vehicle.getId());
        contentValues.put("vehicle",vehicle.getVehicle());
        contentValues.put("entryTime",vehicle.getEntryTime());


       return db.insert("tbl_parking",null,contentValues);


    }

    public List<Integer> showPark(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbl_parking", null);

        List<Integer> parkListID = new ArrayList<>();

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String vehicle = cursor.getString(1);
            String enterTime = cursor.getString(2);

            parkListID.add(id);



        }

        return parkListID;

    }
}
