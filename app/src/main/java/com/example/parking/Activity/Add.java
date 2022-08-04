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

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        edt_time=findViewById(R.id.edt_time);
        car_btn=findViewById(R.id.car_btn);
       bike_btn=findViewById(R.id.bike_btn);
        radioGroup=findViewById(R.id.grp_btn);

        parkDBAdopter = new ParkDBAdopter(getApplicationContext());
         String radio = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Toast.makeText(getBaseContext(),radio,Toast.LENGTH_LONG).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String time = edt_time.getText().toString();
                Vehcle vehcle=new Vehcle();
                vehcle.setEntryTime(time);

                parkDBAdopter.addVehcle(vehcle);
            }
        });

    }
}