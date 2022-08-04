package com.example.parking.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.parking.R;
import com.example.parking.database.ParkDBAdopter;
import com.example.parking.database.ParkDatabase;

public class MainActivity extends AppCompatActivity {

    ParkDatabase parkDatabase;
    AppCompatButton Button_1;
    AppCompatButton Button_2;
    AppCompatButton Button_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        parkDatabase = new ParkDatabase(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_1 = findViewById(R.id.Button_1);
        Button_2 = findViewById(R.id.Button_2);
        Button_3 = findViewById(R.id.Button_3);

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
                finish();
            }
        });

    }
}