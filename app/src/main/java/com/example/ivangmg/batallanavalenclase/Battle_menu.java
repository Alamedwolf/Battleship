package com.example.ivangmg.batallanavalenclase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Battle_menu extends AppCompatActivity {

    private Button play;
    private Button scorebtn;
    private Button acercade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        play = (Button)findViewById(R.id.jugar);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pl = new Intent().setClass(
                        Battle_menu.this, BattleActivity.class);
                startActivity(pl);
            }
        });

        scorebtn=(Button)findViewById(R.id.Scorebtn);
        scorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sc = new Intent().setClass(
                        Battle_menu.this, ScoreClase.class);
                startActivity(sc);
            }
        });

        acercade=(Button)findViewById(R.id.Acercadebtn);
        acercade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sc = new Intent().setClass(
                        Battle_menu.this, AcercaDe.class);
                startActivity(sc);
            }
        });

    }
}
