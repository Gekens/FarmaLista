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

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

                // verifico di aver inserito le password e se queste coincidono
                if (password.getText().toString().length() != 0
                        && password.getText().toString().equals(confermaPassword.getText().toString())) {
                    passwordString = password.getText().toString();

                    passwordString = md5(passwordString);
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
                    credenziali = obj.toString();
                    ApiCall.credenziali = credenziali;

                    // istruzioni per scrivere nel db remoto
                    try {
                        // Creo l'oggetto URL che rappresenta l'indirizzo della pagina da richiamare
                        URL paginaURL = new URL("http://192.168.246.1:3000/utente");

                        // creo l'oggetto HttpURLConnection paragonabile all'apertura di una finestra del browser
                        // QUESTO PER DEFAULT è UNA GET
                        HttpURLConnection client = (HttpURLConnection) paginaURL.openConnection();

                        // se devo inviare il dato in POST
                        client.setDoOutput(true);

                        // codifico dati da inviare
                        String datiPost = obj.toString();// URLEncoder.encode("datanascita", "UTF-8") + "=" + URLEncoder.encode(datainserita, "UTF-8");
                        //String datiPost = URLEncoder.encode(obj.toString(),"utf-8");

                        // A questo punto scrivo il dato nello stream di Uscita:
                        OutputStreamWriter wr = new OutputStreamWriter(client.getOutputStream());
                        wr.write(datiPost);
                        wr.flush();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


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
