package com.example.autopark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Exit extends AppCompatActivity {

    EditText exitVehno;
    TextView entdate,enttime,exitdate,exittime,type,slot;
    Button exitSubmit,confirm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        exitVehno = findViewById(R.id.exitVehno);
        entdate = findViewById(R.id.entdate);
        enttime = findViewById(R.id.enttime);
        exitdate = findViewById(R.id.exitdate);
        exittime = findViewById(R.id.exittime);
        type = findViewById(R.id.type);
        exitSubmit = findViewById(R.id.exitSubmit);
        slot = findViewById(R.id.slot);
        confirm1 = findViewById(R.id.confirm2);

        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),exitVehno.getText().toString()+" Left Parking "+ slot.getText().toString()+" is free.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                i.putExtra("Value", "exit");
                startActivity(i);
            }
        });

        Bundle extras = getIntent().getExtras();
        String vehino = extras.getString("VehNo");
        exitVehno.setText(vehino);

        String exitDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String exitTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String extiming = exitDate + " "+ exitTime;
        exitdate.setText(exitDate);
        exittime.setText(exitTime);

        exitSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vehicle");
                //Query check = reference.orderByChild("VehNo").equalTo(exitVehno.getText().toString());
                //Toast.makeText(getApplicationContext(),exitVehno.getText().toString(), Toast.LENGTH_LONG).show();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        /*if(snapshot.exists())
                        {
                            String exd = snapshot.child(exitVehno.getText().toString()).child("exitTime").getValue(String.class);
                            Toast.makeText(getApplicationContext(),exd, Toast.LENGTH_LONG).show();
                            if(exd.equals("X"))
                            {
                                String ent = snapshot.child(exitVehno.getText().toString()).child("entryTime").getValue(String.class);
                                String end = snapshot.child(exitVehno.getText().toString()).child("entryDate").getValue(String.class);
                                String typ = snapshot.child(exitVehno.getText().toString()).child("type").getValue(String.class);

                                entdate.setText(end);
                                enttime.setText(ent);
                                type.setText(typ);
                                // snapshot.child(exitVehno.getText().toString()).child("exitTime").;
                            }

                        }*/

                        for(DataSnapshot ds : snapshot.getChildren())
                        {
                            VehicleHelper v = ds.getValue(VehicleHelper.class);
                            String no = v.getVehNo();
                            String exd = v.getExitTime();
                            //Toast.makeText(getApplicationContext(),exitVehno.getText().toString(), Toast.LENGTH_LONG).show();

                            if(no.equals(exitVehno.getText().toString()) && exd.equals("X"))
                            {
                                //Toast.makeText(getApplicationContext(),exd, Toast.LENGTH_LONG).show();
                                slot.setText(v.getSlotno());
                                enttime.setText(v.getEntryTime());
                                entdate.setText(v.getEntryDate());
                                type.setText(v.getType());

                                reference.child(v.getEnttiming()).child("exitDate").setValue(exitdate.getText().toString());
                                reference.child(v.getEnttiming()).child("exitTime").setValue(exittime.getText().toString());
                                reference.child(v.getEnttiming()).child("exttiming").setValue(extiming);

                                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Slot");
                                reference1.child(v.getSlotno()).child("avail").setValue("1");
                                reference1.child(v.getSlotno()).child("vehno").setValue("XX");

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                /*check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });


    }
}