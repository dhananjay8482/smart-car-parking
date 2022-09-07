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

public class CheckFillSlots extends AppCompatActivity {

    ListView listView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullslots);

        listView3= findViewById(R.id.listview3);

        final ArrayList<String> list3 = new ArrayList<>();
        final ArrayAdapter adapter3 = new ArrayAdapter<String>(this,R.layout.list_item,list3);
        listView3.setAdapter(adapter3);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Slot");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3.clear();

                for(DataSnapshot snap : snapshot.getChildren())
                {
                    SlotHelper sh = snap.getValue(SlotHelper.class);
                    String slotno = sh.getSlotno();
                    if(!sh.getVehno().equals("XX"))
                    {
                        String txt = slotno + " : " + sh.getVehno() ;
                        list3.add(txt);
                    }

                }
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
