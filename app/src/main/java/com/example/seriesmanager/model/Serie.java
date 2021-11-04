package com.example.seriesmanager.model;

import java.util.Date;
import java.util.logging.Level;

public class Serie {
    private String nome; //chave primaria
    private Date anoLancamento;
    private String emissora;
    private String genero;


    public Serie(String nome, Date anoLancamento, String emissora, String genero) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.emissora = emissora;
        this.genero = genero;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Date anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
