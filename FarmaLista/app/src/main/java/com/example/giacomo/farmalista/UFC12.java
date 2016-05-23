package com.example.giacomo.farmalista;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Utente on 19/05/2016.
 */
public class UFC12 extends DialogFragment {

    String nomeMedicinale;
    String giorniRimanenti;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder vBuilder = new AlertDialog.Builder(getActivity());
        vBuilder.setTitle("il medicinale" + nomeMedicinale + "finirà tra" + giorniRimanenti + "giorni");
        vBuilder.setPositiveButton("Invia", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        vBuilder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return vBuilder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
