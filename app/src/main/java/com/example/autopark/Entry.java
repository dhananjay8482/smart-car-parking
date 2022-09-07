package com.example.autopark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Entry extends AppCompatActivity {

    EditText VehNo;
    TextView date ,time , eslot;
    Button submit,confirm;
    RadioGroup rg;
    FirebaseDatabase rootNode;
    DatabaseReference reference,ref2;
    String type;
    String slotalloted="X";
    int check=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        VehNo = findViewById(R.id.vehno);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        rg = findViewById(R.id.rg);
        eslot = findViewById(R.id.eslot);
        confirm = findViewById(R.id.confirm);

        Bundle extras = getIntent().getExtras();
        String vehino = extras.getString("VehNo");
        VehNo.setText(vehino);
        String entryDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String entryTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        String timing = entryDate +" "+entryTime;

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.wheeler2)
                {
                    type ="2Wheeler";
                }
                else if(checkedId == R.id.wheeler3)
                {
                    type ="3Wheeler";
                }
                else if(checkedId == R.id.wheeler4)
                {
                    type ="4Wheeler";
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),VehNo.getText().toString()+" Entered Parking", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), FirstActivity.class);
                i.putExtra("Value", "entry");
                startActivity(i);
            }
        });

        date.setText(entryDate);
        time.setText(entryTime);

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           // rootNode = FirebaseDatabase.getInstance();
            /*String sln = "A";
            ref2=rootNode.getReference().child("Slot");
            for(int i=1;i<20;i++)
            {
                SlotHelper s = new SlotHelper(sln+i,"1","XX");
                ref2.child(s.getSlotno()).setValue(s);
            }*/
           // VehicleHelper vh = new VehicleHelper(VehNo.getText().toString(),entryTime,entryDate,"X","X",type,timing,"X",slotalloted);

            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Slot");

               reference1.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       for(DataSnapshot ds : snapshot.getChildren()) {


                           SlotHelper slh = ds.getValue(SlotHelper.class);
                          // Toast.makeText(getApplicationContext(),slh.slotno, Toast.LENGTH_LONG).show();
                           if(slh.getAvail().equals("1") && slh.getVehno().equals("XX") && check==0)
                           {
                               rootNode = FirebaseDatabase.getInstance();
                               VehicleHelper vh = new VehicleHelper(VehNo.getText().toString(),entryTime,entryDate,"X","X",type,timing,"X",slotalloted);
                               //Toast.makeText(getApplicationContext(),slh.slotno, Toast.LENGTH_LONG).show();
                               slotalloted=slh.getSlotno();
                               vh.setSlotno(slh.getSlotno());
                               eslot.setText(slh.getSlotno());
                               reference1.child(slh.getSlotno()).child("vehno").setValue(VehNo.getText().toString());
                               reference1.child(slh.getSlotno()).child("avail").setValue("0");
                               reference = rootNode.getReference().child("Vehicle");
                               reference.child(timing).setValue(vh);
                               check=1;
                               break;
                           }
                       }
                       if(check==0)
                       Toast.makeText(getApplicationContext(),"Parking Full !!", Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {
                   }
               });

              // reference = rootNode.getReference().child("Vehicle");
               // Toast.makeText(getApplicationContext(),slotalloted, Toast.LENGTH_LONG).show();

           // reference.child(timing).setValue(vh);

            }
        });

    }
}