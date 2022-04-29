package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Donebooking extends AppCompatActivity {
    Button p_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donebooking);

        //viewticket
        Intent intent2= getIntent();

        TextView name = (TextView) findViewById(R.id.b_name);
        name.setText(intent2.getStringExtra("p_name"));

        TextView age = (TextView) findViewById(R.id.b_age);
        age.setText(intent2.getStringExtra("p_age"));

        TextView unique_id = (TextView) findViewById(R.id.u_id);
        unique_id.setText(intent2.getStringExtra("p_uniqueid"));

        TextView src = (TextView) findViewById(R.id.src);
        src.setText(intent2.getStringExtra("src"));

        TextView dest = (TextView) findViewById(R.id.dest);
        dest.setText(intent2.getStringExtra("dest"));

        TextView train_name = (TextView) findViewById(R.id.train_name);
        train_name.setText(intent2.getStringExtra("tr_name"));

        TextView date = (TextView) findViewById(R.id.date);
        date.setText(intent2.getStringExtra("tr_date"));








        p_logout=findViewById(R.id.p_logout);
        p_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Donebooking.this,MainActivity.class));
                finish();
            }
        });
    }
}