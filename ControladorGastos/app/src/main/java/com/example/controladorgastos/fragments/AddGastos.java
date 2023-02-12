package com.example.controladorgastos.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controladorgastos.NotificacionLimiteGasto;
import com.example.controladorgastos.R;
import com.example.controladorgastos.DatabaseHandler;
import com.example.controladorgastos.adaptador.AdaptadorGastos1;
import com.example.controladorgastos.modelo.Gasto;
import com.example.controladorgastos.modelo.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class
AddGastos extends Fragment implements AdapterView.OnItemSelectedListener {

    public AddGastos() {
    }


    private RecyclerView rv_expense;
    private TextView tv_expense, tv_restante, tv_limitGasto;


    FloatingActionButton mAddFab;
    double totalGasto;

    private AdaptadorGastos1 AdaptadorGastos1;

    private Usuario usuario = new Usuario();
    private List<Gasto> listaGastos = new ArrayList<>();

    private DatabaseHandler databaseHandler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_addgastos, container, false);

        init(view);

        databaseHandler = new DatabaseHandler(getContext());

        fillModeloUsuario();
        fillModeloGasto();

        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGastoDialog();
            }
        });
        return view;
    }

    private void fillModeloGasto() {

        listaGastos = databaseHandler.getAllGastos();

        double total = 0;
        for (Gasto g : listaGastos) {
            total += g.getImporte();
        }
        totalGasto = total;
        tv_expense.setText(String.format("%.2f",total) + "€");
        tv_restante.setText(String.format("%.2f",usuario.getLimiteGastos()-total) + "€");

        AdaptadorGastos1 = new AdaptadorGastos1(getContext(), listaGastos);
        rv_expense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_expense.setHasFixedSize(true);

        rv_expense.setAdapter(AdaptadorGastos1);
    }

    private void fillModeloUsuario() {
        usuario = databaseHandler.getUsuario();
        tv_limitGasto.setText(String.format("%.2f",usuario.getLimiteGastos()) + "€");
        tv_restante.setText(String.format("%.2f",totalGasto)+"€");
    }


    private void showGastoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);


        final View customLayout = getLayoutInflater().inflate(R.layout.gasto_add_item, null);
        EditText et_income = customLayout.findViewById(R.id.et_amount);
        Spinner spinner = customLayout.findViewById(R.id.spinner);
        EditText et_descripcion = customLayout.findViewById(R.id.et_descripcion);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),

                R.array.array_categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_cancel = customLayout.findViewById(R.id.btn_cancel);

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoria = spinner.getSelectedItem().toString();
                String descripcion = et_descripcion.getText().toString();
                long date = System.currentTimeMillis();


                if (et_income.getText().toString().isEmpty()) {
                    et_income.setError("Importe vacio");
                } else if (descripcion.isEmpty()) {
                    et_descripcion.setError("Descripcion vacia");
                } else {
                    double importe = Double.valueOf(et_income.getText().toString());
                    databaseHandler.addGasto(categoria, descripcion, importe, date);
                    alertDialog.dismiss();
                    fillModeloGasto();
                    AdaptadorGastos1 = new AdaptadorGastos1(getContext(), listaGastos);
                    rv_expense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    rv_expense.setHasFixedSize(true);

                    rv_expense.setAdapter(AdaptadorGastos1);
                     if(databaseHandler.checkLimitExcedido()) {
                         NotificacionLimiteGasto nlg = new NotificacionLimiteGasto(getContext());
                         nlg.NotificationIfExceded();
                     }

                }

            }
        });

    }

    private void init(View root) {
        rv_expense = root.findViewById(R.id.rv_expense);

        tv_expense = root.findViewById(R.id.tv_expense);

        tv_limitGasto = root.findViewById(R.id.tv_limitGasto);

        tv_restante = root.findViewById(R.id.tv_restante);

        mAddFab = root.findViewById(R.id.add_fab);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

}