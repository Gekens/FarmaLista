package com.example.giacomo.farmalista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;


public class UFC2 extends AppCompatActivity {

    static String credenziali = "";

    TextView privacy, email, password, confermaPassword;
    Button registrati;
    String emailString = null, passwordString = null;

    // metodo che mi permette di criptare una stringa, viene utilizzato per criptare la password utente
    // importante: la stringa abc se criptata più volte da sempre lo stesso risultato
    public static final String md5(final String toEncrypt) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(toEncrypt.getBytes());
            final byte[] bytes = digest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02X", bytes[i]));
            }
            return sb.toString().toLowerCase();
        } catch (Exception exc) {
            return "";
        }
    }
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

                    // trasformo la stringa mail in un array di caratteri
                    char[] tmp = emailString.toCharArray();

                    /*
                    // stampa di prova
                    Log.d("mail","la mail inserita e': ");
                    for (int i = 0; i < tmp.length; i++){
                        Log.d("mail", String.valueOf(tmp[i]));
                    }
                    */

                    // controllo che la mail abbia un formato valido:
                    // dei caratteri, la @, altri caratteri, il punto e altri caratteri


                } else {
                    Toast.makeText(UFC2.this, "Inserisci la mail", Toast.LENGTH_SHORT).show();
                }

                // verifico che la lunghezza della password sia > di 8 caratteri
                if (password.getText().toString().length() >= 8) {

                    // verifico di aver inserito le password e se queste coincidono
                    if (password.getText().toString().length() != 0
                            && password.getText().toString().equals(confermaPassword.getText().toString())) {

                        passwordString = password.getText().toString();

                        passwordString = md5(passwordString);
                    } else {
                        Toast.makeText(UFC2.this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
                    }
                } else { // se la password è minore di 8 caratteri
                    Toast.makeText(UFC2.this, "La password deve avere almeno 8 caratteri", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confermaPassword.setText("");
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
                    //credenziali = obj.toString();
                    //ApiCall.credenziali = credenziali;

                    // istruzioni per scrivere nel db remoto
                    ApiCall api = new ApiCall(new ApiCall.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {

                        }
                    });
                    // il primo parametro è l'oggetto, il secondo è l'indirizzo del backend, il terzo è il tipo di richiesta
                    api.execute(obj.toString(),"http://10.0.2.2:3000/todos","POST");


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
