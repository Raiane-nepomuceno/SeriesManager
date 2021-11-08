package com.example.seriesmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seriesmanager.R;
import com.example.seriesmanager.dao.SerieDAO;
import com.example.seriesmanager.model.Serie;

import java.util.List;

public class RemocaoActivity extends AppCompatActivity {
    private SerieDAO db;
    private TextView textTituloSerie;
    private EditText nomeSerieEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmacao_deletar_serie);


        textTituloSerie = findViewById(R.id.text_titulo_serie);
        nomeSerieEt = findViewById(R.id.nomeSerieEt);



    }
            public void removerSerie(View view) {

                db = new SerieDAO(this);

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
