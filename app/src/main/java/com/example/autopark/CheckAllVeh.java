package com.example.autopark;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckAllVeh extends AppCompatActivity {

    ListView listView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allveh);

        listView4= findViewById(R.id.listview4);

        final ArrayList<String> list4 = new ArrayList<>();
        final ArrayAdapter adapter4 = new ArrayAdapter<String>(this,R.layout.list_item,list4);
        listView4.setAdapter(adapter4);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Vehicle");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4.clear();

                for(DataSnapshot snap : snapshot.getChildren())
                {
                    VehicleHelper vh = snap.getValue(VehicleHelper.class);

                    if(vh.getExttiming().equals("X"))
                    {
                        String txt = vh.getVehNo() + " - Slot No : " + vh.getSlotno() +"\n"+"Entry Time: "+vh.getEnttiming() ;
                        list4.add(txt);
                    }

                }
                adapter4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
