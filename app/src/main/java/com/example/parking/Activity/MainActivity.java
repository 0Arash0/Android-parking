package com.example.parking.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.database.ParkDBAdopter;
import com.example.parking.database.ParkDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ParkDatabase parkDatabase;
    ParkDBAdopter parkDBAdopter;
    AppCompatButton Button_1;
    AppCompatButton Button_2;
    AppCompatButton Button_3;
    AppCompatButton Button_view;
    List<Integer> allIDs ;
    List<String> allVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parkDatabase = new ParkDatabase(this);
        parkDBAdopter = new ParkDBAdopter(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_1 = findViewById(R.id.Button_1);
        Button_2 = findViewById(R.id.Button_2);
        Button_3 = findViewById(R.id.Button_3);
        Button_view = findViewById(R.id.Button_view);


        Button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Add.class);
                startActivity(intent);
            }
        });

        Button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Remove.class);
                startActivity(intent);
            }
        });

        Button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }

    public void showMessage(String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("View All Data");
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        allIDs = parkDBAdopter.showParkID();
        allVehicle = parkDBAdopter.showParkVehicle();

        Button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(allIDs.size()==0)
                {
                    Toast.makeText(MainActivity.this, "NO Data", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                for (int i =0 ; i<=allIDs.indexOf(allIDs.get(allIDs.size()-1)) ; i++){
                    int z=i;
                    z++;
                    buffer.append("ID : " + allIDs.get(i) + "\n");
                    buffer.append("Vehicle : " + allVehicle.get(i) + "\n");
                    buffer.append("Row : " + z + "\n");
                    buffer.append("\n");

                }
                showMessage(buffer.toString());

            }
        });
    }
}