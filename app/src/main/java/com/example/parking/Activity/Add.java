package com.example.parking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.viewpager.widget.PagerAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.parking.R;
import com.example.parking.database.ParkDBAdopter;
import com.example.parking.model.Vehcle;

public class Add extends AppCompatActivity {

   AppCompatButton btn_add;
    ParkDBAdopter parkDBAdopter;
    AppCompatEditText edt_time;
    AppCompatRadioButton car_btn , bike_btn;
    RadioGroup radioGroup;
    String vehicle;
    Integer ids = 0;

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edt_time=findViewById(R.id.edt_time);
        car_btn=findViewById(R.id.car_btn);
        bike_btn=findViewById(R.id.bike_btn);
        radioGroup=findViewById(R.id.grp_btn);
        btn_add=findViewById(R.id.btn_add);

        parkDBAdopter = new ParkDBAdopter(getApplicationContext());


      btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Vehcle vehcle=new Vehcle();
                String time = edt_time.getText().toString();
                vehcle.setEntryTime(time);
                if(car_btn.isChecked()){

                    vehicle ="car";
                }
               else if(bike_btn.isChecked()){

                   vehicle="bike";
                }
                vehcle.setVehicle(vehicle);
                parkDBAdopter.addVehicle(vehcle);


                String react = "Add Completed ";
                Toast.makeText(getApplicationContext(),react ,Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}