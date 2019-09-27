package com.example.socialos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaTimestamp;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaTimestamp;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CALCULATOR_PACKAGE_NAME = "com.android.calculator2";
    private static final String CALCULATOR_CLASS_NAME = "com.android.calculator2.Calculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_call).setOnClickListener(this);
        findViewById(R.id.bt_files).setOnClickListener(this);
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
        findViewById(R.id.bt_playstore).setOnClickListener(this);

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
            case R.id.bt_calendar://Carlos
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("content://com.android.calendar/time/"));
                startActivity(intent);
                break;
            case R.id.bt_contact://german
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_img://Daniel
                intent = new Intent (Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivity(intent);//ABRIR GALERIA DE FOTOS.
                break;

            case R.id.bt_mail: //Daniel
                    Intent indents = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "ejemplo@gmail.com"));
                indents.putExtra(Intent.EXTRA_SUBJECT, ":D");
                indents.putExtra(Intent.EXTRA_TEXT, ":D");
                    startActivity(indents);//abrir  Correo electronico.
            case R.id.bt_calculator: //Carlos
                intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName(
                        CALCULATOR_PACKAGE_NAME,
                        CALCULATOR_CLASS_NAME));
                startActivity(intent);
                break;
            case R.id.bt_sms: //Carlos
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"));
                startActivity(intent);
                break;
            case R.id.bt_watch:
                intent = new Intent(Intent.ACTION_QUICK_CLOCK);//abrir conf reloj german
                startActivity(intent);
                break;

            case R.id.bt_config: // Germán
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_wifi: //Daniel
                intent = new Intent(Settings.ACTION_WIFI_SETTINGS);//ABRIR CONFIGURACION DE WIFI
                startActivity(intent);
                break;
            case R.id.bt_google: //German
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.com"));//abrir conexiones
                startActivity(intent);
                break;
            case R.id.bt_facebook: //German
                intent = openFacebook(MainActivity.this);
                startActivity(intent);

                break;
            case R.id.bt_whatsapp://german
                try{
                intent  = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                startActivity(intent);
                }catch (Exception e){
                    String url = "https://api.whatsapp.com/";
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
                break;
            case R.id.bt_files://Carlos
                intent = new Intent(getPackageManager().getLaunchIntentForPackage(" com.android.documentsui"));
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                break;
            case R.id.bt_playstore://Carlos
                try{
                    intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.android.vending"));
                    startActivity(intent);
                break;
            }catch (Exception e){
                String url = "https://play.google.com/";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        }

    }
    public static Intent openFacebook(Context cont){ //context para boton de facebook
        try{
            cont.getPackageManager().getPackageInfo("com.facebook.katana",0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/"));

        }catch (Exception e){
            return  new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/"));//abrir conexiones

        }

    }
 }



