package com.example.socialos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    // Declaramos las variables
    TextView registerUser;
    EditText username, password;
    Button loginButton;
    String user, pass;
    // URL del servicio firebase
    String URL_FIREBASE = "https://socialos.firebaseio.com/";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Obtenemos las referencias de los objetos
        registerUser = (TextView)findViewById(R.id.register);//registro aun no probado
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);//registro no subido en este branch
        // Boton para redirigir a la actividad Registrar

       // registerUser.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //     public void onClick(View v) {
        //         startActivity(new Intent(Login.this, Register.class));
        //     }
        // });
        // Boton para acceder al MainActivity principal
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los controles
                user = username.getText().toString();
                pass = password.getText().toString();
                // Validar si el valor es nulo
                if(user.equals("")){
                    username.setError("No puede estar en blanco");
                } // Validar si el valor es nulo
                else if(pass.equals("")){
                    password.setError("No puede estar en blanco");
                }
                else{
                    // URL del servicio Firebase
                    String url = URL_FIREBASE+"/users.json";
                    // ProgressDialog que se mostrara en espera del servicio
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setMessage("Cargando, esperate!,Calmateeeeeeeeeeeeeeeee...");
                    pd.show();
                    // Variable request del servicio
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        public void onResponse(String s) {
                            // Validar si el request es null
                            if(s.equals("null")){//Aqui esta el error se estan recibiendo los parametros en blanco
                                Toast.makeText(Login.this, "se está recibiendo en blanco!", Toast.LENGTH_LONG).show();

                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(s);
                                    // Validar si el usuario fue encontrado
                                    if(!obj.has(user)){
                                        Toast.makeText(Login.this, "Usuario no encontrado!", Toast.LENGTH_LONG).show();
                                    } // Validar si la contraseña es valida
                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                        // Asignar los valores a variables globales
                                        UserDetails.username = user;
                                        UserDetails.password = pass;
                                        // Redirigir al activity
                                        startActivity(new Intent(Login.this, Users.class));
                                    }
                                    else {
                                        // La contraseña es incorrecta
                                        Toast.makeText(Login.this, "Contraseña incorrecta!", Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            // Finalizar el progressbar
                            pd.dismiss();
                        }
                    },new Response.ErrorListener(){
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("ERROR", "Error occurred ", volleyError);
                            pd.dismiss();
                        }
                    });
                    // Variables request utilizando la libreria Volley
                    RequestQueue rQueue = Volley.newRequestQueue(Login.this);
                    rQueue.add(request);
                }

            }
        });
    }
}