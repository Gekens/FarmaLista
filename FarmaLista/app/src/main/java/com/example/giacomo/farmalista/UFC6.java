package com.example.giacomo.farmalista;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class UFC6 extends AppCompatActivity implements DataPicker.IFragment{
    TextView nomemedicina;
    NumberPicker quantita, scatole, dosaggio, giorni;
    String squantita, sscatole, sdosaggio, sgiorni;
    Button insert;
    ImageButton data;
    static String medicine = "";
    DataPicker vFragment;
    FragmentManager vManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc6);
        nomemedicina = (TextView) findViewById(R.id.editTextNameMedicine);
        quantita = (NumberPicker) findViewById(R.id.numberPicker);
        scatole = (NumberPicker) findViewById(R.id.numberPicker2);
        dosaggio = (NumberPicker) findViewById(R.id.numberPicker3);
        giorni = (NumberPicker) findViewById(R.id.numberPicker4);
        insert = (Button) findViewById(R.id.buttonInsert);
        data = (ImageButton) findViewById(R.id.imageButton);


        squantita = quantita.getDisplayedValues().toString();
        sscatole = scatole.getDisplayedValues().toString();
        sdosaggio = scatole.getDisplayedValues().toString();
        sgiorni = giorni.getDisplayedValues().toString();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject obj = new JSONObject();
                try {
                    obj.put("nome_medicina", nomemedicina);
                    obj.put("quantita", squantita);
                    obj.put("scatole", sscatole);
                    obj.put("dosaggio", sdosaggio);
                    obj.put("giorni", sgiorni);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                medicine = obj.toString();
                ApiCall.medicine += medicine;
                // stampa di controllo
                Log.d("json",medicine);
            }
        });


        vManager = getFragmentManager();

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vFragment = new DataPicker();
                FragmentTransaction vTansaction = vManager.beginTransaction();
                vTansaction.add(R.id.container, vFragment, "tag");
                vTansaction.commit();
            }
        });

    }

    @Override
    public void closeFragment() {
        FragmentTransaction vTansaction = vManager.beginTransaction();
        vTansaction.remove(vFragment);
        vTansaction.commit();
    }
}
