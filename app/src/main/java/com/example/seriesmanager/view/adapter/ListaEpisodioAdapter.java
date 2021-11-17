package com.example.seriesmanager.view.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Episodio;
import com.example.seriesmanager.view.episodio.RemocaoEpisodioActivity;

import java.util.List;

public class ListaEpisodioAdapter extends RecyclerView.Adapter<ListaEpisodioAdapter.ListaEpisodioViewHolder>{
    private List<Episodio> episodios;

    public ListaEpisodioAdapter(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    public ListaEpisodioAdapter() {
    }

    @NonNull
    @Override
    public ListaEpisodioAdapter.ListaEpisodioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_episodio, parent, false);
        return new ListaEpisodioAdapter.ListaEpisodioViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListaEpisodioAdapter.ListaEpisodioViewHolder holder, int position) {
        holder.bind(episodios.get(position));

        holder.text_num_seq_ep.setText("Episódio:");
        holder.text_nome_episodio.setText("Nome:");
        holder.text_duracao.setText("Duração");

        holder.nome_ep.setText(episodios.get(position).getNome());
        holder.tempo_duracao_ep.setText(String.valueOf(episodios.get(position).getTempoDuracao()));

        holder.image_delete.setTag(position);
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickPosition = (int) view.getTag();
                //enviando os dados para a tela de remocao de temporada
                Intent intent = new Intent(view.getContext(), RemocaoEpisodioActivity.class);
                intent.putExtra("temporadaClicada",episodios.get(clickPosition).getNumeroSequencialTemp());
                intent.putExtra("serieClicada",episodios.get(clickPosition).getNome());
                intent.putExtra("episodioClicado",episodios.get(clickPosition).getNumeroSequencialTemp());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (episodios != null && episodios.size() > 0) ? episodios.size():0;

    }
    static class ListaEpisodioViewHolder extends RecyclerView.ViewHolder {
        private TextView tempo_duracao_ep,text_duracao,num_seq_ep,text_num_seq_ep,text_nome_episodio,nome_ep;
        private Episodio episodio;
        private ImageView image_edit, image_delete;


        public ListaEpisodioViewHolder(@NonNull View itemView) {
            super(itemView);

            text_num_seq_ep = itemView.findViewById(R.id.text_num_seq_ep);
            num_seq_ep = itemView.findViewById(R.id.num_seq_ep);
            text_nome_episodio = itemView.findViewById(R.id.text_nome_episodio);
            nome_ep = itemView.findViewById(R.id.nome_ep);
            text_duracao = itemView.findViewById(R.id.text_duracao);
            tempo_duracao_ep = itemView.findViewById(R.id.tempo_duracao_ep);

            image_delete = itemView.findViewById(R.id.image_delete);
        }
        public void bind(Episodio episodio) {
            this.episodio = episodio;
            num_seq_ep.setText(String.valueOf(episodio.getNumeroSequencial()));

        }
    }
}