package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UFC7 extends AppCompatActivity {

    Button aggiungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc7);

        aggiungi = (Button) findViewById(R.id.btnAggiungi);


        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UFC7.this, UFC14.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
