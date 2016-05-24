package com.example.giacomo.farmalista;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Utente on 24/05/2016.
 */
public class Fragment_UFC6 extends Fragment {

    EditText nomemedicina, dosaggio, quantita, scatole, giorni;
    TextView dateformat;
    String smedicina, squantita, sscatole, sdosaggio, sgiorni, sdata, day, mounth, year, sdatafine;
    int gg, mm, yyyy;
    long dos, giorniassunzione;
    Button insert;
    ImageButton data;
    static String medicine = "";
    DataPicker vFragment;
    FragmentManager vManager;
    Date datafine;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vView = inflater.inflate(R.layout.activity_ufc6,container,false);

        nomemedicina = (EditText) vView.findViewById(R.id.editTextNameMedicine);
        quantita = (EditText) vView.findViewById(R.id.editTextQtaTot);
        scatole = (EditText) vView.findViewById(R.id.editTextNumberBox);
        dosaggio = (EditText) vView.findViewById(R.id.editTextNumber);
        giorni = (EditText) vView.findViewById(R.id.editTextDayAss);
        insert = (Button) vView.findViewById(R.id.buttonInsert);
        data = (ImageButton) vView.findViewById(R.id.imageButton);
        dateformat = (TextView) vView.findViewById(R.id.textViewDate);



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                smedicina = nomemedicina.getText().toString();
                squantita = quantita.getText().toString();
                sscatole = scatole.getText().toString();
                sdosaggio = dosaggio.getText().toString();
                sgiorni = giorni.getText().toString();

                sdata = dateformat.getText().toString(); // prendo il testo della TextView e lo trasformo in stringa

                dos = (Integer.parseInt(squantita)/Integer.parseInt(sdosaggio))*24*60*60*1000; // prendo la quantità di pastiglie e il dosaggio al giorno e lo trasformo in millisecondi

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                try {
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
                    obj.put("nome_medicina", nomemedicina);
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
                Log.d("json",medicine);
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
        return vView;
    }
    }
