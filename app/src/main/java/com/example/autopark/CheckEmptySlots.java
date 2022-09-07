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

public class CheckEmptySlots extends AppCompatActivity {

    ListView listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emptyslots);

        listView2 = findViewById(R.id.listview2);

        final ArrayList<String> list2 = new ArrayList<>();
        final ArrayAdapter adapter2 = new ArrayAdapter<String>(this,R.layout.list_item,list2);
        listView2.setAdapter(adapter2);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Slot");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2.clear();

                for(DataSnapshot snap : snapshot.getChildren())
                {
                    SlotHelper sh = snap.getValue(SlotHelper.class);
                    String slotno = sh.getSlotno();
                    if(sh.getVehno().equals("XX"))
                    {
                        String txt = slotno ;
                        list2.add(txt);
                    }

                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
