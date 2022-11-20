package com.example.buscaminasv1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorTablero extends RecyclerView.Adapter<AdaptadorTablero.CuadradoViewHolder> {
    private List<Cuadrado> cuadrados;
    private OnCuadradoClickListener listener;

    public AdaptadorTablero(List<Cuadrado> cuadrados, OnCuadradoClickListener listener) {
        this.cuadrados = cuadrados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CuadradoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cuadrado, parent, false);
        return new CuadradoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CuadradoViewHolder holder, int position) {
        holder.bind(cuadrados.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return cuadrados.size();
    }

    public void setCuadrados(List<Cuadrado> cuadrados) {
        this.cuadrados = cuadrados;
        notifyDataSetChanged();
    }

    class CuadradoViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public CuadradoViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.valor_cuadrado);
        }

        public void bind(final Cuadrado cuadrado) {
            itemView.setBackgroundColor(Color.GRAY);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCuadradoClick(cuadrado);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    listener.onCuadradoLongClick(cuadrado);
                    return false;
                }
            });

            if (cuadrado.isRevelado()) {
                if (cuadrado.getValor() == Cuadrado.BOMBA) {
                    valueTextView.setText(R.string.bomba);
                } else if (cuadrado.getValor() == Cuadrado.CUADRADO_VACIO) {
                    valueTextView.setText("");
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    valueTextView.setText(String.valueOf(cuadrado.getValor()));
                    if (cuadrado.getValor() == 1) {
                        valueTextView.setTextColor(Color.BLUE);
                    } else if (cuadrado.getValor() == 2) {
                        valueTextView.setTextColor(Color.GREEN);
                    } else if (cuadrado.getValor() == 3) {
                        valueTextView.setTextColor(Color.RED);
                    }else if (cuadrado.getValor() == 4) {
                        valueTextView.setTextColor(Color.MAGENTA);
                    }else if (cuadrado.getValor() == 5) {
                        valueTextView.setTextColor(Color.BLACK);
                    }else if (cuadrado.getValor() == 6) {
                        valueTextView.setTextColor(Color.LTGRAY);
                    }else if (cuadrado.getValor() == 7) {
                        valueTextView.setTextColor(Color.YELLOW);
                    }else if (cuadrado.getValor() == 8) {
                        valueTextView.setTextColor(Color.CYAN);
                    }
                }
            } else if (cuadrado.isBandera()) {
                valueTextView.setText(R.string.bandera);
            }
        }
    }
}
