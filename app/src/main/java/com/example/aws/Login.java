package com.example.aws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView Login;
    FirebaseAuth Fauth;
    EditText user,password;
    Button login;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fauth = FirebaseAuth.getInstance();

         forgot =  findViewById(R.id.forgot);
        user = (EditText) findViewById(R.id.Username);
        password  = (EditText) findViewById(R.id.PASSWORD);
        login = (Button) findViewById(R.id.button);
        Login = (TextView) findViewById(R.id.registernew);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s1 = user.getText().toString().trim();
                String s2 = password.getText().toString().trim();

                if(TextUtils.isEmpty(s1)){
                    Toast.makeText(Login.this, "enter valid email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(s2)){
                    Toast.makeText(Login.this, "enter valid password", Toast.LENGTH_SHORT).show();
                }


                Fauth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Sucess", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }

                        else{
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register2.class);
                startActivity(intent);
            }
        });

    }
}