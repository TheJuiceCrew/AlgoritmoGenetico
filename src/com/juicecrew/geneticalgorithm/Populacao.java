package com.juicecrew.geneticalgorithm;

public class Populacao {
    private Individuo[] Populacao;
    private int tamanho;

    public Populacao(int tamanho) {
        Populacao = new Individuo[tamanho];
        this.tamanho = tamanho;
        inicializar();
    }

    void inicializar(){

        for(int i = 0; i < Populacao.length; i++){
            Populacao[i] = new Individuo();
        }
    }

    public Individuo[] getPopulacao() {
        return Populacao;
    }

    public void setPopulacao(Individuo[] populacao) {
        Populacao = populacao;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
