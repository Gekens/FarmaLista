package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UFC1 extends AppCompatActivity {

    Button accedi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc1);

        accedi = (Button) findViewById(R.id.accedi);


        accedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // per creare e sucessivamente lanciare la nuova activity
                Intent intent = new Intent(UFC1.this, Ufc11.class);

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
    }
}
