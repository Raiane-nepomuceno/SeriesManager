package com.example.seriesmanager.view.serie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RemocaoActivity extends AppCompatActivity {
    private EditText nomeSerieEt;
    private DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_deletar_serie);

        nomeSerieEt = findViewById(R.id.nomeSerieEt);
        Bundle nomeSerie = getIntent().getExtras();
        if(nomeSerie!=null){
            String serieClicada = nomeSerie.getString("serieClicada");
            nomeSerieEt.setText(serieClicada);
        }

    }
            public void removerSerie(View view) {
                referencia = FirebaseDatabase.getInstance().getReference();
                Query query = referencia.child("series").orderByChild("nome").equalTo(nomeSerieEt.getText().toString());

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(RemocaoActivity.this, "Série excluída com sucesso! :)", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(view.getContext(), SerieActivity.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Erro na leitura: "+error.getCode());

                    }
                });

            }
}
