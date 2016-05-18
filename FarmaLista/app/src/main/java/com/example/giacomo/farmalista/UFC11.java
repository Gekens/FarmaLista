package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UFC11 extends AppCompatActivity {

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

                    // faccio una richiesta al database remoto che mi risponde con una stringa json che al momento
                    // è simulata dalla variabile globale stringa credenziali, da questa stringa ricavo un oggetto
                    // json dal quale prendo le proprietà che mi servono per verificare l'identità dell'utente
                    JSONArray mArrJson;
                    JSONObject mObjJson;

                    try {
                        mArrJson = new JSONArray(UFC2.credenziali);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mArrJson = null;
                    }

                    String emailDB = null;
                    String passwDB = null;

                    try {
                        mObjJson = mArrJson.getJSONObject(0);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mObjJson = null;
                    }

                    try {
                        emailDB = mObjJson.getString("email");
                        passwDB = mObjJson.getString("password");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    // verifico che email e password siano quelle dell'utente
                    if (email.getText().toString().equals(emailDB) && password.getText().toString().equals(passwDB)) {
                        Intent intent = new Intent(UFC11.this, UFC3.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(UFC11.this, "mail o password errati", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(UFC11.this, "Inserire nome utente e/o password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
