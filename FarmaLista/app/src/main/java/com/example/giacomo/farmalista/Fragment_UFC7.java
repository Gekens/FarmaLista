package com.example.giacomo.farmalista;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Utente on 24/05/2016.
 */
public class Fragment_UFC7 extends Fragment {

    Button aggiungi;

    FragmentManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vView = inflater.inflate(R.layout.activity_ufc7,container,false);
        manager = getFragmentManager();

        aggiungi = (Button) vView.findViewById(R.id.btnAggiungi);

        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment_UFC14 aggiungiContatto = new Fragment_UFC14();
                FragmentTransaction vTransaction = manager.beginTransaction();
                vTransaction.replace(R.id.container,aggiungiContatto,"ac");
                vTransaction.commit();
            }
        });

        return vView;
    }
}
