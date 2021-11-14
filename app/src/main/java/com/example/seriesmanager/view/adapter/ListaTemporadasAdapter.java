package com.example.seriesmanager.view.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Temporada;
import com.example.seriesmanager.view.temporada.FormEditaTemporadaActivity;
import com.example.seriesmanager.view.serie.RemocaoTemporadaActivity;

import java.util.List;

public class ListaTemporadasAdapter extends RecyclerView.Adapter<ListaTemporadasAdapter.ListaTemporadasViewHolder> {
    private List<Temporada> temporadas;

    public ListaTemporadasAdapter(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public ListaTemporadasAdapter() {
    }

    @NonNull
    @Override
    public ListaTemporadasAdapter.ListaTemporadasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_temporada, parent, false);
        return new ListaTemporadasAdapter.ListaTemporadasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaTemporadasAdapter.ListaTemporadasViewHolder holder, int position) {
        holder.bind(temporadas.get(position));

        holder.textNumSeq.setText("Temporada:");
        holder.textAnoTemporada.setText("Ano da temporada:");
        holder.texQuantEpisodio.setText("Quantidade de episódios:");

        holder.numSeq.setText(String.valueOf(temporadas.get(position).getNumeroSequencial()));
        holder.quant_episodio.setText(String.valueOf(temporadas.get(position).getQuantidadeEpisodios()));
        holder.text_anoT.setText(String.valueOf(temporadas.get(position).getAnoLancamento()));

        holder.image_delete.setTag(position);
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                //enviando os dados para a tela de remocao de temporada
                Intent intent = new Intent(view.getContext(), RemocaoTemporadaActivity.class);
                intent.putExtra("temporadaClicada",temporadas.get(clickPosition).getNumeroSequencial());
                intent.putExtra("serieClicada",temporadas.get(clickPosition).getNomeSerie());
                view.getContext().startActivity(intent);
            }
        });
        holder.image_edita.setTag(position);
        holder.image_edita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                Intent intent = new Intent(view.getContext(), FormEditaTemporadaActivity.class);
                intent.putExtra("temporadaClicada",temporadas.get(clickPosition).getNumeroSequencial());
                intent.putExtra("serieClicada",temporadas.get(clickPosition).getNomeSerie());
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (temporadas != null && temporadas.size() > 0) ? temporadas.size():0;

    }
    static class ListaTemporadasViewHolder extends RecyclerView.ViewHolder {
        private TextView texQuantEpisodio,textAnoTemporada,textNumSeq,numSeq,quant_episodio,text_anoT;
        private Temporada temporada;
        private ImageView imagePosterSerie,image_delete,image_edita;

        public ListaTemporadasViewHolder(View itemView) {
            super(itemView);

            texQuantEpisodio = itemView.findViewById(R.id.text_quant_episodio);
            textNumSeq = itemView.findViewById(R.id.text_num_seq);
            textAnoTemporada = itemView.findViewById(R.id.text_ano);

            numSeq = itemView.findViewById(R.id.num_seq);
            quant_episodio = itemView.findViewById(R.id.quant_episodio);
            text_anoT = itemView.findViewById(R.id.text_anoEt);

            image_delete = itemView.findViewById(R.id.image_delete);
            image_edita = itemView.findViewById(R.id.image_edita);
        }
        public void bind(Temporada temporada){
            this.temporada = temporada;
            textNumSeq.setText(String.valueOf(temporada.getNumeroSequencial()));

        }
    }
    }