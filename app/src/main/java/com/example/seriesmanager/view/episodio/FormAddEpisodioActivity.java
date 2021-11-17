package com.example.seriesmanager.view.episodio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Episodio;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.model.Temporada;
import com.example.seriesmanager.view.temporada.FormEditaTemporadaActivity;
import com.example.seriesmanager.view.temporada.FormTemporadaActivity;
import com.example.seriesmanager.view.temporada.TemporadaActivity;

import java.util.ArrayList;
import java.util.List;

public class FormAddEpisodioActivity extends AppCompatActivity {
    private EditText numSeqEpisodioEt,nomeEpisodioEt,duracaoEpisodioEt;
    private Banco db;
    private TextView nomeSerieEt,numTemp;
    private List<Episodio> listaEpisodios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_add_episodio);

        numSeqEpisodioEt = findViewById(R.id.numSeqEpisodioEt);
        nomeEpisodioEt = findViewById(R.id.nomeEpisodioEt);
        duracaoEpisodioEt = findViewById(R.id.duracaoEpisodioEt);
        nomeSerieEt = findViewById(R.id.nomeSerieEt);
        numTemp = findViewById(R.id.numTempEt);

        listaEpisodios = new ArrayList<>();

        Bundle nomeSerieB = getIntent().getExtras();

        if(nomeSerieB != null){
            String serieClicada = nomeSerieB.getString("serieClicada");
            int temporadaClicada = nomeSerieB.getInt("temporadaClicada");
            nomeSerieEt.setText(serieClicada);
            numTemp.setText(String.valueOf(temporadaClicada));

        }
    }
    public void salvarEpisodio(View view){
        db = new Banco(this);
      try {


            Bundle nomeSerie = getIntent().getExtras();
            if(nomeSerie != null) {
                String serieClicada = nomeSerie.getString("serieClicada");
                int temporadaClicada = nomeSerie.getInt("temporadaClicada");

                Episodio episodio = new Episodio();
                episodio.setNumeroSequencial(Integer.parseInt(numSeqEpisodioEt.getText().toString()));
                episodio.setNome(nomeEpisodioEt.getText().toString());
                episodio.setTempoDuracao(Integer.parseInt(duracaoEpisodioEt.getText().toString()));
                episodio.setFlag(false);
                episodio.setNumeroSequencialTemp(temporadaClicada);

                    listaEpisodios.add(episodio);

                    //db = new Banco(this);
                    Boolean resultado = db.insertEpisodio(episodio, serieClicada);


                if (resultado == true) {
                    Toast.makeText(FormAddEpisodioActivity.this, "Episódio adicionada com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), TemporadaActivity.class);
                    intent.putExtra("temporadaClicada", numTemp.getText().toString());
                    intent.putExtra("serieClicada", nomeSerieEt.getText().toString());
                    startActivity(intent);

                    db.close();
                }
                }

        }catch (Exception e){
            Toast.makeText(FormAddEpisodioActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();

        }

    }
    public void limparDadosEpisodio(View view){
        numSeqEpisodioEt.getText().clear();
        nomeEpisodioEt.getText().clear();
        duracaoEpisodioEt.getText().clear();
    }
    }