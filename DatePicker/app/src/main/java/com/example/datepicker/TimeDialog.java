package com.example.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class TimeDialog extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {
    OnHoraSeleccionada f;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c= Calendar.getInstance();
        int hora=c.get(Calendar.HOUR);
        int minutos=c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hora,minutos,true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        GregorianCalendar g=new GregorianCalendar(2000,11,11,i,i2);
        f.onResultadoHora(g);
    }


    public interface OnHoraSeleccionada {
        public void onResultadoHora(GregorianCalendar g);
    }
    // Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Activity activity) {
            f=(OnHoraSeleccionada)activity;
            super.onAttach(activity);
    }

}