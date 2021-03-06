package com.example.seriesmanager.view.serie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Serie;
import com.example.seriesmanager.view.adapter.ListaSeriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class FormAddSerieActivity extends AppCompatActivity {
    private EditText nomeSerieEt,lancamentoEt,emissoraEt,generoEt;
    private Button btnLimpar;
    private Banco db;
    private ListaSeriesAdapter mAdapter;
    private List<Serie> listaSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_serie);
        listaSeries = new ArrayList<>();

        nomeSerieEt = findViewById(R.id.nomeEt);
        lancamentoEt = findViewById(R.id.lancamentoEt);
        emissoraEt = findViewById(R.id.emissoraEt);
        generoEt = findViewById(R.id.generoEt);
        btnLimpar = findViewById(R.id.btnLimpar);


    }
    public boolean limpar(View view){

    if(view.getId() == R.id.btnLimpar)
    {
        nomeSerieEt.getText().clear();
        lancamentoEt.getText().clear();
        emissoraEt.getText().clear();
        generoEt.getText().clear();
        return true;
    }
    else{
        Toast.makeText(FormAddSerieActivity.this, "Os campos já estão vazios :)", Toast.LENGTH_SHORT).show();
        return false;

        }
    }

    public void salvarSerie(View view){

        db = new Banco(this);

        if(nomeSerieEt != null &&  emissoraEt!=null && generoEt!=null && lancamentoEt!=null && lancamentoEt!=null)
        {
            try {
            Serie serie = new Serie();
            serie.setNome(nomeSerieEt.getText().toString());
            serie.setEmissora(emissoraEt.getText().toString());
            serie.setGenero(generoEt.getText().toString());
            serie.setAnoLancamento(Integer.parseInt(lancamentoEt.getText().toString()));

            listaSeries.add(serie);

            Boolean resultado = db.insertSerie(serie);


        if(resultado == true)
        {
            Toast.makeText(FormAddSerieActivity.this, "Série adicionada com sucesso! :)", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SerieActivity.class);
            startActivity(intent);

            db.close();
        }
        else{

            Toast.makeText(FormAddSerieActivity.this, "Os campos já estão vazios, vamos preencher! :)", Toast.LENGTH_LONG).show();
            db.close();
        }
        }catch (Exception e){
                Toast.makeText(FormAddSerieActivity.this, "Os campos estão inválidos. Por favor preencha novamente!", Toast.LENGTH_LONG).show();

            }
        }else{
            Toast.makeText(FormAddSerieActivity.this, "Os campos estão inválidos. Por favor preencha novamente!", Toast.LENGTH_LONG).show();
            db.close();
        }



    }

    }
