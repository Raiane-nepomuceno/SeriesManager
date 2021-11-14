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

public class RemocaoTemporadaActivity extends AppCompatActivity {
    private Banco db;
    private EditText numTempEt,nomeSerieEt;
    private Button btnRemoverTemp;

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

                db = new Banco(this);

                Boolean resultado = db.deleteTemporadas(numTempEt.getText().toString(),nomeSerieEt.getText().toString());
                if(resultado == true)
                {
                    Toast.makeText(RemocaoTemporadaActivity.this, "Temporada excluída com sucesso! :)", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(view.getContext(),TemporadaActivity.class);
                    intent.putExtra("temporadaClicada",numTempEt.getText().toString());
                    intent.putExtra("serieClicada",nomeSerieEt.getText().toString());
                    startActivity(intent);

                }
                else{
                    Toast.makeText(RemocaoTemporadaActivity.this, "Por favor informe o nome válido de série. Tente novamente!", Toast.LENGTH_LONG).show();

                }
                db.close();
            }
        }
