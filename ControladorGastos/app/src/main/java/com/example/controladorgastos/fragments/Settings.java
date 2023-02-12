package com.example.controladorgastos.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.controladorgastos.DatabaseHandler;

import com.example.controladorgastos.R;
import com.example.controladorgastos.adaptador.expenseAdapter;
import com.example.controladorgastos.modelo.Usuario;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Settings extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private BottomNavigationView bnv;

    public Settings(BottomNavigationView bnv) {
        this.bnv = bnv;
    }


    // TODO: Rename and change types and number of parameters
    /*
    public static Settings newInstance(String param1, String param2) {
        Settings fragment = new Settings(bnv);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private EditText et_usuario, et_limiteGasto;
    Button btn_save;
    private DatabaseHandler databaseHandler;
    private Usuario user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_configuracion, container, false);

        init(view);

        fillUser();

        return view;
    }

    public void fillUser() {
        user = databaseHandler.getUsuario();
        if(user==null){

        }else{
            et_usuario.setText(user.getUsername());
            et_limiteGasto.setText(String.format("%.2f",user.getLimiteGastos()));

        }

    }

    private void init(View view) {
        et_usuario = view.findViewById(R.id.et_usuario);
        et_limiteGasto = view.findViewById(R.id.et_limiteGasto);
        btn_save = view.findViewById(R.id.btn_saveC);
        databaseHandler = new DatabaseHandler(getContext());
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_usuario.getText().toString();

                if (et_limiteGasto.getText().toString().isEmpty()) {
                    et_limiteGasto.setError("Limite gasto vacio");
                } else if (username.isEmpty()) {
                    et_usuario.setError("Usuario vacio");
                } else {
                    double limiteGasto = Double.valueOf(et_limiteGasto.getText().toString());
                    if(databaseHandler.getUsuario()==null){
                        databaseHandler.addUsuario(username,limiteGasto);
                    }else{
                        databaseHandler.updateUsuario(username,limiteGasto);
                    }
                    fillUser();
                    try {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bnv.getMenu().getItem(1).setEnabled(true);
                    bnv.getMenu().getItem(2).setEnabled(true);
                    bnv.setSelectedItemId(R.id.addGastos);
                }

            }
        });
    }

}