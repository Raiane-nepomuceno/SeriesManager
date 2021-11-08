package com.example.seriesmanager.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.SerieDAO;
import com.example.seriesmanager.model.Serie;

import java.util.ArrayList;
import java.util.List;

public class SerieActivity extends AppCompatActivity {
    private SerieDAO db;
    private ListaSeriesAdapter mAdapter;
    private TextView textTituloSerie;
    public ImageView mDeleteImage;
    private List<Serie> listaSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serie_activity_main);
        listaSeries = new ArrayList<>();

        RecyclerView recyclerSeries = findViewById(R.id.recycler_series);
        mDeleteImage = findViewById(R.id.image_delete);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerSeries.setLayoutManager(linearLayoutManager);
        recyclerSeries.setAdapter(new ListaSeriesAdapter(criarSeries()));

    }

    public void openFormEdita()
    {
        Intent intent = new Intent(this, FormEditaActivity.class);
        startActivity(intent);

    }
    public void openAddSerie(){
        Intent intent = new Intent(this,FormAddSerieActivity.class);
        startActivity(intent);
    }
    private List<Serie> criarSeries(){
        db = new SerieDAO(this);

        Cursor cursor = db.getSeries();
        while(cursor.moveToNext())
        {
            Serie serie = new Serie();
            serie.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            serie.setEmissora(cursor.getString(cursor.getColumnIndex("emissora")));
            serie.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
            serie.setAnoLancamento(cursor.getInt(cursor.getColumnIndex("anoLancamento")));
            listaSeries.add(serie);
        }
        db.close();
        return listaSeries;
    }

    public void adicionarSerie(View view){
        openAddSerie();
        criarSeries();
    }
    public void editarSerie(View view) {
        openFormEdita();
        criarSeries();

    }


    public void removerSerie(View view) {
        Intent intent = new Intent(this, RemocaoActivity.class);
        startActivity(intent);
        criarSeries();

    }
}