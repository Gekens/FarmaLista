package com.example.giacomo.farmalista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


// estendo la classe BaseAdapter ed implemento i suoi metodi astratti, quando l'IDE protesta premo
// alt+invio e gli dico di fare l'override di tutti i metodi
public class UFC7_Adapter extends BaseAdapter{

    private ArrayList<CellaContatti> mObjects; // la lista degli oggetti che devo inserire nel listView
    private Context mContext;

    // costruttore
    public UFC7_Adapter(ArrayList<CellaContatti> mObjects, Context mContext) {
        this.mObjects = mObjects;
        this.mContext = mContext;
    }

    class ViewHolder {//creo una classe ViewHolder che mappi 1:1 gli oggetti presenti nel layout
        TextView mNome;
        TextView mCognome;
        TextView mTipoContatto;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            //recupero l'oggetto di tipo LayoutInflater grazie al context riferito all'activity
            LayoutInflater vInflater = LayoutInflater.from(mContext);

            //faccio l'inflate del layout che è come fare il setContentView dell'activity
            convertView = vInflater.inflate(R.layout.cell_layout_ufc7, null);

            // creo le variabili riferimento e le collego al cell_layout
            TextView nome = (TextView) convertView.findViewById(R.id.txtNome);
            TextView cognome = (TextView) convertView.findViewById(R.id.txtCognome);
            TextView tipoContatto = (TextView) convertView.findViewById(R.id.txtTipoContatto);

            //creo un oggetto di tipo ViewHolder a cui associo le 2 TextView che ho recuperato
            ViewHolder vHolder = new ViewHolder();
            vHolder.mNome = nome;
            vHolder.mCognome = cognome;
            vHolder.mTipoContatto = tipoContatto;

            //setto come tag della view l'oggetto vHolder in modo che non mi serva ricrearlo ogni volta
            convertView.setTag(vHolder);
        }

        //tramite il metodo getItem recuperiamo la cella nella posizione i
        CellaContatti vNotizia = (CellaContatti) getItem(position);

        //recuperiamo dalla view tramite getTag il nostro oggetto ViewHolder creato in precedenza
        ViewHolder vViewHolder = (ViewHolder) convertView.getTag();

        //setto il testo nelle textview
        vViewHolder.mNome.setText(vNotizia.getNome());
        vViewHolder.mCognome.setText(vNotizia.getCognome());
        vViewHolder.mTipoContatto.setText(vNotizia.getTipoContatto());

        return convertView;
    }
}
