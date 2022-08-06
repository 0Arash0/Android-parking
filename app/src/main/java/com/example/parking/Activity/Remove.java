package com.example.parking.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.database.ParkDBAdopter;
import com.example.parking.database.ParkDatabase;

import java.util.ArrayList;
import java.util.List;


public class Remove extends AppCompatActivity {

    ParkDatabase parkDatabase;
    ParkDBAdopter parkDBAdopter;
    AppCompatEditText edt_id;
    AppCompatEditText edt_exitHour;
    AppCompatButton btn_remove;
    AppCompatImageView back;
    Integer idDelete = 0;
    List<Integer> listOfAllID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        parkDatabase = new ParkDatabase(this);
        parkDBAdopter = new ParkDBAdopter(getApplicationContext());
        listOfAllID = parkDBAdopter.showParkID();

        edt_id = findViewById(R.id.getID);
        edt_exitHour = findViewById(R.id.exitH);
        btn_remove = findViewById(R.id.btn_remove);
        back = findViewById(R.id.back_home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("First","Test");
                String deleteID = idDelete.toString();
                Integer deleteRow = parkDBAdopter.remove(deleteID);
                Log.e("","");
                if(deleteRow > 0) {
                    Toast.makeText(getApplicationContext(), "Remove Completed", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Remove Uncompleted",Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No" , null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> vehicleList = parkDBAdopter.showParkVehicle();
        List<String> timeList = parkDBAdopter.showParkTime();

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Integer id = Integer.parseInt(edt_id.getText().toString());
                id = id-1;*/
                Log.e("First","Test");
                idDelete = (listOfAllID.indexOf(Integer.parseInt(edt_id.getText().toString()))+1);
                String vehicle = vehicleList.get(idDelete-1);
                Integer cost=0;
                Log.e("","");


                /*if(edt_exitHour.getText().length()==0&&edt_id.getText().length()==0) {
                    Toast.makeText(getApplicationContext(),"Please Enter!",Toast.LENGTH_LONG).show();
                    return;
                }

                if(edt_exitHour.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Enter Time!",Toast.LENGTH_LONG).show();
                    return;
                }

                if(edt_id.getText().length()==0) {
                    Toast.makeText(getApplicationContext(),"enter ID!",Toast.LENGTH_LONG).show();
                    return;
                }*/
                if(vehicle.equals("car")){

                    Integer exit = Integer.parseInt(edt_exitHour.getText().toString());
                    Integer entry = Integer.parseInt(timeList.get(idDelete-1));
                    cost = 50000 + (exit-entry)*20000;

                }
                else if(vehicle.equals("bike")){

                    Integer exit = Integer.parseInt(edt_exitHour.getText().toString());
                    Integer entry = Integer.parseInt(timeList.get(idDelete-1));
                    cost = 30000 + (exit-entry)*10000;

                }

                //String toast = " Shoma Bayad Mablagh " + cost/10 + " Toman Variz Konid ";

                //Toast.makeText(getApplicationContext(),toast,Toast.LENGTH_LONG).show();


                StringBuffer buffer = new StringBuffer();
                buffer.append("ID: " + edt_id.getText().toString() + "\n");
                buffer.append("Vehicle: " + vehicle + "\n");
                buffer.append("EntryTime: " + timeList.get(idDelete-1) + "\n");
                buffer.append("ExitTime: " + edt_exitHour.getText().toString() + "\n");
                buffer.append("SoratHesab: " + cost/10 + " Toman " );
                showMessage("Delete Data", buffer.toString());




            }
        });
    }
}