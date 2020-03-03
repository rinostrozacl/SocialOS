package com.example.socialos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class RegisterActivity extends AppCompatActivity {

    private TextView Already_have_account_link;
    private FirebaseAuth mAuth;
    private Button bt_registrar;
    private EditText UserEmail, UserPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        bt_registrar = findViewById(R.id.register_button);

        InitializeFields();

        Already_have_account_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToLoginActivity();
            }
        });

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewAccount();

            }
        });
    }

    private void InitializeFields() {
        UserEmail = (EditText) findViewById(R.id.register_email);
        UserPassword = (EditText) findViewById(R.id.register_password);
        Already_have_account_link =(TextView) findViewById(R.id.already_have_account_link);

        loadingBar= new ProgressDialog(this);
    }


            private void createNewAccount() {

                String email = UserEmail.getText().toString();
                String password = UserPassword.getText().toString();
                Integer validacion=6;


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(this,"Por favor Ingresa tu Correo",Toast.LENGTH_SHORT).show();

                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(this,"Por favor Ingresa tu Contraseña",Toast.LENGTH_SHORT).show();

                }

                if (password.length() < validacion){
                    Toast.makeText(this,"Contraseña debe ser superior a 6 digitos",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    loadingBar.setTitle("Creando Nueva Cuenta");
                    loadingBar.setMessage("Por favor espere, Mientras creamos una nueva cuenta para ti..");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        SendUserToLoginActivity();
                                        Toast.makeText(RegisterActivity.this, "Cuenta Creada", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                else
                                    {
                                        String message=task.getException().toString();
                                        Toast.makeText(RegisterActivity.this, "Corrija el Formato de su Correo Electronico", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }



                                }
                            });
                }
            }



    private void SendUserToLoginActivity() {
        Intent loginIntent =new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }



    }

