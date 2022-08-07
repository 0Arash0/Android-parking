package com.example.parking.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.parking.model.Vehcle;

import java.util.ArrayList;
import java.util.List;


public class ParkDBAdopter extends ParkDatabase{


    public final static String KEY_ID = "_id";

    public ParkDBAdopter(@Nullable Context context) {
        super(context);
    }

    public boolean addVehicle (Vehcle vehicle){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("vehicle",vehicle.getVehicle());
        contentValues.put("entryTime",vehicle.getEntryTime());

        long result =db.insert("tbl_parking",null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public List<Integer> showParkID(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbl_parking", null);

        List<Integer> parkListID = new ArrayList<>();

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            parkListID.add(id);

        }
        return parkListID;
    }


   public List<String> showParkVehicle(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbl_parking", null);

        List<String> parkListVehicle = new ArrayList<>();

        while (cursor.moveToNext()) {

            String vehicle  = cursor.getString(1);
            parkListVehicle.add(vehicle);

        }
        return parkListVehicle;
    }


    public List<String> showParkTime(){

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from tbl_parking", null);

        List<String> parkListTime = new ArrayList<>();

        while (cursor.moveToNext()) {

            String enterTime  = cursor.getString(2);
            parkListTime.add(enterTime);

        }
        return parkListTime;
    }


    /*public Integer remove(String id){
        
        SQLiteDatabase database = getWritableDatabase();
        return database.delete("tbl_parking", "ID = ?", new String[] {id});


    }*/

}
