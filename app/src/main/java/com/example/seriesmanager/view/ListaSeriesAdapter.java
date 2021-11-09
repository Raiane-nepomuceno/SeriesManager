package com.example.seriesmanager.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Serie;
import java.util.List;

public class ListaSeriesAdapter extends RecyclerView.Adapter<ListaSeriesAdapter.ListaSeriesViewHolder> {
    private List<Serie> series;
    private static ItemSerieClickListener itemSerieClickListener;

    public ListaSeriesAdapter(List<Serie> series) {
        this.series = series;
    }

    public ListaSeriesAdapter(ItemSerieClickListener itemSerieClickListener) {
        this.itemSerieClickListener = itemSerieClickListener;
        this.series = series;
    }


    @NonNull
    @Override
    public ListaSeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_serie, parent, false);
        return new ListaSeriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaSeriesViewHolder holder, int position) {
        //lista de filmes
        // holder.bind(series.get(position));

        holder.textAno.setText("Ano de Lancamento:");
        holder.textGenero.setText("Gênero:");
        holder.textEmissora.setText("Emissora:");

        holder.textTituloSerie.setText(series.get(position).getNome());
        holder.textAnoSerie.setText(String.valueOf(series.get(position).getAnoLancamento()));
        holder.text_genero_serie.setText(series.get(position).getGenero());
        holder.text_emissora_serie.setText(series.get(position).getEmissora());

    }

    @Override
    public int getItemCount() {
        return (series != null && series.size() > 0) ? series.size():0;
    }
    static class ListaSeriesViewHolder extends RecyclerView.ViewHolder{
        private TextView textTituloSerie,textAnoSerie,textAno, textGenero,text_genero_serie,text_emissora_serie,textEmissora;
        private Serie serie;

        public ListaSeriesViewHolder(View itemView) {
            super(itemView);

            textTituloSerie = itemView.findViewById(R.id.text_titulo_serie);
            textAnoSerie = itemView.findViewById(R.id.text_ano_serie);
            textAno = itemView.findViewById(R.id.text_ano);
            textGenero = itemView.findViewById(R.id.text_genero);
            text_genero_serie = itemView.findViewById(R.id.text_genero_serie);
            textEmissora = itemView.findViewById(R.id.text_emissora);
            text_emissora_serie = itemView.findViewById(R.id.text_emissora_serie);

            //imagePosterSerie = itemView.findViewById(R.id.image_poster_serie);

            textTituloSerie.setText("Filme de exemplo");
            textAnoSerie.setText("Ano:");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemSerieClickListener!=null){

                        itemSerieClickListener.onItemClicado(serie);
                    }
                }
            });

        }
        public void bind(Serie serie){
            this.serie = serie;
            textTituloSerie.setText(serie.getNome());

        }


    }
    public interface ItemSerieClickListener{
        void onItemClicado(Serie serie);
    }

}

