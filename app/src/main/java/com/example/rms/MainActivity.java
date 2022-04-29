package com.example.rms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button button2,button3;
    String username,password;
    public static String J_DATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for signup
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });


        // for login validation
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();


            }

        });


    }

    private void isUser() {
        //getting user values
        EditText text = (EditText) findViewById(R.id.username);
        String usn = text.getText().toString().replace(".",",");

        EditText text2 = (EditText) findViewById(R.id.password);
        password = text2.getText().toString().trim();

        DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users");

        Query checkUser = myref.orderByChild("email").equalTo(usn);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String passwordfromDB = snapshot.child(usn).child("password").getValue(String.class);
                    String user_type= snapshot.child(usn).child("type").getValue(String.class);
                    if(passwordfromDB.equals(password)){
                        if( user_type.equals("Employee") ){
                            startActivity(new Intent( MainActivity.this,AddTrain.class));
                        }
                        else {
                            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                            intent.putExtra("usn",usn);
                            startActivity(intent);
                            finish();

                        }
                        finish();
                        Toast.makeText(MainActivity.this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Data doesn't exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}