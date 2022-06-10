package com.example.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class interfazjuegos extends Activity {
    Button playfamguy,playfutur,playsouth,exitinterfaz;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfazjuegos);
        playfamguy = findViewById(R.id.buttonfamguy);
        playfutur = findViewById(R.id.buttonfuturama);
        playsouth = findViewById(R.id.buttonsouthpark);
        exitinterfaz = findViewById(R.id.salirinterfaz);
        playfamguy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Iniciando XD");
                iniciarJuego();
            }

        });
        playfutur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Iniciando XD");
                iniciarJuego2();
            }

        });
        playsouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Iniciando XD");
                iniciarJuego3();
            }

        });
        exitinterfaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void iniciarJuego(){
        Intent i = new Intent(this,primerjuego.class);
        startActivity(i);
    }
    private void iniciarJuego2(){
        Intent i = new Intent(this,futuramajuego.class);
        startActivity(i);
    }
    private void iniciarJuego3(){
        Intent i = new Intent(this,southjuego.class);
        startActivity(i);
    }
}
