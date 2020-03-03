package com.example.socialos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    private ProgressDialog loadingBar;
    private EditText UserEmail, UserPassword;
    private Button bto_login;
    private TextView ForgetPasswordLink;
    private Button  bt_registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        bto_login = findViewById(R.id.bt_login);
        InitializeFields();
        mAuth = FirebaseAuth.getInstance();
        bto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }
        });


        bt_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToRegisterActivity();

            }
        });

    }

    private void InitializeFields() {
        UserEmail = (EditText) findViewById(R.id.username);
        UserPassword = (EditText) findViewById(R.id.password);
        ForgetPasswordLink=(TextView) findViewById(R.id.ForgetPasswordLink);
        bt_registro=(Button)findViewById(R.id.bt_Registrarme);
        loadingBar = new ProgressDialog(this);
    }


    private void AllowUserToLogin() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor ingresa correo", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor ingresa contraseña", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Iniciando Sesión");
            loadingBar.setMessage("Por favor espere mientras se validan los datos...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SendUserToMainActivity();
                                Toast.makeText(LoginActivity.this, "Has ingresado exitosamente...", Toast.LENGTH_SHORT).show();
                            } else {
                                String message = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Error! " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

 private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivityChat.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegisterActivity() {
        Intent registerIntent =new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }
}




