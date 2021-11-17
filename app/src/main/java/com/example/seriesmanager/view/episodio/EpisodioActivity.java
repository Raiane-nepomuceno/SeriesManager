package com.example.seriesmanager.view.episodio;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Episodio;
import com.example.seriesmanager.view.adapter.ListaEpisodioAdapter;
import com.example.seriesmanager.view.serie.FormAddSerieActivity;
import com.example.seriesmanager.view.serie.RemocaoActivity;

import java.util.ArrayList;
import java.util.List;

public class EpisodioActivity extends AppCompatActivity {
    private List<Episodio> listaEpisodios;
    private Banco db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episodio_activity_main);

        listaEpisodios = new ArrayList<>();
        RecyclerView recyclerEpisodios = findViewById(R.id.recycler_episodios);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerEpisodios.setLayoutManager(linearLayoutManager);

       recyclerEpisodios.setAdapter(new ListaEpisodioAdapter(carregarEpisodios()));

    }
    public void removerEpisodio(View view){
        Intent intent = new Intent(this, RemocaoEpisodioActivity.class);
        startActivity(intent);

    }
    private List<Episodio> carregarEpisodios(){
        Bundle nomeSerie = getIntent().getExtras();
        if(nomeSerie != null){
            String serieClicada = nomeSerie.getString("serieClicada");
            int temporadaClicada = nomeSerie.getInt("temporadaClicada");

            db = new Banco(this);

            //falta editar aqui
            Cursor cursor = db.getEpisodios(serieClicada,String.valueOf(temporadaClicada));

            while(cursor.moveToNext()) {
                Episodio episodio = new Episodio();
                episodio.setTempoDuracao(cursor.getInt(cursor.getColumnIndex("tempoDuracao")));
                int flag = cursor.getInt(cursor.getColumnIndex("flag"));
                if(flag == 0)
                {
                    episodio.setFlag(false);
                }
                else{
                    episodio.setFlag(true);
                }
                episodio.setNumeroSequencial(cursor.getInt(cursor.getColumnIndex("numeroSequencialEp")));
                episodio.setNumeroSequencialTemp(cursor.getInt(cursor.getColumnIndex("numeroSequencialTemp")));
                episodio.setNome(cursor.getString(cursor.getColumnIndex("nomeSerie")));
                listaEpisodios.add(episodio);

            }
        }
        db.close();
        return listaEpisodios;

    }
}