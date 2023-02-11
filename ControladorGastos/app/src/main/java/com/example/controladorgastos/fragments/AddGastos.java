package com.example.controladorgastos.fragments;

import android.os.Bundle;
import android.text.InputType;
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

import com.example.controladorgastos.R;
import com.example.controladorgastos.DatabaseHandler;
import com.example.controladorgastos.adaptador.expenseAdapter;
import com.example.controladorgastos.modelo.Gasto;
import com.example.controladorgastos.modelo.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddGastos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class
AddGastos extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddGastos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddGastos.
     */
    // TODO: Rename and change types and number of parameters
    public static AddGastos newInstance(String param1, String param2) {
        AddGastos fragment = new AddGastos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView rv_expense;
    private TextView tv_expense, tv_restante, tv_limitGasto;


    FloatingActionButton mAddFab;
    double totalGasto;
    TextView addIncomeText, addExpenseText;

    private expenseAdapter expenseAdapter;

    private Usuario usuario = new Usuario();
    private List<Gasto> listaGastos = new ArrayList<>();

    private DatabaseHandler databaseHandler;
    //private DatabaseHandlerExpense databaseHandlerExpense;

    private String Saldo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        expenseAdapter = new expenseAdapter(getContext(), listaGastos);
        rv_expense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_expense.setHasFixedSize(true);

        rv_expense.setAdapter(expenseAdapter);
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
                    expenseAdapter = new expenseAdapter(getContext(), listaGastos);
                    rv_expense.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    rv_expense.setHasFixedSize(true);

                    rv_expense.setAdapter(expenseAdapter);
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