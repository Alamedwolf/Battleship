package com.example.ivangmg.batallanavalenclase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreClase extends AppCompatActivity {
    private TextView puntaje;
    private TextView mPuntaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final TextView puntaje = (TextView)findViewById(R.id.scoretxt);
        final TextView mPuntaje = (TextView)findViewById(R.id.hstxt);
        Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
        puntaje.setTypeface(col);
        mPuntaje.setTypeface(col);


        //recibir score
        /*int punt = getIntent().getIntExtra("Puntaje",-1);*/
        //set ScoreClase
        /*puntaje.setText(""+punt);*/

        SharedPreferences  prefs = getSharedPreferences("misPreferencias", Context.MODE_WORLD_WRITEABLE);
        int score = prefs.getInt("score",0);
        puntaje.setText(""+score);


    }
}
