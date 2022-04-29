package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrain extends AppCompatActivity {
    String train_name, source, dest,time,seats,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);

        Button addtrain,logout;
        addtrain=findViewById(R.id.addtrain);
        addtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtrain_database(train_name,source,dest,time,seats,date);
                startActivity(new Intent(AddTrain.this,AddTrain.class));
                finish();
            }
        });
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddTrain.this,MainActivity.class));
                finish();
                Toast.makeText(AddTrain.this, "Log Out Successful ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addtrain_database(String train_name, String source, String dest, String time, String seats,String date) {

        //adding trains
        EditText text = (EditText) findViewById(R.id.train_name);
        train_name = text.getText().toString();

        EditText text1 = (EditText) findViewById(R.id.st_source);
        source = text1.getText().toString();

        EditText text2 = (EditText) findViewById(R.id.st_dest);
        dest = text2.getText().toString();

        EditText text3 = (EditText) findViewById(R.id.time);
        time = text3.getText().toString();

        EditText text4 = (EditText) findViewById(R.id.seats);
        seats = text4.getText().toString();

        EditText text5= (EditText) findViewById(R.id.date);
        date = text5.getText().toString();





        Train train1 = new Train(train_name,source,dest,time,seats,date);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Trains");
        myRef.child(train1.train_name);
        myRef.child(train1.train_name).child("name").setValue(train1.train_name);
        myRef.child(train1.train_name).child("source").setValue(train1.source);
        myRef.child(train1.train_name).child("destination").setValue(train1.dest);
        myRef.child(train1.train_name).child("seats").setValue(train1.seats);
        myRef.child(train1.train_name).child("date").setValue(train1.date);
        myRef.child(train1.train_name).child("time").setValue(train1.time);

        Toast.makeText(AddTrain.this, "New Train added", Toast.LENGTH_SHORT).show();
    }
}