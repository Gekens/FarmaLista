package com.example.giacomo.farmalista;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Utente on 24/05/2016.
 */
public class Fragment_UFC3 extends Fragment {

    ListView mListView;
    Fragment_UFC5 modifica_medicinale;
    FragmentManager vManager;
    String listaMedicine, name, hour, finishDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.activity_ufc3,container,false);
        vManager = getFragmentManager();
        //medicina = ApiCall.medicine;

        // mi collego al database e richiamo l'eventuale lista medicinali memorizzata
        // se il db è vuoto mostro una lista vuota

        // istruzioni per richiedere al db la lista medicinali
        ApiCall api = new ApiCall(new ApiCall.AsyncResponse() {
            @Override
            public void processFinish(String output) {

                // non so come funziona è solo una ipotesi: quando il processo di richiesta finisce
                // dentro la stringa output ho quando ricevuto dal backend, cioè la lista della medicine
                listaMedicine = output; // è un array di stringhe, eventualmente vuoto
            }
        });
        // il primo parametro è l'oggetto, il secondo è l'indirizzo del backend, il terzo è il tipo di richiesta
        api.execute("","http://10.0.2.2:3000/medicine/"+ApiCall.id_utente,"GET");

        /*JSONArray array = null;
        try {
            array = new JSONArray(listaMedicine);
            if (array!=null){
                for(int i=0; i<array.length(); i++){
                    JSONObject jsonObj  = array.getJSONObject(i);
                    name = jsonObj.getString("nome_medicina");
                    hour = jsonObj.getString("dosaggio");
                    finishDate = jsonObj.getString("vdata");
                }
            }
            else
            {

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        mListView = (ListView) vView.findViewById(R.id.listView);
        ArrayList<Medicine> vMedicine = new ArrayList<>();
        /*for (int i=0; i<vMedicine.length();i++){
            vMedicine.add(new Medicine());
        }*/


        MedicineAdapter vAdapter= new MedicineAdapter(vView.getContext(),vMedicine);
        mListView.setAdapter(vAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                modifica_medicinale=new Fragment_UFC5();
                FragmentTransaction vTransaction = vManager.beginTransaction();
                vTransaction.replace(R.id.container,modifica_medicinale,"mm");
                vTransaction.commit();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) vView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Inserisci un nuovo medicinale", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent vIntent = new Intent(getActivity().getBaseContext(), UFC6.class);
                startActivity(vIntent);
            }
        });
        return vView;
    }
}
