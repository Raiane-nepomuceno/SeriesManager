package com.example.seriesmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.SerieDAO;
import com.example.seriesmanager.model.Serie;

public class FormAddSerieActivity extends AppCompatActivity {
    private EditText nomeSerieEt,lancamentoEt,emissoraEt,generoEt;
    private Button btnLimpar;
    private SerieDAO db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_serie);

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

        Serie serie = new Serie();
        db = new SerieDAO(this);

        serie.setNome(nomeSerieEt.getText().toString());
        serie.setEmissora(emissoraEt.getText().toString());
        serie.setGenero(generoEt.getText().toString());
        serie.setAnoLancamento(Integer.parseInt(lancamentoEt.getText().toString()));

        Boolean resultado = db.insertSerie(serie);
        if(resultado == true)
        {
            Toast.makeText(FormAddSerieActivity.this, "Série adicionada com sucesso! :)", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(FormAddSerieActivity.this, "Os campos já estão vazios, vamos preencher! :)", Toast.LENGTH_LONG).show();

        }
        db.close();


    }

    }
