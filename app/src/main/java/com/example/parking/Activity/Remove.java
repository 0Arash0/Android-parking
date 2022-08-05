package com.example.parking.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.database.Cursor;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        parkDatabase = new ParkDatabase(this);
        parkDBAdopter = new ParkDBAdopter(getApplicationContext());

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
                String vehicle = vehicleList.get(Integer.parseInt(edt_id.getText().toString())-1);
                Integer cost=0;


                if(edt_exitHour.getText().length()==0&&edt_id.getText().length()==0) {
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
                }

                if(vehicle=="car"){

                    Integer exit = Integer.parseInt(edt_exitHour.getText().toString());
                    Integer entry = Integer.parseInt(timeList.get(Integer.parseInt(edt_id.getText().toString())-1));
                    cost = 50000 + (exit-entry)*20000;

                }
                else {

                    Integer exit = Integer.parseInt(edt_exitHour.getText().toString());
                    Integer entry = Integer.parseInt(timeList.get(Integer.parseInt(edt_id.getText().toString())-1));
                    cost = 30000 + (exit-entry)*10000;

                }

                String toast = " Shoma Bayad Mablagh " + cost/10 + " Toman Variz Konid ";

                Toast.makeText(getApplicationContext(),toast,Toast.LENGTH_LONG).show();


                StringBuffer buffer = new StringBuffer();
                buffer.append("ID: " + edt_id.getText().toString() + "\n");
                buffer.append("Vehicle: " + vehicle + "\n");
                buffer.append("EntryTime: " + timeList.get(Integer.parseInt(edt_id.getText().toString())-1) + "\n");
                buffer.append("ExitTime: " + edt_exitHour.getText().toString() + "\n");
                showMessage("Date", buffer.toString());


                Integer deleteRow = parkDBAdopter.remove(edt_id.getText().toString());

                if(deleteRow > 0)
                    Toast.makeText(getApplicationContext(),"Remove Completed",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Remove UnCompleted",Toast.LENGTH_LONG).show();


            }
        });
    }
}