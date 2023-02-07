package com.example.controladorgastos.adaptador;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controladorgastos.DatabaseHandler;
import com.example.controladorgastos.R;
import com.example.controladorgastos.modelo.Gasto;

import java.util.Calendar;
import java.util.List;

public class expenseAdapter2 extends RecyclerView.Adapter<expenseAdapter2.viewholder> {
    private Context context;
    private List<Gasto> listaGastos;
    private DatabaseHandler databaseHandler;

    public expenseAdapter2(Context context, List<Gasto> listaGastos, DatabaseHandler databaseHandler) {
        this.context = context;
        this.listaGastos = listaGastos;
        this.databaseHandler = databaseHandler;
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
        //et_type.setText(g.getType());
        s_category.setPrompt("testing");
        et_description.setText(g.getDescripcion());

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
                String id = String.valueOf(g.getId());
                String amount = et_income.getText().toString();
                String type = s_category.getSelectedItem().toString();
                String note = et_description.getText().toString();
                long date = System.currentTimeMillis();

                if (amount.isEmpty()) {
                    et_income.setError("Empty amount");
                    return;
                } else if (type.isEmpty()) {
                    //et_type.setError("Empty Type");
                    return;
                } else if (note.isEmpty()) {
                    et_description.setError("Empty note");
                    return;
                } else {
                   // databaseHandler.update(id, amount, type, note, String.valueOf(date));
                    alertDialog.dismiss();
                }

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
