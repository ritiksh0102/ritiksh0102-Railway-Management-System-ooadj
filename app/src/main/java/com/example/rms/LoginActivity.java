package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Button button5,button8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        String usn = intent.getStringExtra("usn");


        button5=findViewById(R.id.onnewbooking);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(LoginActivity.this,DoBooking.class);
                intent1.putExtra("usn",usn);
                startActivity(intent1);
                finish();

            }
        });

        button8=findViewById(R.id.seebooking);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, Reschedule.class));
                finish();
            }
        });

    }
}