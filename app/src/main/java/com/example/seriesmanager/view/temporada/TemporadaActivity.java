package com.example.seriesmanager.view.temporada;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.model.Temporada;
import com.example.seriesmanager.view.adapter.ListaSeriesAdapter;
import com.example.seriesmanager.view.adapter.ListaTemporadasAdapter;
import com.example.seriesmanager.view.temporada.RemocaoTemporadaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TemporadaActivity extends AppCompatActivity implements ListaTemporadasAdapter.ItemTemporadaClickListener {
    public ImageView mDeleteImage;
    private List<Temporada> listaTemporadas;
    private ListaTemporadasAdapter mAdapter;
    private DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temporada_main_activity);

        setSupportActionBar(findViewById(R.id.toolbar));
        carregarTemporadas();
        listaTemporadas = new ArrayList<>();

        RecyclerView recyclerTemporadas = findViewById(R.id.recycler_temporadas);
        mDeleteImage = findViewById(R.id.image_delete);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mAdapter = new ListaTemporadasAdapter(listaTemporadas);

        recyclerTemporadas.setLayoutManager(linearLayoutManager);
        recyclerTemporadas.setAdapter(mAdapter);

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
        String serieClicada = nomeSerie.getString("serieClicada");

         referencia = FirebaseDatabase.getInstance().getReference();
         Query query = referencia.child("temporadas").orderByChild("nomeSerie").equalTo(serieClicada);
         query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Temporada temporada = dataSnapshot.getValue(Temporada.class);
                    listaTemporadas.add(temporada);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Erro na leitura: "+error.getCode());
            }
        });
        return listaTemporadas;
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
    public void onItemClicado(Temporada temporada) {

    }
}