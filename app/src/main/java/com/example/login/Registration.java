package com.example.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

public class Registration extends AppCompatActivity {
EditText etEmail,etPass,etConfPass;
Button btnSignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etEmail=findViewById(R.id.textEmail);
        etPass=findViewById(R.id.textPassword);
        etConfPass=findViewById(R.id.textConform);
        btnSignup=findViewById(R.id.btnSignUp);
        FirebaseApp.initializeApp(this);
        FirebaseInstanceId.getInstance().getToken();
         mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equals("")){
                    etEmail.setError("Enter your email");
                    }else if(etPass.getText().toString().equals("")){
                    etPass.setError("enter password");
                }else if(etConfPass.getText().toString().equals("")){
                    etConfPass.setError("enter conform password");
                }
                else if(!etPass.getText().toString().equals(etConfPass.getText().toString())){
                    etPass.setError("Password not match");
                    etConfPass.setError("password not match");
                }else {
                    String email =etEmail.getText().toString().trim();
                    String pass = etPass.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(),"SignUp Failed  " + task.getException().toString(),Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"SignUp Succes",Toast.LENGTH_LONG).show();
                                        Intent i =new Intent(Registration.this, Login.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }


}
