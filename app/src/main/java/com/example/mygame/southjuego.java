package com.example.mygame;

import android.app.Activity;
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

import androidx.annotation.Nullable;
public class southjuego extends Activity {
    ImageButton img00,img01,img02,img03,img04,img05,img06,img07,img08,img09,img10,img11,img12,img13,img14,img15;
    ImageButton[] tablero = new ImageButton[16];
    Button botonReiniciarJuego, botonSalir;
    TextView textoPuntuacion;
    int puntuacion;
    int aciertos;
    //imagenes
    int[] imagenes;
    int fondo;

    //variables del juego
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.southjuego);
        init();
    }
    private void cargarTablero(){
        img00 = findViewById(R.id.boton0);
        img01 = findViewById(R.id.boton1);
        img02 = findViewById(R.id.boton2);
        img03 = findViewById(R.id.boton3);
        img04 = findViewById(R.id.boton4);
        img05 = findViewById(R.id.boton5);
        img06 = findViewById(R.id.boton6);
        img07 = findViewById(R.id.boton7);
        img08 = findViewById(R.id.boton8);
        img09 = findViewById(R.id.boton9);
        img10 = findViewById(R.id.boton10);
        img11 = findViewById(R.id.boton11);
        img12 = findViewById(R.id.boton12);
        img13 = findViewById(R.id.boton13);
        img14 = findViewById(R.id.boton14);
        img15 = findViewById(R.id.boton15);

        tablero[0] = img00;
        tablero[1] = img01;
        tablero[2] = img02;
        tablero[3] = img03;
        tablero[4] = img04;
        tablero[5] = img05;
        tablero[6] = img06;
        tablero[7] = img07;
        tablero[8] = img08;
        tablero[9] = img09;
        tablero[10] = img10;
        tablero[11] = img11;
        tablero[12] = img12;
        tablero[13] = img13;
        tablero[14] = img14;
        tablero[15] = img15;
    }
    private void cargarBotones(){
        botonReiniciarJuego = findViewById(R.id.botonReiniciarJuego);
        botonSalir = findViewById(R.id.botonJuegoSalir);
        botonReiniciarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void cargarTexto(){
        textoPuntuacion = findViewById(R.id.texto_puntuacion);
        puntuacion = 0;
        aciertos = 0;
        textoPuntuacion.setText("Puntuacion: " + puntuacion);
    }

    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.su,
                R.drawable.su1,
                R.drawable.su2,
                R.drawable.su3,
                R.drawable.su4,
                R.drawable.su5,
                R.drawable.su6,
                R.drawable.su7,
        };
        fondo = R.drawable.fondo;
    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<longitud*2; i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        // System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);
        } else {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo = arrayDesordenado.get(i);
            if(numeroPrimero == numeroSegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                textoPuntuacion.setText("Puntuación: " + puntuacion);
                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;
                        puntuacion--;
                        textoPuntuacion.setText("Puntuación: " + puntuacion);
                    }
                }, 1000);
            }
        }
    }
    private void init(){
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);
        for(int i=0; i<tablero.length; i++) {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }

    }
}
