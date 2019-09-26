package com.example.socialos;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.os.Bundle;
=======
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaTimestamp;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
>>>>>>> Stashed changes

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
=======
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
            case R.id.bt_call://German
                intent = new Intent(Intent.ACTION_DIAL);//realizar llamada
                startActivity(intent);
                break;
            case R.id.bt_cam://German
                intent = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);//iniciar camara
                startActivity(intent);
                break;
            case R.id.bt_calendar://Carlos
              //  intent = new Intent(Intent);
                //startActivity(intent);
                break;
            case R.id.bt_contact://german
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_img://Daniel
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_archives2://Carlos
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_mail: //Daniel
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_sms: //Carlos
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_watch:
                intent = new Intent(Intent.ACTION_QUICK_CLOCK);//abrir conf reloj german
                startActivity(intent);
                break;
            case R.id.bt_play_store:// Carlos
               // intent = new Intent(Intent.);
               // startActivity(intent);
                break;
            case R.id.bt_config: // Germán
                intent = new Intent();
                startActivity(intent);
                break;
            case R.id.bt_wifi: //Daniel
                intent = new Intent(Intent.ACTION_MANAGE_NETWORK_USAGE);//abrir conexiones
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
        }

>>>>>>> Stashed changes
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



