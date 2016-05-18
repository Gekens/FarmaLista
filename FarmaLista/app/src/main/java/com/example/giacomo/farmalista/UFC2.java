package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UFC2 extends AppCompatActivity {

    TextView privacy, email, password, confermaPassword;
    Button registrati;
    String emailString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc2);

        privacy = (TextView) findViewById(R.id.privacy);
        email = (TextView) findViewById(R.id.email);
        password = (TextView) findViewById(R.id.password);
        confermaPassword = (TextView) findViewById(R.id.confermaPassword);
        registrati = (Button) findViewById(R.id.registrati);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UFC2.this, UFC13.class);
                startActivity(intent);
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().length() != 0) {
                    emailString = email.getText().toString();
                } else {
                    Toast.makeText(UFC2.this, "Inserisci la mail", Toast.LENGTH_SHORT).show();
                }

                if (password.getText().toString().length() != 0
                        && password.getText().toString().equals(confermaPassword.getText().toString())) {
                    passwordString = password.getText().toString();
                } else {
                    Toast.makeText(UFC2.this, "Le password non coincidono", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
