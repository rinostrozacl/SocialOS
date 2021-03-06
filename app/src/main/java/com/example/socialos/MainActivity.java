package com.example.socialos;

import android.content.Intent;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import android.hardware.camera2.CameraManager;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.socialos.chatcomunal.MainActivityChat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_call).setOnClickListener(this);
      //  findViewById(R.id.bt_files).setOnClickListener(this);
        findViewById(R.id.bt_calculator).setOnClickListener(this);
        findViewById(R.id.bt_calendar).setOnClickListener(this);
        findViewById(R.id.bt_cam).setOnClickListener(this);
        findViewById(R.id.bt_config).setOnClickListener(this);
      //  findViewById(R.id.bt_contact).setOnClickListener(this);
        findViewById(R.id.bt_facebook).setOnClickListener(this);
        findViewById(R.id.bt_img).setOnClickListener(this);
      //  findViewById(R.id.bt_mail).setOnClickListener(this);
        findViewById(R.id.bt_sms).setOnClickListener(this);
        findViewById(R.id.bt_watch).setOnClickListener(this);
        findViewById(R.id.bt_whatsapp).setOnClickListener(this);
        findViewById(R.id.bt_wifi).setOnClickListener(this);
        findViewById(R.id.app_list).setOnClickListener(this);
        //findViewById(R.id.bt_chat).setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.bt_call:
                intent = new Intent(Intent.ACTION_DIAL);//realizar llamada*
                startActivity(intent);
                break;
            case R.id.bt_cam:
                startActivity(openCam());
                break;
            case R.id.bt_calendar://Carlos
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://com.android.calendar/time/"));
                startActivity(intent);
                break;
            case R.id.bt_img://Daniel
                intent = new Intent(Intent.ACTION_VIEW, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivity(intent);//ABRIR GALERIA DE FOTOS.
                break;
            case R.id.bt_calculator: //Carlos
                startActivity(openCalculator());
                break;
            case R.id.bt_sms: //Carlos
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"));
                startActivity(intent);
                break;
            case R.id.bt_watch:
                startActivity(openAlarm());
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
                startActivity(openFacebook());
                break;
            case R.id.bt_whatsapp://german
                startActivity(openWhatsapp());
                break;
            case R.id.bt_youtube: //german
                startActivity(openYoutube());
                break;
            case R.id.app_list:
                startActivity(openApps());
                break;
            case R.id.bt_chat://Daniel
                Intent intent2 = new Intent(v.getContext(), MainActivityChat.class);
                startActivityForResult(intent2, 0);
                break;
        }


    }

    private Intent openCamHuawei() {
        Intent intent, intent2;
        try{
             intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.huawei.camera"));

            if(intent != null){
                return intent;
            }else{
                return null;
            }
        }catch (Exception e){
           // Toast.makeText(MainActivity.this, "No se ha podido acceder a la camara del dispositivo", Toast.LENGTH_SHORT).show();
        }return null;
    }
    private Intent openCamNokia() {
        Intent intent, intent2;
        try{
            intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.hmdglobal.camera2lite"));

            if(intent != null){
                return intent;
            }else{
                return null;
            }
        }catch (Exception e){
           // Toast.makeText(MainActivity.this, "No se ha podido acceder a la camara del dispositivo", Toast.LENGTH_SHORT).show();
        }return null;
    }

    private Intent openCam(){

        try{
            if(openCamHuawei() != null && openCamNokia() == null){
                return openCamHuawei();

            }else{
                if(openCamHuawei() == null && openCamNokia() != null){
                    return openCamNokia();

                }else{
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                    return intent;
                }
            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "No se ha podido acceder a la camara del dispositivo", Toast.LENGTH_SHORT).show();
        }return null;
    }



    public Intent openApps() {
        try {
            Intent intent = new Intent(this, AppListActivity.class);
            return(intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Ha Ocurrido un Error", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_DOCK_EVENT);//abrir conexiones
        }
    }

    public Intent openFacebook() { //context para boton de facebook
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.facebook.katana"));
            return (intent);

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"));//abrir conexiones

        }

    }

    public Intent openWhatsapp() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.whatsapp"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp&hl=es"));

        }

    }

    public Intent openYoutube() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.youtube"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
        }
    }

    public Intent openMaps() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=es"));
        }
    }

    public Intent openChrome() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.android.chrome"));//abrir conexiones
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.android.chrome&hl=es"));
        }
    }

    public Intent openGmail() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.gm"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm&hl=es"));

        }
    }

    public Intent openCalculator() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.calculator"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.calculator&hl=es"));

        }
    }

    public Intent openPlaystore() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.android.vending"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/"));

        }
    }

    public Intent openFiles() {
        try {
            Intent intent = new Intent(getPackageManager().getLaunchIntentForPackage("com.google.android.apps.nbu.files"));
            return (intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Redirigiendo", Toast.LENGTH_SHORT).show();
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.nbu.files&hl=es"));

        }
    }

    public Intent openContacts() {
        try {
            Toast.makeText(MainActivity.this, "Abriendo Contactos", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
            return (intent);
        } catch (Exception e) {
            return null;

        }
    }

    public Intent openAlarm() {
        try {
            Toast.makeText(MainActivity.this, "Abriendo Alarmas", Toast.LENGTH_SHORT).show();
            Intent openClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return (openClockIntent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "No se pudo abrir la aplicacion", Toast.LENGTH_SHORT).show();
            return null;
        }
    }


}



