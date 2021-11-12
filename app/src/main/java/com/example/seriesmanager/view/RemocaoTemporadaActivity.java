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
    private EditText numTempEt;
    private Button btnRemoverTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_deletar_temporada);
        btnRemoverTemp = findViewById(R.id.btnRemoverTemp);
        numTempEt= findViewById(R.id.nomeNumTempEt);
        Bundle numTemp = getIntent().getExtras();
        if(numTemp!=null){
           int temporadaClicada = numTemp.getInt("temporadaClicada");
            numTempEt.setText(String.valueOf(temporadaClicada));

        }

    }
            public void removerTemporada(View view) {

                db = new Banco(this);
                int num = Integer.parseInt(numTempEt.getText().toString());

                Boolean resultado = db.deleteTemporadas(num);
                if(resultado == true)
                {
                    Toast.makeText(RemocaoTemporadaActivity.this, "Temporada excluída com sucesso! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, TemporadaActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(RemocaoTemporadaActivity.this, "Por favor informe o nome válido de série. Tente novamente!", Toast.LENGTH_LONG).show();

                }
                db.close();


            }
        }
