package com.example.socialos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaTimestamp;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_call).setOnClickListener(this);
        findViewById(R.id.bt_archives2).setOnClickListener(this);
        findViewById(R.id.bt_calculator).setOnClickListener(this);
        findViewById(R.id.bt_calendar).setOnClickListener(this);
        findViewById(R.id.bt_cam).setOnClickListener(this);
        findViewById(R.id.bt_config).setOnClickListener(this);
        findViewById(R.id.bt_contact).setOnClickListener(this);
        findViewById(R.id.bt_facebook).setOnClickListener(this);
        findViewById(R.id.bt_img).setOnClickListener(this);
        findViewById(R.id.bt_mail).setOnClickListener(this);
        findViewById(R.id.bt_menu).setOnClickListener(this);
        findViewById(R.id.bt_sms).setOnClickListener(this);
        findViewById(R.id.bt_watch).setOnClickListener(this);
        findViewById(R.id.bt_whatsapp).setOnClickListener(this);
        findViewById(R.id.bt_wifi).setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.bt_call:
                intent = new Intent(Intent.ACTION_DIAL);//realizar llamada
                startActivity(intent);
                break;
            case R.id.bt_cam:
                intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);//iniciar camara
                startActivity(intent);
                break;
            case R.id.bt_calendar:
              //  intent = new Intent(Intent);
                //startActivity(intent);
                break;
            case R.id.bt_contact:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_img:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_archives2:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_mail:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_sms:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_watch:
                intent = new Intent(Intent.ACTION_QUICK_CLOCK);//abrir conf reloj
                startActivity(intent);
                break;
            case R.id.bt_play_store:
               // intent = new Intent(Intent.);
               // startActivity(intent);
                break;
            case R.id.bt_config:
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_wifi:
                intent = new Intent(Intent.ACTION_MANAGE_NETWORK_USAGE);//abrir conexiones
                startActivity(intent);
                break;
            

        }

    }

}
