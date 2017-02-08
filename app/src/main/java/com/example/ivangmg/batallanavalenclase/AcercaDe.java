package com.example.ivangmg.batallanavalenclase;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);


        ImageView img = (ImageView)findViewById(R.id.unizarjpg);
        img.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.unizar.es/"));
                startActivity(intent);
            }

        });

        ImageView imgin = (ImageView)findViewById(R.id.inajpg);
        imgin.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.inacap.cl/"));
                startActivity(intent);
            }

        });


    }
}
