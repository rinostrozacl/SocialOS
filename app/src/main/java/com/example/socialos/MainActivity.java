package com.example.socialos;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.icu.text.IDNA;
import android.os.Build;
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
import android.provider.AlarmClock;
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
import java.time.Clock;
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v){

        Intent intent;
        switch (v.getId()) {
            case R.id.bt_call:
                intent = new Intent(Intent.ACTION_DIAL);//realizar llamada*
                startActivity(intent);
                break;
            case R.id.bt_cam:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivity(intent);
                break;
            case R.id.bt_calendar://Carlos
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("content://com.android.calendar/time/"));
                startActivity(intent);
                break;
            case R.id.bt_contact://german
               /* try{
                    intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.contacts"));
                    startActivity(intent);
                    break;}catch (Exception e){}*/break;
            case R.id.bt_img://Daniel
                intent = new Intent (Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivity(intent);//ABRIR GALERIA DE FOTOS.
                break;

            case R.id.bt_mail: //Daniel
                startActivity(openGmail());
                break;
            case R.id.bt_calculator: //Carlos
                startActivity(openCalculator());
                break;
            case R.id.bt_sms: //Carlos
                intent = new Intent(Intent.ACTION_VIEW,Uri.parse("sms:"));
                startActivity(intent);
                break;
            case R.id.bt_watch:
                intent = new Intent();//abrir conf reloj german
                startActivity(intent);
                break;
            case R.id.bt_config: // Germán
                intent = new Intent(Settings.ACTION_SETTINGS);// rdy
                startActivity(intent);
                break;
            case R.id.bt_wifi: //Daniel
                intent = new Intent(Settings.ACTION_WIFI_SETTINGS);//ABRIR CONFIGURACION DE WIFI
                startActivity(intent);
                break;
            case R.id.bt_google: //German
                startActivity(openChrome());
                break;
            case R.id.bt_facebook: //German
                intent = openFacebook(MainActivity.this);
                startActivity(intent);
                break;
            case R.id.bt_whatsapp://german
                startActivity(openWhatsapp());
                break;
            case R.id.bt_files://Carlos
               /* intent = new Intent(getPackageManager().getLaunchIntentForPackage(" com.android.documentsui"));
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                break;*/break;
            case R.id.bt_playstore://Carlos
                startActivity(openPlaystore());
                break;
            case R.id.bt_youtube: //german
                startActivity(openYoutube());
                break;
            case R.id.bt_maps: //german
                startActivity(openMaps());
                break;
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

    public  Intent openWhatsapp(){
        try{
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.whatsapp"));
            return (intent);
        }catch (Exception e){
            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://api.whatsapp.com/"));

        }

    }
    public  Intent openYoutube(){
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.youtube"));
            return (intent);
        }
        catch (Exception e){

            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://youtube.com"));
        }
    }
    public  Intent openMaps(){
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps"));
            return (intent);
        }
        catch (Exception e){

            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=es"));
        }
    }
    public Intent openChrome(){
        try{
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.android.chrome"));//abrir conexiones
            return(intent);
        }catch (Exception e){
            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome&hl=es"));
        }
    }
    public Intent openGmail(){
        try{
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.gm"));
            return(intent);
            }
        catch (Exception e){
            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm&hl=es"));

        }
    }
    public Intent openCalculator(){
        try{
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.calculator"));
            return(intent);
           }
        catch (Exception e){
            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.calculator&hl=es"));

        }
    }
    public Intent openPlaystore(){
        try{
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.android.vending"));
            return(intent);
        }catch (Exception e){
            return new Intent (Intent.ACTION_VIEW,Uri.parse("https://play.google.com/"));

        }
    }
 }



