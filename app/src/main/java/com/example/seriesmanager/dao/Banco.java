package com.example.seriesmanager.dao;

import androidx.annotation.NonNull;

import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.view.adapter.ListaSeriesAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private ListaSeriesAdapter mAdapter;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private List<Serie> listaSeries = new ArrayList<>();

    public List<Serie> listarSeries(){
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        ref.child("series").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Serie serie = dataSnapshot.getValue(Serie.class);
                mAdapter.notifyDataSetChanged();
                listaSeries.add(serie);
                mAdapter = new ListaSeriesAdapter(listaSeries);
            }


        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Erro na leitura: "+error.getCode());
            }
        });
    return listaSeries;
}
}
