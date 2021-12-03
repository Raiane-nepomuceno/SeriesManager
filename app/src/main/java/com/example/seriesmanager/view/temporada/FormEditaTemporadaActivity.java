package com.example.seriesmanager.view.temporada;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seriesmanager.R;
import com.example.seriesmanager.model.Temporada;

public class FormEditaTemporadaActivity extends AppCompatActivity {
    private EditText lancamentoEt,quantidadeEpisodiosTempEt;
    private TextView numeroTempEt,nomeEt,text_anoT;
    private Button btnLimpar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edita_temporada);
        nomeEt = findViewById(R.id.nomeEt);
        lancamentoEt = findViewById(R.id.lancamentoEt);
        quantidadeEpisodiosTempEt = findViewById(R.id.quantidadeEpisodiosTempEt);
        numeroTempEt = findViewById(R.id.numeroTempEt);
        text_anoT = findViewById(R.id.text_anoEt);

        btnLimpar = findViewById(R.id.btnLimparCampoTemp);

        Bundle numTemp = getIntent().getExtras();

        if(numTemp!=null){
            int temporadaClicada = numTemp.getInt("temporadaClicada");
            String nomeSerie = numTemp.getString("serieClicada");
            numeroTempEt.setText(String.valueOf(temporadaClicada));
            nomeEt.setText(nomeSerie);
        }

    }
    public void editarTemporada(View view){
        /*db = new Banco(this);
        try {

            Temporada temporada = new Temporada();

            temporada.setNumeroSequencial(Integer.parseInt(numeroTempEt.getText().toString()));
            temporada.setAnoLancamento(Integer.parseInt(lancamentoEt.getText().toString()));
            temporada.setQuantidadeEpisodios(Integer.parseInt(quantidadeEpisodiosTempEt.getText().toString()));
            temporada.setNomeSerie(nomeEt.getText().toString());

            Boolean resultado = db.updateTemporada(temporada, nomeEt.getText().toString(),numeroTempEt.getText().toString());

            if (resultado == true) {
                Toast.makeText(FormEditaTemporadaActivity.this, "Temporada editada com sucesso! :)", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), TemporadaActivity.class);
                intent.putExtra("temporadaClicada",temporada.getNumeroSequencial());
                intent.putExtra("serieClicada",nomeEt.getText().toString());
                view.getContext().startActivity(intent);

                db.close();
            }
        }catch (Exception e){
            Toast.makeText(FormEditaTemporadaActivity.this, "Erro :(", Toast.LENGTH_SHORT).show();

        }
    }
    public void limparDadosTemp(View view){
        if(view.getId() == R.id.btnLimparCampoTemp)
        {
            //nomeNovoEt.getText().clear();
            lancamentoEt.getText().clear();
            quantidadeEpisodiosTempEt.getText().clear();
            quantidadeEpisodiosTempEt.getText().clear();

        }*/
    }
}
