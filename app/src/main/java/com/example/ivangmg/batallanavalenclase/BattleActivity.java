package com.example.ivangmg.batallanavalenclase;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BattleActivity extends AppCompatActivity {
    // Texto para mensajes
    private TextView mensaje;
    /** Referencia a lógica del juego Batalla Naval */
    private Batalla batalla;
    //contador de barcos tocados
    int contatk;
    int vidas;
    int score;
    int highscore;
    private TextView viditas;
    Handler mHandler;

    //SharedPreferences prefs;
    //SharedPreferences.Editor editorprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        mHandler = new Handler();

        mensaje = (TextView) findViewById(R.id.textMensaje);
        viditas= (TextView) findViewById(R.id.contadorintentos);
        iniciarEtiquetas();
        batalla = new Batalla();
        Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
        final TextView ttx = (TextView)findViewById(R.id.textMensaje);
        final TextView tt2 = (TextView)findViewById(R.id.intentos);
        final TextView tt3 = (TextView)findViewById(R.id.contadorintentos);

        ttx.setTypeface(col);
        tt2.setTypeface(col);
        tt3.setTypeface(col);
        contatk=0;
        vidas=13;
        score=130;
        highscore=0;

    }
    /* Este método inicia los tags (etiquetas) de las filas y las columnas */
    public void iniciarEtiquetas(){
        // Recorrer las filas
        LinearLayout linearFilas = (LinearLayout) findViewById(R.id.linearFilas);
        for(int i=0; i< linearFilas.getChildCount(); i++)
        {
            // Etiquetar la fila
            LinearLayout fila = (LinearLayout) linearFilas.getChildAt(i);
            fila.setTag(""+i);
            // Recorrer columnas de cada fila
            for(int j=0; j<fila.getChildCount();j++){
                View view = fila.getChildAt(j);
                // Etiquetar columna
                view.setTag(""+j);
            }
        }
    }

    public void resultado(){


        //return score;
        SharedPreferences  prefs2 = getSharedPreferences("misPreferencias", Context.MODE_WORLD_WRITEABLE);
        int score2 = prefs2.getInt("score",0);
        Log.e("recuperado","score recuperado="+score2);

        if (score>score2) {
            //preferencias
            SharedPreferences prefs = getSharedPreferences("misPreferencias", Context.MODE_WORLD_WRITEABLE);
            SharedPreferences.Editor editorprefs = prefs.edit();
            editorprefs.putInt("score", score);
            editorprefs.commit();
            Log.e("prueba", "score=" + score);
        }

    }

    /* Ataca la posición del mapa e indica si ha tocado algún barco o ha dado en el agual */
    public void atacar (View view){
        // Mostrar fila y columna
        View viewFila = (View) view.getParent();
        int fila = Integer.parseInt(viewFila.getTag().toString());
        int columna = Integer.parseInt(view.getTag().toString());

        String msg;

        final  MediaPlayer aguaplayer = MediaPlayer.create(this, R.raw.splash);
        final MediaPlayer bombplayer = MediaPlayer.create(this, R.raw.bomb);

        if(batalla.hayBarco(fila, columna)){
            msg = "Hit";
            bombplayer.start();
            view.setBackgroundResource(R.drawable.bombita);
            view.setEnabled(false);
            contatk=contatk+1;
            vidas=vidas-1;
            Log.e("prueba","contatk = "+contatk);

            //Ganado
          if (contatk==9){
              resultado();
                //cumstom dialog
              final Dialog dialog = new Dialog(this);
              dialog.setContentView(R.layout.dialoga);
              Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
              //set custom dialog
              TextView text = (TextView)dialog.findViewById(R.id.status);
              text.setText("You WIN!");
              text.setTypeface(col);
              TextView texts = (TextView)dialog.findViewById(R.id.scoredlg);
              texts.setText(Integer.toString(score));
              texts.setTypeface(col);
              Button menubtn = (Button)dialog.findViewById(R.id.btnmenu);
              menubtn.setTypeface(col);
              //button menu clicked then go to menu screen
              menubtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent btn = new Intent(BattleActivity.this, Battle_menu.class);
                      startActivity(btn);
                      finish();
                  }
              });
              Button restarbtn = (Button)dialog.findViewById(R.id.btnrestart);
              restarbtn.setTypeface(col);
              //button restart clicked then restar de game
              restarbtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                      startActivity(intent);
                      finish();
                  }
              });
              dialog.setCancelable(false);
              dialog.show();
              //custom dialog size
              Window window = dialog.getWindow();
              window.setLayout(1400,1000);


                      /*  Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                        startActivity(intent);
                        finish();*/


            }




        }else{
            msg = "Water";
            aguaplayer.start();
            view.setBackgroundResource(R.drawable.fallo);
            view.setEnabled(false);
            vidas=vidas-1;
            score=score-10;
        }
        //Perdido
        if (vidas==0 && contatk<9){
            resultado();

            //cumstom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialoga);
            Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
            //set custom dialog
            TextView text = (TextView)dialog.findViewById(R.id.status);
            text.setText("No more attemps!");
            text.setTypeface(col);
            TextView texts = (TextView)dialog.findViewById(R.id.scoredlg);
            texts.setText(Integer.toString(score));
            texts.setTypeface(col);
            Button menubtn = (Button)dialog.findViewById(R.id.btnmenu);
            //menubtn.setTypeface(col);
            //button menu clicked then go to menu screen
            menubtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent btn = new Intent(BattleActivity.this, Battle_menu.class);
                    startActivity(btn);
                    finish();
                }
            });
            Button restarbtn = (Button)dialog.findViewById(R.id.btnrestart);
            //restarbtn.setTypeface(col);
            //button restart clicked then restar de game
            restarbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            dialog.setCancelable(false);
            dialog.show();
            //custom dialog size
            Window window = dialog.getWindow();
            window.setLayout(1400,1000);




        }


        mensaje.setText(msg+" in "+fila+","+columna);
        viditas.setText(" "+vidas);
        if (vidas<=4){
            viditas.setTextColor(Color.parseColor("#FF0000"));}
    }
}
