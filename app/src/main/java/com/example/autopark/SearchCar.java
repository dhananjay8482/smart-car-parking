package com.example.autopark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchCar extends AppCompatActivity {

    Button search;
    ListView listView5;
    EditText no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_car);

        listView5= findViewById(R.id.listview5);
        no = findViewById(R.id.no);
        search=findViewById(R.id.ser);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String> list5 = new ArrayList<>();
                final ArrayAdapter adapter5 = new ArrayAdapter<String>(SearchCar.this,R.layout.list_item,list5);
                listView5.setAdapter(adapter5);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Vehicle");

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list5.clear();

                        for(DataSnapshot snap : snapshot.getChildren())
                        {
                            VehicleHelper vh = snap.getValue(VehicleHelper.class);

                            if(vh.getVehNo().equals(no.getText().toString()))
                            {
                                if(vh.getExttiming().equals("X"))
                                {
                                    String txt ="Entry : "+vh.getEntryDate()+" "+vh.getEntryTime()+"\n"+"Parked at: "+vh.getSlotno()+" ";
                                    list5.add(txt);
                                }
                                else {
                                    String txt = "Entry : " + vh.getEntryDate() + " " + vh.getEntryTime() + "\n" + "Exit : " + vh.getExitDate() + " " + vh.getExitTime() + "\n" + "Parked at: " + vh.getSlotno() + " ";
                                    list5.add(txt);
                                }
                            }

                        }
                        adapter5.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}