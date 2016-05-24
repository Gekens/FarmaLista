package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class UFC11 extends AppCompatActivity {

    JSONArray mArrJson;
    JSONObject mObjJson;

    Button accedi;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc11);

        accedi = (Button) findViewById(R.id.buttonAccedi);
        email = (EditText) findViewById(R.id.emailLogin);
        password = (EditText) findViewById(R.id.passwordLogin);

        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // se i campi email e password sono completi verifico l'identità utente
                // altrimenti avviso con un messaggio di toast
                if (email.getText().toString().length() != 0 && password.getText().toString().length() != 0) {

                    // cripto la password inserita dall'utente al momento del login, così posso confrontarla
                    // con quella inserita in fase di registrazione e criptata poi salvata dentro la variabile
                    // statica ApiCall.credenziali
                    String passwordLogin = UFC2.md5(password.getText().toString());

                    // faccio una richiesta al database remoto che mi risponde con una stringa json che al momento
                    // è simulata dalla variabile globale stringa Apical.credenziali, da questa stringa ricavo un oggetto
                    // json dal quale prendo le proprietà che mi servono per verificare l'identità dell'utente
                    Log.d("json",ApiCall.credenziali);

                    String mailDB = null;
                    String pswDB = null;

                    try {
                        mObjJson = new JSONObject(ApiCall.credenziali);

                        // stampe di prova
                        Log.d("json",mObjJson.get("email").toString());
                        Log.d("json",mObjJson.get("password").toString());


                        // recupero mail e password dal json
                        mailDB = mObjJson.get("email").toString();
                        pswDB = mObjJson.get("password").toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                        mObjJson = null;
                    }



                    // verifico che email e password siano quelle dell'utente
                    if (email.getText().toString().equals(mailDB) && passwordLogin.equals(pswDB)) {
                        Intent intent = new Intent(UFC11.this, UFC8.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UFC11.this, "Username o password errati", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UFC11.this, "Username o password errati", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
