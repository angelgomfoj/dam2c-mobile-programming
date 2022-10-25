package com.example.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class DateDialog extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    OnFechaSeleccionada f;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c= Calendar.getInstance();
        int año=c.get(Calendar.YEAR);
        int mes=c.get(Calendar.MONTH);
        int dia=c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),this,año,mes,dia);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        GregorianCalendar g=new GregorianCalendar(i,i2,i3);
        f.onResultadoFecha(g);
    }

    public interface OnFechaSeleccionada {
        public void onResultadoFecha(GregorianCalendar g);
    }
    // Se invoca cuando el fragmento se añade a la actividad
    @Override
    public void onAttach(Activity activity) {
            f=(OnFechaSeleccionada)activity;
            super.onAttach(activity);
    }

}