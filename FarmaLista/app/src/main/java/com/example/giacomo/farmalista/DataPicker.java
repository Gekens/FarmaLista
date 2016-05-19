package com.example.giacomo.farmalista;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

/**
 * Created by Giacomo on 19/05/2016.
 */
public class DataPicker extends Fragment{
    String data;
    CalendarView calendario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datapicker, container, false);
        calendario = (CalendarView) view.findViewById(R.id.calendarView);

        data = String.valueOf(calendario.getDate());

        return view;
    }
}
