package com.example.seriesmanager.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Serie;

import java.util.List;

public class ListaSeriesAdapter extends RecyclerView.Adapter<ListaSeriesAdapter.ListaSeriesViewHolder> {
    private List<Serie> series;

    public ListaSeriesAdapter(List<Serie> series) {
        this.series = series;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public ListaSeriesViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serie, parent, false);
        return new ListaSeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull ListaSeriesViewHolder holder, int position) {
        //lista de filmes
       // holder.bind(series.get(position));
        holder.textTituloSerie.setText(series.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return (series != null && series.size() > 0) ? series.size():0;
    }
    static class ListaSeriesViewHolder extends RecyclerView.ViewHolder{
        private TextView textTituloSerie;
       // private ImageView imagePosterSerie;

        public ListaSeriesViewHolder(View itemView) {
            super(itemView);
            textTituloSerie = itemView.findViewById(R.id.text_titulo_serie);
         //   imagePosterSerie = itemView.findViewById(R.id.image_poster_serie);
            textTituloSerie.setText("Filme de exemplo");
        }
    }

}
