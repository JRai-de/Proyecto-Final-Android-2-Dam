package com.example.mygame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends Activity {
    Button login,registrar,salir,invitado;
    private EditText correo,contrasena;
    private FirebaseAuth mAuth;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.buttonlogin);
        registrar = findViewById(R.id.buttonregistro);
        salir = findViewById(R.id.buttonsalirlogin);
        invitado = findViewById(R.id.buttoninvitado);
        correo = findViewById(R.id.correologin);
        contrasena = findViewById(R.id.contrasenalogin);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Iniciando XD");
                iniciarRegistro();
            }

        });
        invitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Iniciando como Invitado");
                iniciarInvitado();
            }

        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    public void iniciarsesion(View view) {

        mAuth.signInWithEmailAndPassword(correo.getText().toString(),contrasena.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent i = new Intent(getApplicationContext(), interfazjuegos.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });


    }
    private void iniciarRegistro(){
        Intent i = new Intent(this,registraa.class);
        startActivity(i);
    }
    private void iniciarInvitado(){
        Intent i = new Intent(this,interfazjuegos.class);
        startActivity(i);
    }
}
