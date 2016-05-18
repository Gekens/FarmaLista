package com.example.giacomo.farmalista;

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


                } else {
                    Toast.makeText(UFC11.this, "Inserire nome utente e/o password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
