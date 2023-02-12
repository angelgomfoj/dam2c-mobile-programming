package com.example.controladorgastos.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controladorgastos.R;
import com.example.controladorgastos.DatabaseHandler;
import com.example.controladorgastos.adaptador.AdaptadorGastos2;
import com.example.controladorgastos.modelo.Gasto;

import java.util.ArrayList;
import java.util.List;

public class ViewGastos extends Fragment implements AdapterView.OnItemSelectedListener {

    public ViewGastos() {
    }

    private Spinner spinnerF;
    private TextView tvExpense;
    private RecyclerView rvExpense;
    private List<Gasto> listaGastos = new ArrayList<>();
    private DatabaseHandler databaseHandler;

    private AdaptadorGastos2 expenseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_gastos, container, false);

        init(view);

        fillExpense(0);

        return view;
    }

    private void fillExpense(int num) {
        if (num == 0) {
            listaGastos = databaseHandler.getAllGastos();
        } else {
            listaGastos = databaseHandler.getFilteredGastos(spinnerF.getSelectedItem().toString());
        }
        double total = 0;
        for (Gasto g : listaGastos) {
            total += g.getImporte();
        }
        tvExpense.setText(String.format("%.2f",total) + "€");

        expenseAdapter = new AdaptadorGastos2(getContext(), listaGastos, databaseHandler,tvExpense,num,spinnerF.getSelectedItem().toString());
        rvExpense.setLayoutManager(new LinearLayoutManager(getContext()));
        rvExpense.setHasFixedSize(true);

        rvExpense.setAdapter(expenseAdapter);

    }

    private void init(View view) {
        tvExpense = view.findViewById(R.id.tvExpense);
        rvExpense = view.findViewById(R.id.rvExpense);

        spinnerF = view.findViewById(R.id.spinnerFilter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),

                R.array.array_filtros, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerF.setAdapter(adapter);
        spinnerF.setOnItemSelectedListener(this);
        spinnerF.setSelection(0);

        databaseHandler = new DatabaseHandler(getContext());
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);
        if (parent.getSelectedItem().toString().equals("Todos")) {
            fillExpense(0);
        } else {
            fillExpense(1);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }


}