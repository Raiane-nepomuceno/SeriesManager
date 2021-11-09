package com.example.seriesmanager.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.TemporadaDAO;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.model.Temporada;

import java.util.ArrayList;
import java.util.List;

public class FormTemporadaActivity extends AppCompatActivity {
    private EditText nomeSerie, anoLancamento, quantidadeEpisodiosTemp, numeroTempEt;
    private Button btnlimpar;
    private TemporadaDAO db;
    private List<Temporada> listaTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_temporada);
        listaTemp = new ArrayList<>();

        nomeSerie = findViewById(R.id.nomeEt);
        anoLancamento = findViewById(R.id.lancamentoEt);
        quantidadeEpisodiosTemp = findViewById(R.id.quantidadeEpisodiosTempEt);
        numeroTempEt = findViewById(R.id.numeroTempEt);
        btnlimpar = findViewById(R.id.btnLimpar);
    }
    public boolean limpar(View view){
        if(view.getId() == R.id.btnLimpar){
            nomeSerie.getText().clear();
            anoLancamento.getText().clear();
            quantidadeEpisodiosTemp.getText().clear();
            numeroTempEt.getText().clear();
            return true;
        }
        else{
            Toast.makeText(FormTemporadaActivity.this, "Os campos já estão vazios :)", Toast.LENGTH_SHORT).show();
            return false;

        }

    }
    public void salvarTemporada(View view) {
            db = new TemporadaDAO(this);
            try {
                Temporada temporada = new Temporada();

                temporada.setNumeroSequencial(Integer.parseInt(numeroTempEt.getText().toString()));
                temporada.setAnoLancamento(Integer.parseInt(anoLancamento.getText().toString()));
                temporada.setQuantidadeEpisodios(Integer.parseInt(quantidadeEpisodiosTemp.getText().toString()));

                listaTemp.add(temporada);

                Boolean resultado = db.insertTemporadas(temporada,nomeSerie.getText().toString());

                if (resultado == true) {
                    Toast.makeText(FormTemporadaActivity.this, "Temporada adicionada com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, SerieActivity.class);
                    startActivity(intent);

                    db.close();
                }
    }catch (Exception e){
                Toast.makeText(FormTemporadaActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();

            }
}}
