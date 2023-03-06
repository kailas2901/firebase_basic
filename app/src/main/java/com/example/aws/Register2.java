package com.example.aws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register2 extends AppCompatActivity {

    FirebaseAuth Fauth;
    EditText ed1,ed2,ed3;
    Button b1;
    String s1,s2,s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        ed1 = (EditText) findViewById(R.id.username);
        ed2 = (EditText) findViewById(R.id.PasswordL);
        ed3 = (EditText) findViewById(R.id.Email);
        b1 = (Button) findViewById(R.id.b2);
        Fauth = FirebaseAuth.getInstance();





        if(TextUtils.isEmpty(s2)){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(s3)){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s1 = ed1.getText().toString();
                s3 = ed2.getText().toString().trim();
                s2 = ed3.getText().toString().trim();

                Fauth.createUserWithEmailAndPassword(s2,s3).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser User = Fauth.getCurrentUser();
                            User.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register2.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register2.this, "Registration failed", Toast.LENGTH_SHORT).show();

                                }
                            });


                            Intent intent = new Intent(Register2.this,Login.class);
                            startActivity(intent);
                        }
                        else{

                        }
                    }
                });

            }
        });






    }
}