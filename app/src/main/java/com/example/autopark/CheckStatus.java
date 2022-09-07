package com.example.autopark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CheckStatus extends AppCompatActivity {

    Button calls,cemps,cfills,callv,home3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstat);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        calls=findViewById(R.id.calls);
        cemps = findViewById(R.id.cemps);
        cfills = findViewById(R.id.cfills);
        callv = findViewById(R.id.callv);
        home3 = findViewById(R.id.home3);

        calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CheckAllSlots.class);
                startActivity(i);

            }
        });

        cemps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CheckEmptySlots.class);
                startActivity(i);
            }
        });

        cfills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), CheckFillSlots.class);
                startActivity(i);
            }
        });

        callv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CheckAllVeh.class);
                startActivity(i);
            }
        });

        home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
