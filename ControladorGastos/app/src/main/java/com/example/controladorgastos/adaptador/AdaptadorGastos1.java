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

public class AdaptadorGastos1 extends RecyclerView.Adapter<AdaptadorGastos1.viewholder> {
    private Context context;
    private List<Gasto> listaGastos = new ArrayList<>();

    public AdaptadorGastos1(Context context, List<Gasto> listaGastos) {
        this.context = context;
        this.listaGastos = listaGastos;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_gasto_item1, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Gasto g = listaGastos.get(position);
        holder.tv_cantidadGasto.setText(String.format("%.2f",g.getImporte())+"€");

        String formattedDate = DateFormat.format("dd/MM/yyyy", g.getFecha()).toString();

        holder.tv_fechaGasto.setText(formattedDate);
        holder.tv_categoriaGasto.setText(g.getCategoria());
    }

    @Override
    public int getItemCount() {
        return listaGastos.size();
    }

    class viewholder extends RecyclerView.ViewHolder {
        TextView tv_categoriaGasto, tv_cantidadGasto, tv_fechaGasto;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            tv_cantidadGasto = itemView.findViewById(R.id.tv_cantidadGasto);
            tv_categoriaGasto = itemView.findViewById(R.id.tv_categoriaGasto);
            tv_fechaGasto = itemView.findViewById(R.id.tv_fechaGasto);
        }
    }
}
