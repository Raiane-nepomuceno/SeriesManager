package com.example.seriesmanager.model;


public class Temporada {
    /*Cada
    temporada deve ter um número sequencial, ano de lançamento, e quantidade de episódios.*/
    private int numeroSequencial,anoLancamento,quantidadeEpisodios;

    public Temporada(int numeroSequencial, int anoLancamento, int quantidadeEpisodios, String nomeSerie) {
        this.numeroSequencial = numeroSequencial;
        this.anoLancamento = anoLancamento;
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    public Temporada() {

    }

    public int getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(int numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios) {
        this.quantidadeEpisodios = quantidadeEpisodios;
    }
}
