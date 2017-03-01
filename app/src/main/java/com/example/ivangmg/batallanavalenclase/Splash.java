package com.example.ivangmg.batallanavalenclase;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.view.View;

public class Splash extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView= getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
        setContentView(R.layout.activity_splash);
        final TextView spl = (TextView)findViewById(R.id.splashin);
        Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
        spl.setTypeface(col);
        final MediaPlayer musicplayer = MediaPlayer.create(this,R.raw.mdrums);
        musicplayer.start();



        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        Splash.this, Battle_menu.class);
                startActivity(mainIntent);

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task,SPLASH_SCREEN_DELAY);
    }




}