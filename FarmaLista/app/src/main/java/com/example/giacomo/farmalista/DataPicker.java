package com.example.giacomo.farmalista;

import android.app.Activity;
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
    IFragment mListener;
    IFragment mData;

    public interface IFragment {
        public void closeFragment();
        public void getDate(String data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datapicker, container, false);
        calendario = (CalendarView) view.findViewById(R.id.calendarView);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                data = Integer.toString(year).concat(Integer.toString(month).concat(Integer.toString(dayOfMonth)));
                mListener.closeFragment();
                mData.getDate(data);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof IFragment) {
            mListener = (IFragment) activity;
        } else {
            mListener = null;
        }
    }
}
