package com.example.seriesmanager.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.bind(series.get(position));

        holder.textAno.setText("Ano de Lancamento:");
        holder.textGenero.setText("Gênero:");
        holder.textEmissora.setText("Emissora:");

        holder.textTituloSerie.setText(series.get(position).getNome());
        holder.textAnoSerie.setText(String.valueOf(series.get(position).getAnoLancamento()));
        holder.text_genero_serie.setText(series.get(position).getGenero());
        holder.text_emissora_serie.setText(series.get(position).getEmissora());


        holder.imagePosterSerie.setTag(position);
        holder.imagePosterSerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                Intent intent = new Intent(view.getContext(),TemporadaActivity.class);
                intent.putExtra("serieClicada",series.get(clickPosition).getNome());
                view.getContext().startActivity(intent);

            }
        });
        holder.image_delete.setTag(position);
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                Intent intent = new Intent(view.getContext(),RemocaoActivity.class);
                intent.putExtra("serieClicada",series.get(clickPosition).getNome());
                view.getContext().startActivity(intent);

            }
        });
        holder.btnEdita.setTag(position);
        holder.btnEdita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                Intent intent = new Intent(view.getContext(),FormEditaActivity.class);
                intent.putExtra("serieClicada",series.get(clickPosition).getNome());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (series != null && series.size() > 0) ? series.size():0;
    }
    static class ListaSeriesViewHolder extends RecyclerView.ViewHolder{
        private TextView textTituloSerie,textAnoSerie,textAno, textGenero,text_genero_serie,text_emissora_serie,textEmissora;
        private Serie serie;
        private ImageView image_delete,btnEdita;
        private RelativeLayout imagePosterSerie;

        public ListaSeriesViewHolder(View itemView) {
            super(itemView);

            textTituloSerie = itemView.findViewById(R.id.text_titulo_serie);
            textAnoSerie = itemView.findViewById(R.id.text_ano_serie);
            textAno = itemView.findViewById(R.id.text_ano);
            textGenero = itemView.findViewById(R.id.text_genero);
            text_genero_serie = itemView.findViewById(R.id.text_genero_serie);
            textEmissora = itemView.findViewById(R.id.text_emissora);
            text_emissora_serie = itemView.findViewById(R.id.text_emissora_serie);

            imagePosterSerie = itemView.findViewById(R.id.image_poster_serie);
            image_delete = itemView.findViewById(R.id.image_delete);

            btnEdita = itemView.findViewById(R.id.btnEdita);
            textTituloSerie.setText("Filme de exemplo");
            textAnoSerie.setText("Ano:");


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

