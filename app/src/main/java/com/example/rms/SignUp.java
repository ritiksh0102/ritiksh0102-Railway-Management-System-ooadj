package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    Button button3;
    RadioGroup radioGroup;
    RadioButton gender;
    Switch sw1;
    User user;

    public String p_name,p_email,p_unique_id,p_gender,p_age,p_type;
    private String p_password;

    //set password
    public String setPassword(String password)
    {
        this.p_password=password;
        return p_password ;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        button3=findViewById(R.id.newsignup);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_to_database(p_name,p_email,p_unique_id,p_password,p_gender,p_age,p_type);
                startActivity(new Intent(SignUp.this,MainActivity.class));
                finish();
            }
        });
}

    public void add_to_database(String p_name, String p_email, String p_unique_id, String p_password, String p_gender, String p_age, String p_type)
    {
        //getting attributes of passenger
        EditText text = (EditText) findViewById(R.id.name);
        p_name = text.getText().toString();

        EditText text1 = (EditText) findViewById(R.id.email);
        p_email = text1.getText().toString();

        EditText text2 = (EditText) findViewById(R.id.age);
        p_age = text2.getText().toString();

        EditText text3 = (EditText) findViewById(R.id.password_new);
        p_password = text3.getText().toString();

        EditText text4 = (EditText) findViewById(R.id.uid);
        p_unique_id = text4.getText().toString();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        gender = (RadioButton) findViewById(selectedId);
        p_gender = gender.getText().toString();

        sw1 = (Switch) findViewById(R.id.switch1);
        if (sw1.isChecked())
            p_type = "Employee";
        else
            p_type = "Passenger";



        //calling passenger object
        User user1 = new User(p_name,p_email,p_unique_id,p_password,p_gender,p_age,p_type);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        user1.p_email = user1.p_email.replace(".",",");
        myRef.child(user1.p_email);
        myRef.child(user1.p_email).child("name").setValue(user1.p_name);
        myRef.child(user1.p_email).child("age").setValue(user1.p_age);
        myRef.child(user1.p_email).child("Unique_id").setValue(user1.p_unique_id);
        myRef.child(user1.p_email).child("gender").setValue(user1.p_gender);
        myRef.child(user1.p_email).child("password").setValue(user1.setPassword(p_password));
        myRef.child(user1.p_email).child("email").setValue(user1.p_email);
        myRef.child(user1.p_email).child("type").setValue(user1.p_type);

        Toast toast = Toast.makeText(getApplicationContext(),"You have successfully signed-up with us",Toast.LENGTH_LONG);
        toast.show();

    }
}