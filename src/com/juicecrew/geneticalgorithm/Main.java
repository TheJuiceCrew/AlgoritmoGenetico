package com.juicecrew.geneticalgorithm;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Random;

public class Main {
    static Random rand = new Random();
    public static void main(String[] args) {

        int geracoes = 1000000;
        Populacao pop = new Populacao(10 );

        //Printando a população só pra debug mesmo
        for(int j = 0; j< pop.getPopulacao().length; j++){
            System.out.println(Integer.toString(pop.getPopulacao()[j].getFitness()));
        }

        System.out.println("________________");

        //LOOP PRINCIPAL
        for(int i = 0; i < geracoes; i++){

            // Sorting the population array in descending order fitness of each individual
            //a.k.a: RANKING
            Arrays.sort(pop.getPopulacao(), Comparator.comparing(Individuo::getFitness).reversed());

            //Seleção dos pais para gerar os filhos
            Populacao popPais =  torneio(pop, 10, 2);

            //Cruzamento dos pais para geração dos filhos
            Populacao popFilhos = pontoCorte(popPais);

            for(int j = 0; j< popFilhos.getPopulacao().length; j++){
                System.out.println(Integer.toString(popFilhos.getPopulacao()[j].getFitness()));
            }

        }
    }


    public static Populacao torneio(Populacao pop, int taxaCruz, int numTorneios){

        Individuo selecao = pop.getPopulacao()[pop.getPopulacao().length - 1]; // Inicia seleção com o individuo com menor fitness

        //Tamanho da nova população de pais, é a porcentagem passada em taxaCruz
        int tamanho = (int)Math.ceil( pop.getTamanho() * (taxaCruz / 100.0));


        //Garantir que sempre seja um numero par (pois cada filho tem dois pais)
        if((tamanho % 2) != 0){
            // Se tamanho for um número impar, somamos mais um para virar um numero par
            tamanho = tamanho + 1;
        }

        Populacao popPais = new Populacao(tamanho); //Nova população de pais com o tamanho indicado

        for(int i = 0; i < tamanho; i++){
            // Inicializa o maiorFitness com o menor fitness possivel da população
            int maiorFitness = pop.getPopulacao()[pop.getPopulacao().length - 1].getFitness(); //Fitness do ultimo elemento do vetor população, ou seja, menor fitness

            for(int n = 0; n < numTorneios; n++){
                int candidatoIndex = rand.nextInt(pop.getTamanho()); //Indice do candidato aleatório
                Individuo candidato = pop.getPopulacao()[candidatoIndex];  //Candidato aleatório com indice acima escolhido

                if(candidato.getFitness() > maiorFitness){ //Se ele tiver maior fitness
                    maiorFitness = candidato.getFitness(); //Sua fitness vira a maior
                    selecao = candidato; //Ele vira um possivel candidato selecionado por torneio
                }

            }

            popPais.getPopulacao()[i] = selecao; //Aduciona o ultimo dos numTorneios candidados que foi escolhido ao vetor de pais
        }

        return popPais; //Retorna a nova população de pais
    }



    public static Populacao pontoCorte(Populacao popPais){
        Populacao popFilhos = popPais;

        for(int i = 0; i < popFilhos.getPopulacao().length; i += 2){
            int ponto = rand.nextInt(popFilhos.getTamanho()); // Não precisa subtrair 1 do valor pq esse valor ja vai
                                                              // no máximo até popFilhos.getTamanho - 1
                                                              // a função rand.nextInt é exclusiva para o valor máximo

            Individuo filho1 = cruzar(popPais.getPopulacao()[i], popPais.getPopulacao()[i + 1], ponto); // Calcula o primeiro filho
            Individuo filho2 = cruzar(popPais.getPopulacao()[i + 1], popPais.getPopulacao()[i], ponto); // Calcula o segundo filho

            popFilhos.getPopulacao()[i] = filho1; // Salva o primeiro filho
            popFilhos.getPopulacao()[i].calculateFitness();// Calcula a fitness dele
            popFilhos.getPopulacao()[i+1] = filho2; // Salva o segundo filho
            popFilhos.getPopulacao()[i+1].calculateFitness(); // Calcula a fitness dele

        }


        return popFilhos;
    }

    public static Individuo cruzar (Individuo pai1, Individuo pai2, int ponto){
        Individuo filho = pai2;
        for(int i = 0; i < ponto; i++){
            filho.getCromossomo()[i] = pai1.getCromossomo()[i];
        }


        return filho;
    }

    public static Populacao mutacao (Populacao pop, int taxaDeMutacao){
        int nMutacao = (int)Math.ceil( pop.getTamanho() * (taxaDeMutacao / 100.00));

        if (nMutacao < 1) {
            nMutacao = 1;
        }

        for (int i = 1; i < nMutacao; i++) {
            int theChosenCromo = 1 + (rand.nextInt(pop.getTamanho()));
            int theChosenGene = rand.nextInt(5);





        }

    }

}
