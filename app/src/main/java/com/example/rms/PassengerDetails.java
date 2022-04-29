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

public class PassengerDetails extends AppCompatActivity {
    Button bookticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_details);

        Intent intent1= getIntent();
        String src =intent1.getStringExtra("src");
        String dest = intent1.getStringExtra("dest");
        String tr_name= intent1.getStringExtra("tr_name1");
        String tr_date= intent1.getStringExtra("tr_date");

        bookticket=findViewById(R.id.bookticket);
        bookticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText text1 = (EditText) findViewById(R.id.p_name);
                String p_name = text1.getText().toString();

                EditText text2 = (EditText) findViewById(R.id.p_uniqueid);
                String p_uniqueid = text2.getText().toString();

                EditText text3 = (EditText) findViewById(R.id.p_age);
                String p_age = text3.getText().toString();

                EditText text4 = (EditText) findViewById(R.id.p_email);
                String p_email = text4.getText().toString().replace(".",",");



                add_to_database(p_email,p_name,p_uniqueid,p_age,src,dest,tr_name,tr_date);

                Intent intent2 = new Intent(PassengerDetails.this,Donebooking.class);
                intent2.putExtra("p_name",p_name);
                intent2.putExtra("p_uniqueid",p_uniqueid);
                intent2.putExtra("p_age",p_age);
                intent2.putExtra("src",src);
                intent2.putExtra("dest",dest);
                intent2.putExtra("tr_date",tr_date);
                intent2.putExtra("tr_name",tr_name);
                startActivity(intent2);
                finish();




            }
        });
    }

    public void add_to_database(String p_email, String p_name, String p_uniqueid, String p_age, String src, String dest, String tr_name1, String tr_date) {
        Tickets tickets= new Tickets(p_email,p_name,p_uniqueid,p_age,src,dest,tr_name1,tr_date);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Tickets");
        myRef.child(tickets.email);
        myRef.child(tickets.email).child("name").setValue(tickets.name);
        myRef.child(tickets.email).child("email").setValue(tickets.email);
        myRef.child(tickets.email).child("src").setValue(tickets.source);
        myRef.child(tickets.email).child("dest").setValue(tickets.dest);
        myRef.child(tickets.email).child("age").setValue(tickets.age);
        myRef.child(tickets.email).child("date").setValue(tickets.date);
        myRef.child(tickets.email).child("tr_name").setValue(tickets.tr_name);
        myRef.child(tickets.email).child("unique_id").setValue(tickets.unique_id);

        Toast.makeText(getApplicationContext(),"ticket is added to database",Toast.LENGTH_LONG).show();




    }
}