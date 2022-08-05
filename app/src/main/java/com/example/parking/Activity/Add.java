package com.example.parking.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
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

import java.util.List;

public class Add extends AppCompatActivity {

   AppCompatButton btn_add;
    ParkDBAdopter parkDBAdopter;
    AppCompatEditText edt_time;
    AppCompatRadioButton car_btn , bike_btn;
    RadioGroup radioGroup;
    String vehicle;
    AppCompatImageView back;

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
        back = findViewById(R.id.back2_home);

        parkDBAdopter = new ParkDBAdopter(getApplicationContext());

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

        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Vehcle vehcle=new Vehcle();
                String time = edt_time.getText().toString();
                vehcle.setEntryTime(time);

                if(car_btn.isChecked()==false&&bike_btn.isChecked()==false) {
                    Toast.makeText(getApplicationContext(),"didnt choose!!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(edt_time.getText().length()==0) {
                    Toast.makeText(getApplicationContext(),"enter Time!",Toast.LENGTH_LONG).show();
                    return;
                }

                if(car_btn.isChecked()){

                    vehicle ="car";
                }
                else if(bike_btn.isChecked()){

                    vehicle="bike";
                }
                vehcle.setVehicle(vehicle);
                parkDBAdopter.addVehicle(vehcle);

                List<Integer> lastID = parkDBAdopter.showParkID();

                StringBuffer buffer = new StringBuffer();
                buffer.append("ID: " + lastID.get(lastID.size()-1) + "\n");
                buffer.append("Vehicle: " + vehicle + "\n");
                buffer.append("EntryTime: " + edt_time.getText().toString() + "\n");
                showMessage("Date", buffer.toString());

            }
        });
    }
}