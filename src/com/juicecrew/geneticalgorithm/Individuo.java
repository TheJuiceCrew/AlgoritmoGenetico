package com.juicecrew.geneticalgorithm;
import java.util.Random;





public class Individuo {


    public static final int MAX_CROMOSS   = 50;
    public static final int MIN_CROMOSS   = 0;
    public static final int MAX_PESO      = 500;
    public static final int PENALIZACAO   = 20;
    public static final int[] PESO        = {2, 4, 5, 8, 12};
    public static final int[] VALOR       = {3, 6, 10, 18, 26};

    private int[] Cromossomo = new int[5];
    private int fitness;


    public Individuo() {
        initialize();
        calculateFitness();
    }

    void initialize(){
        Random rand = new Random();
        for(int i = 0; i < Cromossomo.length; i++ ){
            Cromossomo[i] = rand.nextInt((MAX_CROMOSS - MIN_CROMOSS) + 1) + MIN_CROMOSS;
        }
    }

    void calculateFitness(){

        int pesoCromossomo = 0;
        int valorCromossomo = 0;

        //Calculo do Produto Vetorial
        for(int i = 0; i < Cromossomo.length; i++){

            pesoCromossomo += Cromossomo[i] * PESO[i];
            valorCromossomo += Cromossomo[i] * VALOR[i];

        }

        if(pesoCromossomo < MAX_PESO){
            this.fitness = valorCromossomo;
        }

        else{
            this.fitness = valorCromossomo - ((pesoCromossomo - MAX_PESO) * PENALIZACAO);
        }
    }

    public int[] getCromossomo() {
        return Cromossomo;
    }

    public void setCromossomo(int[] cromossomo) {
        Cromossomo = cromossomo;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
}
