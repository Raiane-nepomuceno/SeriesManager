package com.example.seriesmanager.view.temporada;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Temporada;
import com.example.seriesmanager.view.adapter.ListaSeriesAdapter;
import com.example.seriesmanager.view.adapter.ListaTemporadasAdapter;
import com.example.seriesmanager.view.temporada.RemocaoTemporadaActivity;

import java.util.ArrayList;
import java.util.List;

public class TemporadaActivity extends AppCompatActivity {
    private Banco db;
    public ImageView mDeleteImage;
    private List<Temporada> listaTemporadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporada_main_activity);

        listaTemporadas = new ArrayList<>();

        RecyclerView recyclerTemporadas = findViewById(R.id.recycler_temporadas);
        mDeleteImage = findViewById(R.id.image_delete);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerTemporadas.setLayoutManager(linearLayoutManager);

        recyclerTemporadas.setAdapter(new ListaTemporadasAdapter(carregarTemporadas()));

    }
    public void adicionarTemporada(View view){
        Intent intent = new Intent(this, FormTemporadaActivity.class);
        startActivity(intent);
    }
    public void removerTemporada(View view){
        Intent intent = new Intent(this, RemocaoTemporadaActivity.class);
        startActivity(intent);
    }
    public void editarTemporada(View view){
        Intent intent = new Intent(this, FormEditaTemporadaActivity.class);
        startActivity(intent);

    }
    private List<Temporada> carregarTemporadas(){
        Bundle nomeSerie = getIntent().getExtras();
        if(nomeSerie != null){
            String serieClicada = nomeSerie.getString("serieClicada");
            db = new Banco(this);
            Cursor cursor = db.getTemporadas(serieClicada);
            while(cursor.moveToNext()) {
                Temporada temporada = new Temporada();
                temporada.setQuantidadeEpisodios(cursor.getInt(cursor.getColumnIndex("quantidadeEpisodios")));
                temporada.setNumeroSequencial(cursor.getInt(cursor.getColumnIndex("numeroSequencialTemp")));
                temporada.setAnoLancamento(cursor.getInt(cursor.getColumnIndex("anoLancamento")));
                temporada.setNomeSerie(cursor.getString(cursor.getColumnIndex("nomeSerie")));
                listaTemporadas.add(temporada);
            }
        }
        db.close();
        return listaTemporadas;

    }
}