package com.example.seriesmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;
import com.example.seriesmanager.model.Serie;

import java.util.List;

public class FormEditaActivity extends AppCompatActivity {
    private EditText nomeSerieEt,nomeAntigoSerieEt,lancamentoEt,emissoraEt,generoEt;
    private Banco db;
    private List<Serie> listaSeries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_edita_serie);

        nomeSerieEt = findViewById(R.id.nomeEt);
        lancamentoEt = findViewById(R.id.lancamentoEt);
        emissoraEt = findViewById(R.id.emissoraEt);
        generoEt = findViewById(R.id.generoEt);
        nomeAntigoSerieEt = findViewById(R.id.nomeAntigoEt);

        Bundle nomeSerie = getIntent().getExtras();
        if(nomeSerie!=null){
            String serieClicada = nomeSerie.getString("serieClicada");
            nomeAntigoSerieEt.setText(serieClicada);

        }
    }
    public void salvarEdicaoSerie(View view) {

        if (nomeSerieEt != null && emissoraEt != null && generoEt != null && lancamentoEt != null && lancamentoEt != null && nomeAntigoSerieEt!=null) {
            Serie serie = new Serie();
            db = new Banco(this);
            serie.setNome(nomeSerieEt.getText().toString());
            serie.setEmissora(emissoraEt.getText().toString());
            serie.setGenero(generoEt.getText().toString());
            try {
                serie.setAnoLancamento(Integer.parseInt(lancamentoEt.getText().toString()));
                Boolean resultado = db.updateSerie(serie,nomeAntigoSerieEt.getText().toString());
                if(resultado == true)
                {
                    Toast.makeText(FormEditaActivity.this, "Temporada editada com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, TemporadaActivity.class);
                    startActivity(intent);

                    db.close();
                }
                else{

                    Toast.makeText(FormEditaActivity.this, "Os campos já estão vazios, vamos preencher! :)", Toast.LENGTH_LONG).show();
                    db.close();
                }

            }catch (Exception e){
                Toast.makeText(FormEditaActivity.this, "Os campos estão inválidos. Por favor preencha novamente!", Toast.LENGTH_LONG).show();

            }

        }else{
            Toast.makeText(FormEditaActivity.this, "Os campos estão inválidos. Por favor preencha novamente!", Toast.LENGTH_LONG).show();
            db.close();
        }

        }
        public void limparCampos(View view){
            if(view.getId() == R.id.btnLimpar)
            {
                nomeSerieEt.getText().clear();
                lancamentoEt.getText().clear();
                emissoraEt.getText().clear();
                generoEt.getText().clear();
                nomeAntigoSerieEt.getText().clear();

            }
        }

}







