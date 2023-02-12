package com.example.controladorgastos.adaptador;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Layout;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controladorgastos.DatabaseHandler;
import com.example.controladorgastos.NotificacionLimiteGasto;
import com.example.controladorgastos.R;
import com.example.controladorgastos.modelo.Gasto;

import java.util.List;

public class expenseAdapter2 extends RecyclerView.Adapter<expenseAdapter2.viewholder> {
    private Context context;
    private List<Gasto> listaGastos;
    private DatabaseHandler databaseHandler;
    private int mode;
    private String filter;
    private TextView gastoTotalFiltrado;

    public expenseAdapter2(Context context, List<Gasto> listaGastos, DatabaseHandler databaseHandler,TextView gastoTotalFiltrado,int num,String filter) {
        this.context = context;
        this.listaGastos = listaGastos;
        this.databaseHandler = databaseHandler;
        this.mode = num;
        this.filter = filter;
        this.gastoTotalFiltrado = gastoTotalFiltrado;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expense_item2, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Gasto g = listaGastos.get(position);
        holder.tv_incomeAmount.setText(g.getImporte()+"€");

        String formattedDate = DateFormat.format("dd/MM/yyyy", g.getFecha()).toString();

        holder.tv_incomeDate.setText(formattedDate);
        holder.tv_incomeType.setText(g.getCategoria());
        holder.tv_incomeNote.setText(g.getDescripcion());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExpenseDialog(context, g);
            }
        });
    }


    public void showExpenseDialog(Context context, Gasto g) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final View customLayout = LayoutInflater.from(context).inflate(R.layout.gasto_add_item, null);
        EditText et_income = customLayout.findViewById(R.id.et_amount);
        Spinner s_category = customLayout.findViewById(R.id.spinner);
        EditText et_description = customLayout.findViewById(R.id.et_descripcion);

        et_income.setText(String.valueOf(g.getImporte()));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context.getApplicationContext(), R.array.array_categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_category.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(g.getCategoria());
        s_category.setSelection(spinnerPosition);
        et_description.setText(g.getDescripcion());

        Button btn_save = customLayout.findViewById(R.id.btn_save);
        Button btn_delete = customLayout.findViewById(R.id.btn_cancel);

        btn_delete.setText("Eliminar");

        builder.setView(customLayout);
        AlertDialog alertDialog = builder.create();

        alertDialog.show();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(v.getContext());
                confirmationDialog.setTitle("Eliminar gasto");
                confirmationDialog.setMessage("¿Estás seguro de que desas eliminar este gasto?");
                confirmationDialog.setPositiveButton("Sí",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                int id = g.getId();
                                databaseHandler.deleteGasto(id);
                                alertDialog.dismiss();
                                if (mode==0){
                                    listaGastos = databaseHandler.getAllGastos();
                                }else{
                                    listaGastos = databaseHandler.getFilteredGastos(filter);
                                }
                                double total = 0;
                                for (Gasto g : listaGastos) {
                                    total += g.getImporte();
                                }
                                gastoTotalFiltrado.setText(String.format("%.2f",total) + "€");
                                notifyDataSetChanged();
                            }
                        });
                confirmationDialog.setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                confirmationDialog.show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = g.getId();
                String category = s_category.getSelectedItem().toString();
                String description = et_description.getText().toString();
                long date = System.currentTimeMillis();

                if (et_income.getText().toString().isEmpty()) {
                    et_income.setError("Importe vacio");
                    return;
                } else if (description.isEmpty()) {
                    et_description.setError("Descripcion vacia");
                    return;
                } else {
                    double amount = Double.valueOf(et_income.getText().toString());
                    databaseHandler.updateGasto(id, category, description, amount, date);
                    alertDialog.dismiss();
                }
                if (mode==0){
                    listaGastos = databaseHandler.getAllGastos();
                }else{
                    listaGastos = databaseHandler.getFilteredGastos(filter);
                }
                double total = 0;
                for (Gasto g : listaGastos) {
                    total += g.getImporte();
                }
                gastoTotalFiltrado.setText(String.format("%.2f",total) + "€");
                notifyDataSetChanged();
                NotificacionLimiteGasto nlgx = new NotificacionLimiteGasto(context);
                        nlgx.NotificationIfExceded();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        private TextView tv_incomeDate, tv_incomeType, tv_incomeNote, tv_incomeAmount;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_incomeDate = itemView.findViewById(R.id.tv_incomeDate);
            tv_incomeType = itemView.findViewById(R.id.tv_incomeType);
            tv_incomeNote = itemView.findViewById(R.id.tv_incomeNote);
            tv_incomeAmount = itemView.findViewById(R.id.tv_incomeAmount);

        }
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        parent.getItemAtPosition(pos);

    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
