package com.example.seriesmanager.view;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Temporada;

import java.util.ArrayList;
import java.util.List;

public class FormTemporadaActivity extends AppCompatActivity {
    private EditText nomeSerie, anoLancamento, quantidadeEpisodiosTemp, numeroTempEt;
    private Button btnlimpar;
    private Banco db;
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
        btnlimpar = findViewById(R.id.btnLimparTemp);
        Bundle nomeSerieB = getIntent().getExtras();
        if(nomeSerieB!=null){
            String serieClicada = nomeSerieB.getString("serieClicada");
            nomeSerie.setText(serieClicada);

        }
    }
    public boolean limparDadosTemp(View view){
        if(view.getId() == R.id.btnLimparTemp){
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
            db = new Banco(this);
            try {

                Temporada temporada = new Temporada();

                temporada.setNumeroSequencial(Integer.parseInt(numeroTempEt.getText().toString()));
                temporada.setAnoLancamento(Integer.parseInt(anoLancamento.getText().toString()));
                temporada.setQuantidadeEpisodios(Integer.parseInt(quantidadeEpisodiosTemp.getText().toString()));
                temporada.setNomeSerie(nomeSerie.getText().toString());

                listaTemp.add(temporada);

                Boolean resultado = db.insertTemporadas(temporada,nomeSerie.getText().toString());

                if (resultado == true) {
                    Toast.makeText(FormTemporadaActivity.this, "Temporada adicionada com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(),TemporadaActivity.class);
                    intent.putExtra("temporadaClicada",temporada.getNumeroSequencial());
                    intent.putExtra("serieClicada",nomeSerie.getText().toString());
                    view.getContext().startActivity(intent);

                    db.close();
                }
               }catch (Exception e){
                Toast.makeText(FormTemporadaActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();

            }
}}
