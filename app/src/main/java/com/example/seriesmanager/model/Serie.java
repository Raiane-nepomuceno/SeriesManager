package com.example.seriesmanager.model;


public class Serie {
    private String nome; //chave primaria
    private int anoLancamento;
    private String emissora;
    private String genero;


    public Serie(String nome, int anoLancamento, String emissora, String genero) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.emissora = emissora;
        this.genero = genero;
    }


    public Serie() {
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
