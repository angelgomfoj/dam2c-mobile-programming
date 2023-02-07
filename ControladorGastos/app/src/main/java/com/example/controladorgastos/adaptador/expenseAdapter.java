package com.example.controladorgastos.adaptador;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.controladorgastos.modelo.Gasto;
import com.example.controladorgastos.R;

import java.util.ArrayList;
import java.util.List;

public class expenseAdapter extends RecyclerView.Adapter<expenseAdapter.viewholder> {
    private Context context;
    private List<Gasto> listaGastos = new ArrayList<>();

    public expenseAdapter(Context context, List<Gasto> listaGastos) {
        this.context = context;
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_expense_item, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Gasto g = listaGastos.get(position);
        holder.tv_incomeAmount.setText(String.format("%.2f",g.getImporte())+"€");

        String formattedDate = DateFormat.format("dd/MM/yyyy", g.getFecha()).toString();

        holder.tv_incomeDate.setText(formattedDate);
        holder.tv_incomeJob.setText(g.getCategoria());
    }

    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_incomeJob, tv_incomeAmount, tv_incomeDate;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_incomeAmount = itemView.findViewById(R.id.tv_expenseAmount);
            tv_incomeJob = itemView.findViewById(R.id.tv_expenseJob);
            tv_incomeDate = itemView.findViewById(R.id.tv_expenseDate);
        }
    }
}
