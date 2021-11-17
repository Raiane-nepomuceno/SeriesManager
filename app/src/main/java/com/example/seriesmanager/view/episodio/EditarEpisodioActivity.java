package com.example.seriesmanager.view.episodio;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;

public class EditarEpisodioActivity extends AppCompatActivity {
    private TextView numTempEt,nomeSerieEt,numEpisodioEt;
    private Banco db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_editar_episodio);

        numTempEt= findViewById(R.id.numTempEt);
        nomeSerieEt = findViewById(R.id.nomeSerieEt);
        numEpisodioEt = findViewById(R.id.numEpisodioEt);

        Bundle numTemp = getIntent().getExtras();

        if(numTemp!=null){
            int temporadaClicada = numTemp.getInt("temporadaClicada");
            String nomeSerie = numTemp.getString("serieClicada");
            int numTempEp = numTemp.getInt("episodioClicado");

            numTempEt.setText(String.valueOf(temporadaClicada));
            nomeSerieEt.setText(nomeSerie);
            numEpisodioEt.setText(String.valueOf(numTempEp));
        }

    }
}
