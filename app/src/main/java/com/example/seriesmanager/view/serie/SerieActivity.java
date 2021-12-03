package com.example.seriesmanager.view.serie;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.view.adapter.ListaSeriesAdapter;
import com.example.seriesmanager.view.temporada.FormTemporadaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SerieActivity extends AppCompatActivity implements ListaSeriesAdapter.ItemSerieClickListener {
    private ListaSeriesAdapter mAdapter;
    private TextView textTituloSerie;
    public ImageView mDeleteImage;
    private List<Serie> listaSeries;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serie_activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        criarSeries();
        listaSeries = new ArrayList<>();

        RecyclerView recyclerSeries = findViewById(R.id.recycler_series);
        mDeleteImage = findViewById(R.id.image_delete);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mAdapter = new ListaSeriesAdapter(listaSeries);

        recyclerSeries.setLayoutManager(linearLayoutManager);
        recyclerSeries.setAdapter(mAdapter);
    }

    public void openFormEdita() {
        Intent intent = new Intent(this, FormEditaActivity.class);
        startActivity(intent);
    }

    public void openAddSerie() {
        Intent intent = new Intent(this, FormAddSerieActivity.class);
        startActivity(intent);
    }

    private List<Serie> criarSeries() {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.child("series").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Serie serie = dataSnapshot.getValue(Serie.class);
                    listaSeries.add(serie);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Erro na leitura: "+error.getCode());
            }
        });
        //System.out.println("Tamnho lista serie:"+listaSeries.size());
        return listaSeries;

    }

    public void adicionarTemporada(View view) {
        Intent intent = new Intent(this, FormTemporadaActivity.class);
        startActivity(intent);

    }

    public void adicionarSerie(View view) {
        openAddSerie();
        criarSeries();
    }

    public void editarSerie(View view) {
        openFormEdita();
    }

    public void removerSerie(View view) {
        Intent intent = new Intent(this, RemocaoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.atualizarMi)
        {
            mAdapter.notifyDataSetChanged();
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public void onItemClicado(Serie serie) {

    }
}
