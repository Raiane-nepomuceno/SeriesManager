package com.example.seriesmanager.view.serie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.Banco;

public class RemocaoActivity extends AppCompatActivity {
    private Banco db;
    private EditText nomeSerieEt;



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

                db = new Banco(this);

                Boolean resultado = db.deleteSeries(nomeSerieEt.getText().toString());
                if(resultado == true)
                {
                    Toast.makeText(RemocaoActivity.this, "Série excluída com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, SerieActivity.class);
                    startActivity(intent);


                }
                else{
                    Toast.makeText(RemocaoActivity.this, "Por favor informe o nome válido de série. Tente novamente!", Toast.LENGTH_LONG).show();

                }
                db.close();


            }
        }
