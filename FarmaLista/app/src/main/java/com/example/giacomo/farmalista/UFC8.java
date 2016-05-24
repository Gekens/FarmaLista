package com.example.giacomo.farmalista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

//test di gestione della Toolbar per possibile Main Activity dove si agganceranno tutti i fragment.

public class UFC8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc8);

        //creazione dell'oggetto Toolbar definito nel res/menu/toolbar_menu.xml
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //metodo per gestire il click dell'utente sull'icona.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_profile:

                Intent vIntent = new Intent(UFC8.this, UFC4.class);
                startActivity(vIntent);

                return true;

            default:
                //se siamo arrivati qui, l'azione dell'utente non è stata riconosciuta.
                //Invochiamo la superclasse per gestirla.
                return super.onOptionsItemSelected(item);

        }
    }

    }
