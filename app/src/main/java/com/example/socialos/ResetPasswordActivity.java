package com.example.socialos;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText emailEdit;
    private Button mButtonReset;
    private String email="";
    private FirebaseAuth mAuth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        mAuth=FirebaseAuth.getInstance();

        emailEdit=(EditText)findViewById(R.id.editTextEmail);
        mButtonReset=(Button)findViewById(R.id.bt_recuper_contraseña);
        mDialog=new ProgressDialog(this);

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= emailEdit.getText().toString();

                if (!email.isEmpty())

                    {
                        mDialog.setMessage("Espere un momento...");
                        mDialog.setCanceledOnTouchOutside(false);
                        mDialog.show();
                        resetPassword();
                }

                else
                {
                    Toast.makeText(ResetPasswordActivity.this,"Debe ingresar el Email",Toast.LENGTH_SHORT).show();
                }


            }

            private void resetPassword() {

                mAuth.setLanguageCode("es");
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(ResetPasswordActivity.this, " Se ha enviado un correo para restablecer tu contraseña", Toast.LENGTH_SHORT).show();

                        }

                        else
                        {
                            Toast.makeText(ResetPasswordActivity.this,"No se pudo enviar el correo de Restrablecer contraseña",Toast.LENGTH_SHORT).show();

                        }
                        mDialog.dismiss();

                    }
                });

            }
        });

            }

    }



