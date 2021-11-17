package com.example.seriesmanager.model;

public class Episodio {
    /*Cada episódio deve um número
    sequencial, um nome, um tempo de duração (em minutos), uma flag (para indicar se o
    episódio já foi assistido ou não). Clicando num episódio específico o usuário poderá gerenciá-lo
    (editá-lo ou marca-lo como já assistido*/


    private int numeroSequencial;
    private String nome;
    private int tempoDuracao; //em minutos
    private boolean flag; //flag de Assistido
    private int numeroSequencialTemp;

    public Episodio(int numeroSequencial, String nome, int tempoDuracao, boolean flag, int numeroSequencialTemp) {
        this.numeroSequencial = numeroSequencial;
        this.nome = nome;
        this.tempoDuracao = tempoDuracao;
        this.flag = flag;
        this.numeroSequencialTemp = numeroSequencialTemp;
    }

    public Episodio() {
    }

    public int getNumeroSequencialTemp() {
        return numeroSequencialTemp;
    }

    public void setNumeroSequencialTemp(int numeroSequencialTemp) {
        this.numeroSequencialTemp = numeroSequencialTemp;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNumeroSequencial() {
        return numeroSequencial;
    }

    public void setNumeroSequencial(int numeroSequencial) {
        this.numeroSequencial = numeroSequencial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(int tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

}
