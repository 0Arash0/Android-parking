package com.example.parking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;

import com.example.parking.R;
import com.example.parking.database.ParkDBAdopter;
import com.example.parking.database.ParkDatabase;

import java.util.List;

public class Remove extends AppCompatActivity {

    ParkDatabase parkDatabase;
    ParkDBAdopter parkDBAdopter;
    AppCompatEditText edt_id;
    AppCompatEditText edt_exitHour;
    AppCompatButton btn_remove;
    AppCompatTextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        parkDatabase = new ParkDatabase(this);
        parkDBAdopter = new ParkDBAdopter(getApplicationContext());


        edt_id = findViewById(R.id.getID);
        edt_exitHour = findViewById(R.id.exitH);
        btn_remove = findViewById(R.id.btn_remove);
        test = findViewById(R.id.test);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);



    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Integer> parkList = parkDBAdopter.showPark();
        Integer idExit = Integer.parseInt(edt_id.getText().toString());
        parkList.get(idExit);

    }
}