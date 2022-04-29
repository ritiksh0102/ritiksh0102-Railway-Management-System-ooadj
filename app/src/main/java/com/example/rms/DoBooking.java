package com.example.rms;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DoBooking extends AppCompatActivity {
    ImageView iv;
    TextView tv;
    Integer year,month,day;
    Button button9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_booking);





        iv =findViewById(R.id.iv);
        tv=findViewById(R.id.tv);
        EditText text1 = (EditText) findViewById(R.id.source);
        EditText text2 = (EditText) findViewById(R.id.destination);

        Calendar cal = Calendar.getInstance();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year=cal.get(Calendar.YEAR);
                month=cal.get(Calendar.MONTH);
                day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpd= new DatePickerDialog(DoBooking.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        tv.setText(i2 + "-0" + i1 +"-" + i);
                    }
                },day,month,year);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });




        button9=findViewById(R.id.findtrains);

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jj  = new Intent(DoBooking.this,Availabletrains.class);
                String j_date = tv.getText().toString();


                String src = text1.getText().toString();


                String dest = text2.getText().toString();

                jj.putExtra("j_date",j_date);
                jj.putExtra("src",src);
                jj.putExtra("dest",dest);
                startActivity(jj);
                finish();
            }
        });



    }

}