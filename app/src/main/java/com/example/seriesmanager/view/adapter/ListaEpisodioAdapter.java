package com.example.seriesmanager.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Episodio;
import com.example.seriesmanager.model.Temporada;

import java.util.List;

public class ListaEpisodioAdapter extends RecyclerView.Adapter<ListaEpisodioAdapter.ListaEpisodioViewHolder>{
    private List<Episodio> episodios;

    public ListaEpisodioAdapter(List<Episodio> episodios) {
        this.episodios = episodios;
    }
    @NonNull
    @Override
    public ListaEpisodioAdapter.ListaEpisodioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_episodio, parent, false);
        return new ListaEpisodioAdapter.ListaEpisodioViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListaEpisodioAdapter.ListaEpisodioViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return (episodios != null && episodios.size() > 0) ? episodios.size():0;

    }
    static class ListaEpisodioViewHolder extends RecyclerView.ViewHolder {
        private TextView texQuantEpisodio, textAnoTemporada, textNumSeq, numSeq, quant_episodio, text_anoT;
        private Temporada temporada;
        private ImageView imagePosterSerie, image_delete, image_edita;


        public ListaEpisodioViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Temporada temporada) {
            this.temporada = temporada;
            textNumSeq.setText(String.valueOf(temporada.getNumeroSequencial()));

        }
    }
}