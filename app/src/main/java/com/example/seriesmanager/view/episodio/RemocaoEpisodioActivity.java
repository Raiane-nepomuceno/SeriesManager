package com.example.seriesmanager.view.episodio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.view.temporada.RemocaoTemporadaActivity;
import com.example.seriesmanager.view.temporada.TemporadaActivity;

public class RemocaoEpisodioActivity extends AppCompatActivity {
    private TextView numTempEt,nomeSerieEt,numEpisodioEt;
    private Banco db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_deletar_episodio);
        Bundle numTemp = getIntent().getExtras();

        numTempEt= findViewById(R.id.numTempEt);
        nomeSerieEt = findViewById(R.id.nomeSerieEt);
        numEpisodioEt = findViewById(R.id.numEpisodioEt);

        if(numTemp!=null){
            int temporadaClicada = numTemp.getInt("temporadaClicada");
            String nomeSerie = numTemp.getString("serieClicada");
           int numTempEp = numTemp.getInt("episodioClicado");

            numTempEt.setText(String.valueOf(temporadaClicada));
            nomeSerieEt.setText(nomeSerie);
            numEpisodioEt.setText(String.valueOf(numTempEp));
        }

    }
    public void removerEpisodio(View view) {

        db = new Banco(this);

       Boolean resultado = db.deleteEpisodios(numTempEt.getText().toString(),nomeSerieEt.getText().toString(),numEpisodioEt.getText().toString());
        if(resultado == true)
        {
            Toast.makeText(RemocaoEpisodioActivity.this, "Episódio excluído com sucesso! :)", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(view.getContext(),TemporadaActivity.class);
            intent.putExtra("temporadaClicada",numTempEt.getText().toString());
            intent.putExtra("serieClicada",nomeSerieEt.getText().toString());
            startActivity(intent);

        }
        else{
            Toast.makeText(RemocaoEpisodioActivity.this, "Por favor informe o nome válido de série. Tente novamente!", Toast.LENGTH_LONG).show();

        }
        db.close();
    }
}

