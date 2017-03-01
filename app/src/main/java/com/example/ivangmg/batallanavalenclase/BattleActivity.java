package com.example.ivangmg.batallanavalenclase;

import android.content.Context;
import android.content.DialogInterface;
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

        final TextView ttx = (TextView)findViewById(R.id.textMensaje);
        final TextView tt2 = (TextView)findViewById(R.id.intentos);
        final TextView tt3 = (TextView)findViewById(R.id.contadorintentos);
        Typeface col = Typeface.createFromAsset(getAssets(),"colleged.ttf");
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
                AlertDialog.Builder build1 = new AlertDialog.Builder(this);
                build1.setMessage("You Win! "+score);
                build1.setCancelable(false);
                build1.setPositiveButton("Restart",
                        new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int id){
                        Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                AlertDialog alert11 = build1.create();
                alert11.show();
                //alert11.dismiss();

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

            //pasar resultado

            //crear mensaje
            AlertDialog.Builder build2 = new AlertDialog.Builder(this);
            build2.setMessage("No more attemps! "+ score);
            build2.setCancelable(false);
            AlertDialog alert12 = build2.create();
            alert12.show();
            //alert12.dismiss();
            mHandler.postDelayed(new Runnable()
            {
                @Override
                public void run() {
                    Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                    startActivity(intent);
                    finish();

                }
            }, 2000);
                /*build2.setPositiveButton("reiniciar",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id){
                                Intent intent = new Intent(BattleActivity.this, BattleActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });*/

        }


        mensaje.setText(msg+" in "+fila+","+columna);
        viditas.setText(" "+vidas);
        if (vidas<=4){
            viditas.setTextColor(Color.parseColor("#FF0000"));}
    }
}
