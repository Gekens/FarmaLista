package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UFC1 extends AppCompatActivity {

    Button accedi, registrati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc1);

        accedi = (Button) findViewById(R.id.accedi);

        registrati = (Button) findViewById(R.id.registrati);


        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // per creare e sucessivamente lanciare la nuova activity
                Intent intent = new Intent(UFC1.this, UFC11.class);

                /*
                // per passare dati da una activity ad un'altra
                Bundle bundle = new Bundle();
                bundle.putString("stringa", stringa.getText().toString());
                bundle.putString("numero", numero.getText().toString());
                intent.putExtras(bundle);
                */

                // faccio partire la nuova activity
                startActivity(intent);
                // distruggo quella attuale, non è necessario farlo sempre
                finish();
            }
        });

        registrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UFC1.this, UFC2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
