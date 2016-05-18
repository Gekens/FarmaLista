package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;



public class UFC2 extends AppCompatActivity {

    static String credenziali = "";

    TextView privacy, email, password, confermaPassword;
    Button registrati;
    String emailString = null, passwordString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc2);

        privacy = (TextView) findViewById(R.id.privacy);
        email = (TextView) findViewById(R.id.emailLogin);
        password = (TextView) findViewById(R.id.passwordLogin);
        confermaPassword = (TextView) findViewById(R.id.confermaPassword);
        registrati = (Button) findViewById(R.id.registrati);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lancio l'activity gestione privacy, non chiuso questa activity dove poi devo ritornare
                Intent intent = new Intent(UFC2.this, UFC13.class);
                startActivity(intent);
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // verifico che la mail sia valida
                if (email.getText().toString().length() != 0) {
                    emailString = email.getText().toString();
                } else {
                    Toast.makeText(UFC2.this, "Inserisci la mail", Toast.LENGTH_SHORT).show();
                }

                // verifico di aver inserito le password e se queste coincidono
                if (password.getText().toString().length() != 0
                        && password.getText().toString().equals(confermaPassword.getText().toString())) {
                    passwordString = password.getText().toString();
                } else {
                    Toast.makeText(UFC2.this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
                }

                // se la mail e le password sono state correttamente inserite ...
                if (emailString != null && passwordString != null) {
                    // genero il json che verrà salvato nel database

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("email", emailString);
                        obj.put("password", passwordString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // al momento salvo il json in una variabile globale di tipo string
                    // qui dovrei mettere le istruzioni per scrivere nel db remoto

                    credenziali = obj.toString();
                    ApiCall.credenziali = credenziali;
                    // stampa di controllo
                    Log.d("json",credenziali);

                    // lancio l'activity UFC11
                    Intent intent = new Intent(UFC2.this, UFC11.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
