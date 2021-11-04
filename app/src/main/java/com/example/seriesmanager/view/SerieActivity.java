package com.example.seriesmanager.view;


import static java.util.logging.Level.parse;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.SerieDAO;
import com.example.seriesmanager.model.Serie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SerieActivity extends AppCompatActivity {
    private SerieDAO db;
    private TextView textTituloSerie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serie_activity_main);

        RecyclerView recyclerSeries = findViewById(R.id.recycler_series);
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
        List<Serie> listaSeries = new ArrayList<>();
        while(cursor.moveToNext())
        {
            Serie serie = new Serie();
            serie.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            serie.setEmissora(cursor.getString(cursor.getColumnIndex("emissora")));
            serie.setGenero(cursor.getString(cursor.getColumnIndex("genero")));
            serie.setAnoLancamento(cursor.getInt(cursor.getColumnIndex("anoLancamento")));
            listaSeries.add(serie);
        }
        return listaSeries;
    }

    public void adicionarSerie(View view){
        openAddSerie();

    }
    public void editarSerie(View view) {
        openFormEdita();
    }

    public void removerSerie(View view){
        db = new SerieDAO(this);
        textTituloSerie = view.findViewById(R.id.text_titulo_serie);

        String str = textTituloSerie.toString() ;
        Toast.makeText(this, str,Toast.LENGTH_SHORT).show();


        //boolean resultado = db.deleteSeries(textTituloSerie.toString());
        /*if(resultado == true){
            Toast.makeText(SerieActivity.this, "Série excluída com sucesso", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(SerieActivity.this, "Houve um erro na exclusão da série. Tente novamente!", Toast.LENGTH_SHORT).show();

        }*/

    }


}