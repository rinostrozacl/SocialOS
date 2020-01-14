package com.example.socialos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialos.chatcomunal.MainActivityChat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEditTextEmail;
    private EditText mEditTetPassword;
    private Button mButtonLogin;

    private String email="";
    private String password="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mEditTextEmail=findViewById(R.id.username);
        mEditTetPassword=findViewById(R.id.password);
        mButtonLogin=findViewById(R.id.bt_login);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                email=mEditTextEmail.getText().toString();
                password=mEditTetPassword.getText().toString();
                if (!email.isEmpty()&& !password.isEmpty()){
                    loginUser();
                }
                else {
                    Toast.makeText(LoginActivity.this, "complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("ShowToast")
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, MainActivityChat.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "No se pudo iniciar Sesion", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
