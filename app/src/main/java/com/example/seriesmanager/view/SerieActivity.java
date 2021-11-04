package com.example.seriesmanager.view;


import static java.util.logging.Level.parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Serie;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SerieActivity extends AppCompatActivity {
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

    private List<Serie> criarSeries(){
        return Arrays.asList(
                new Serie("Round 6",new Date(),"Chinesa","drama"),
                new Serie("Round 6",new Date(),"Chinesa","drama"),
                new Serie("Round 6",new Date(),"Chinesa","drama"),
                new Serie("Round 6",new Date(),"Chinesa","drama"),
                new Serie("Round 6",new Date(),"Chinesa","drama")

        );

    }
    public void editarSerie(View view) {
        //Toast.makeText(SerieActivity.this, "Clicou no botão editar", Toast.LENGTH_SHORT).show();
        openFormEdita();
    }

    public void removerSerie(View view){
        Toast.makeText(SerieActivity.this, "Clicou no botão remover", Toast.LENGTH_SHORT).show();

    }


}