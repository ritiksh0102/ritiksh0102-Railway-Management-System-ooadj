package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Availabletrains extends AppCompatActivity {
    Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availabletrains);

        Intent intent= getIntent();
        String tr_date = intent.getStringExtra("j_date");
        String src= intent.getStringExtra("src");
        String dest= intent.getStringExtra("dest");
        String rajd =" RAJDHANI";
        String tr_name1 = src+dest+rajd;


        selected_train(tr_name1,tr_date);

        button7=findViewById(R.id.dobooking);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(Availabletrains.this,PassengerDetails.class);
                intent1.putExtra("src",src);
                intent1.putExtra("dest",dest);
                intent1.putExtra("tr_date",tr_date);
                intent1.putExtra("tr_name",tr_name1);


                startActivity(intent1);

                finish();
            }
        });
    }

    private void selected_train(String tr_name1,String tr_date) {

        DatabaseReference myref1 = FirebaseDatabase.getInstance().getReference("Trains");
        Query checkTrain = myref1.orderByChild("name").equalTo(tr_name1);

        RadioButton tr_name = (RadioButton)findViewById(R.id.rb3);

        checkTrain.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String t_date = snapshot.child(tr_name1).child("date").getValue(String.class);
                    if(t_date.equals(tr_date)){
                        String t_name= snapshot.child(tr_name1).child("name").getValue(String.class);
                        tr_name.setText(t_name);

                    }
                    else {
                        Toast.makeText(Availabletrains.this, "No Trains", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Availabletrains.this, "No Trains", Toast.LENGTH_SHORT).show();

                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}