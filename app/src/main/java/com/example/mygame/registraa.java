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
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registraa extends AppCompatActivity {

    private EditText correo,contrasena,contrasenaconfirma;
    private Button salir;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correo);
        contrasena = findViewById(R.id.contrasena);
        contrasenaconfirma = findViewById(R.id.contrasenaconfirma);
        salir = findViewById(R.id.buttonsalirregistro);
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
        //updateUI(currentUser);
    }

    public void registrarusuario(View view) {
        if (contrasena.getText().toString().equals(contrasenaconfirma.getText().toString())){
            mAuth.createUserWithEmailAndPassword(correo.getText().toString(),contrasenaconfirma.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                Toast.makeText(getApplicationContext(), "Registro Aceptado.",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                        startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        } else {
                Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }


    }
}