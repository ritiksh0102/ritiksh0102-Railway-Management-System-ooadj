package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Reschedule extends AppCompatActivity {
    Button button6,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule_cancel);

        EditText text= findViewById(R.id.b_email);
        String email = text.getText().toString();

        button6=findViewById(R.id.onreschedule);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reschedule(email);
                startActivity(new Intent(Reschedule.this,DoBooking.class));
                finish();
            }
        });
        button= findViewById(R.id.cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(email);
                startActivity(new Intent(Reschedule.this,LoginActivity.class));
                finish();

            }
        });
    }

    private void cancel(String email) {
        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Tickets");

        Query checkUser = myref.orderByChild("email").equalTo(email);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    myref.child(email).removeValue();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void reschedule(String email) {


    }
}