package com.example.seriesmanager.view.temporada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.view.serie.RemocaoActivity;
import com.example.seriesmanager.view.serie.SerieActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RemocaoTemporadaActivity extends AppCompatActivity {
    private EditText numTempEt,nomeSerieEt;
    private Button btnRemoverTemp;
    private DatabaseReference referencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_deletar_temporada);
        btnRemoverTemp = findViewById(R.id.btnRemoverTemp);
        numTempEt= findViewById(R.id.numTempEt);
        nomeSerieEt = findViewById(R.id.nomeSerieEt);

        Bundle numTemp = getIntent().getExtras();

        if(numTemp!=null){
           int temporadaClicada = numTemp.getInt("temporadaClicada");
            String nomeSerie = numTemp.getString("serieClicada");
            numTempEt.setText(String.valueOf(temporadaClicada));
            nomeSerieEt.setText(nomeSerie);
        }


    }
            public void removerTemporada(View view) {
                referencia = FirebaseDatabase.getInstance().getReference();
                referencia.child("temporadas").child(numTempEt.getText().toString()).child(nomeSerieEt.getText().toString());
                //Query query = referencia.child(numTempEt.getText().toString()).child(nomeSerieEt.getText().toString());
                referencia.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            dataSnapshot.getRef().removeValue();
                            Toast.makeText(RemocaoTemporadaActivity.this, "entrei na função ondaDatChange", Toast.LENGTH_LONG).show();
                            System.out.println("dataSnapshot"+dataSnapshot.getValue());
                            Toast.makeText(RemocaoTemporadaActivity.this, "Temporada excluída com sucesso! :)", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(view.getContext(), SerieActivity.class);
                            intent.putExtra("temporadaClicada",numTempEt.getText().toString());
                            intent.putExtra("serieClicada",nomeSerieEt.getText().toString());
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Erro na leitura: "+error.getCode());
                        Toast.makeText(RemocaoTemporadaActivity.this, "Por favor informe o nome válido de série. Tente novamente!", Toast.LENGTH_LONG).show();

                    }
                });

            }
}
