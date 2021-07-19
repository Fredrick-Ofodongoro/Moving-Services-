package com.example.movingservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText reg_email;
    EditText reg_password;
    Button register;
    Button login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        auth = FirebaseAuth.getInstance();


        register.setOnClickListener(view -> {
            createUser();
            validateEmailAddress(reg_email);
        });
        login.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });


    }
    private boolean validateEmailAddress(EditText reg_email){
        String emailInput = reg_email.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "Email Successfully added", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
            return false;


        }

    }

    private void createUser() {
        String txtEmail = reg_email.getText().toString();
        String txtPassword = reg_password.getText().toString();
        if (TextUtils.isEmpty(txtEmail)) {
            reg_email.setError("Please write your Email");
            reg_email.requestFocus();
        } else if (TextUtils.isEmpty(txtPassword)) {
            reg_password.setError("Kindly input a password");
            reg_password.requestFocus();
        } else {
            auth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}






