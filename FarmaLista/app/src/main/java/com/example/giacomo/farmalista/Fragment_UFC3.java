package com.example.giacomo.farmalista;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Utente on 24/05/2016.
 */
public class Fragment_UFC3 extends Fragment {

    ListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.activity_ufc3,container,false);

        mListView = (ListView) vView.findViewById(R.id.listView);
        ArrayList<Medicine> vMedicine = new ArrayList<>();

        MedicineAdapter vAdapter= new MedicineAdapter(vView.getContext(),vMedicine);
        mListView.setAdapter(vAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) vView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Inserisci un nuovo medicinale", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // Intent vIntent = new Intent(UFC3.this, UFC6.class);
               // startActivity(vIntent);
            }
        });
        return vView;
    }
}
