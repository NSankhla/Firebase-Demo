package com.example.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    TextView txtNewAccount;
    Button btnLogin;
    EditText etEmail,etPass;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth =FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }
        setContentView(R.layout.activity_login);

       // FirebaseApp.initializeApp(Login.this);
        txtNewAccount = findViewById(R.id.textView3);
        btnLogin=findViewById(R.id.button);
        etEmail=findViewById(R.id.txtEmail);
        etPass=findViewById(R.id.txtPassword);
        txtNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Registration.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etEmail.getText().toString().equals("")){
                    etEmail.setError("Enter Email");
                }else if(etPass.getText().toString().equals("Enter Password")){
                    etPass.setError("enter Password");
                }else {
                    String email=etEmail.getText().toString().trim();
                    String pass = etPass.getText().toString().trim();

                    auth.signInWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(Login.this, "Login Failed "+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent i = new Intent(Login.this,Home.class);
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
