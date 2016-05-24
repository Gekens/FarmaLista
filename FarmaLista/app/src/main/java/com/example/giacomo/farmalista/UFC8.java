package com.example.giacomo.farmalista;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//test di gestione della Toolbar per possibile Main Activity dove si agganceranno tutti i fragment.

public class UFC8 extends ActionBarActivity {

    //istanzio il fragment e il manager dello stesso, l'obiettivo è che nell'UFC 8 all'inizio ci sia la Lista dei Medicinali.
    Fragment_UFC3 lista_medicinali;
    Fragment_UFC4 profilo_utente;
    FragmentManager vManager;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc8);

        //creazione dell'oggetto Toolbar definito nel res/menu/toolbar_menu.xml
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_container);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,myToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        vManager = getFragmentManager();

     if (savedInstanceState==null){
            lista_medicinali=new Fragment_UFC3();
            FragmentTransaction vTransaction = vManager.beginTransaction();
           vTransaction.add(R.id.container,lista_medicinali,"lm");
            vTransaction.commit();
        }
        else {
            lista_medicinali = (Fragment_UFC3) vManager.findFragmentByTag("lm");
        }
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

                profilo_utente=new Fragment_UFC4();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.container,profilo_utente,"pu");
                vTransaction.commit();

                return true;

            default:
                //se siamo arrivati qui, l'azione dell'utente non è stata riconosciuta.
                //Invochiamo la superclasse per gestirla.
                return super.onOptionsItemSelected(item);

        }
    }

    }
