package com.example.giacomo.farmalista;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UFC6 extends AppCompatActivity{
    EditText nomemedicina, dosaggio, quantita, scatole, giorni;
    TextView dateformat;
    String smedicina, squantita, sscatole, sdosaggio, sgiorni, sdata, day, mounth, year, sdatafine;
    int gg, mm, yyyy;
    long dos, giorniassunzione;
    Button insert;
    ImageButton data;
    static String medicine = "";
    FragmentManager vManager;
    Date datafine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ufc6);
        nomemedicina = (EditText) findViewById(R.id.editTextNameMedicine);
        quantita = (EditText) findViewById(R.id.editTextQtaTot);
        scatole = (EditText) findViewById(R.id.editTextNumberBox);
        dosaggio = (EditText) findViewById(R.id.editTextNumber);
        giorni = (EditText) findViewById(R.id.editTextDayAss);
        insert = (Button) findViewById(R.id.buttonInsert);
        data = (ImageButton) findViewById(R.id.imageButton);
        dateformat = (TextView) findViewById(R.id.textViewDate);

        Spinner spinner = (Spinner) findViewById(R.id.spinner5);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_medicine, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                smedicina = nomemedicina.getText().toString();
                squantita = quantita.getText().toString();
                sscatole = scatole.getText().toString();
                sdosaggio = dosaggio.getText().toString();
                sgiorni = giorni.getText().toString();
                sdata = dateformat.getText().toString(); // prendo il testo della TextView e lo trasformo in stringa

                if (smedicina.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci il nome del medicinale", Toast.LENGTH_SHORT).show();
                }
                else if (squantita.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci la quantità", Toast.LENGTH_SHORT).show();
                }
                else if (sscatole.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci il numero di scatole", Toast.LENGTH_SHORT).show();
                }
                else if (sdosaggio.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci il dosaggio", Toast.LENGTH_SHORT).show();
                }
                else if (sdata.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci la data", Toast.LENGTH_SHORT).show();
                }
                else if (sgiorni.length() == 0) {
                    Toast.makeText(UFC6.this, "Inserisci i giorni di preavviso", Toast.LENGTH_SHORT).show();
                    Toast.makeText(UFC6.this, "Controlla di aver inserito la tipologia di medicine corretta", Toast.LENGTH_SHORT).show();
                }
                else {

                    try {
                        dos = (Integer.parseInt(squantita) / Integer.parseInt(sdosaggio)) * 24 * 60 * 60 * 1000; // prendo la quantità di pastiglie e il dosaggio al giorno e lo trasformo in millisecondi

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataselezionata = formatter.parse(sdata); // trasformo la stringa sdata in Date
                        long mill = dataselezionata.getTime(); // trasformo la Date in millisecondi
                        giorniassunzione = dos + mill; // sommo il dosaggio con la Date in millisecondi
                        datafine = new Date(giorniassunzione); // trasformo i millisecondi in Date
                        sdatafine = datafine.toString(); // trasformo la Date in Stringa
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("nome_medicina", smedicina);
                        obj.put("quantita", squantita);
                        obj.put("scatole", sscatole);
                        obj.put("dosaggio", sdosaggio);
                        obj.put("giorni", sgiorni);
                        obj.put("data", day);
                        obj.put("vdata", sdatafine);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    medicine = obj.toString();
                    ApiCall.medicine += medicine;
                    // stampa di controllo
                    Log.d("json", medicine);
                }
            }
        });


        vManager = getFragmentManager();

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DataPicker();
                newFragment.show(getFragmentManager(),"Date Picker");
            }
        });

    }



}
