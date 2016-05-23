package com.example.giacomo.farmalista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Utente on 19/05/2016.
 */
public class MedicineAdapter extends BaseAdapter {

    private ArrayList<Medicine> mMedicine;
    private Context mContext;

    public MedicineAdapter(Context mContext, ArrayList<Medicine> mMedicine) {
        this.mContext = mContext;
        this.mMedicine = mMedicine;
    }

    @Override
    public int getCount() {
        return mMedicine.size();
    }

    @Override
    public Object getItem(int position) {
        return mMedicine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mMedicine.get(position).getmId();
    }


    class ViewHolder{

        TextView mName;
        TextView mHour;
        TextView mFinishDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater vInflater = LayoutInflater.from(mContext);
            convertView=vInflater.inflate(R.layout.cell_layout_medicine,null);
            TextView vName= (TextView) convertView.findViewById(R.id.textViewNameMedicine);
            TextView vHour= (TextView) convertView.findViewById(R.id.textViewHourOfAssumption);
            TextView vFinishDate = (TextView) convertView.findViewById(R.id.textViewTermineMedicina);
            ViewHolder vHolder = new ViewHolder();
            vHolder.mName=vName;
            vHolder.mHour=vHour;
            vHolder.mFinishDate=vFinishDate;
            convertView.setTag(vHolder);
        }
        Medicine vMedicine= (Medicine) getItem(position);
        ViewHolder vViewHolder= (ViewHolder) convertView.getTag();
        vViewHolder.mName.setText(vMedicine.getName());
        vViewHolder.mHour.setText(vMedicine.getHour());
        vViewHolder.mFinishDate.setText(vMedicine.getFinishDate());
        return convertView;
    }


}
