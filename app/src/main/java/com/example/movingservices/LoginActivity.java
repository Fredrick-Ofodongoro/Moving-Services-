package com.example.movingservices;

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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login;
    Button register;
    EditText log_email;
    EditText log_password;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_email = findViewById(R.id.log_email);
        log_password = findViewById(R.id.log_password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener (view -> {
            loginUser();

        });
        register.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        } );

    }
    private void loginUser(){
        String txtEmail = log_email.getText().toString();
        String txtPassword = log_password.getText().toString();
        if(TextUtils.isEmpty(txtEmail)){
            log_email.setError("Please write your Email");
            log_email.requestFocus();
        }else if(TextUtils.isEmpty(txtPassword)){
            log_password.setError("Kindly input a password");
            log_password.requestFocus();
        }else{
            auth.signInWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull  Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "LogIn Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                    }

                }
            });
        }



    }
}