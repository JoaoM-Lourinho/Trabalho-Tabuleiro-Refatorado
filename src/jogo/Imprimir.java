package jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jogadores.Jogador;

public class Imprimir {

    
    //Imprimir mensagens das casas que o jogador atual cair
    public static void imprimirCasaBloqueio(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + ". Que pena! Você foi bloqueado e perderá a próxima rodada.\n");
    }

    public static void imprimirCasaDaSorteNaoAzarado(Jogador jogador) {
        System.out.println("Sorte grande! Jogador " + jogador.getCor() + " está na casa da sorte e avança 3 casas! Ele vai para a casa " + jogador.getCasadotabuleiro()+1 + "\n");
    }

    public static void imprimirCasaDaSorteAzarado(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " tá na casa " + numeroDaCasa + " e caiu na casa da sorte, mas por ser azarado, nada acontece.\n");
    }

    public static void imprimirCasaMagicaUltimo(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + ". Ela é uma casa mágica e o jogador deve trocar com o último! Ops... ele já é o último. Nada acontece.\n");
    }

    public static void imprimirCasaMagicaNaoUltimo(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + ". Ela é uma casa mágica e o jogador deve trocar com o último! Agora ele está na casa: " + (jogador.getCasadotabuleiro()+1) + "\n");
    }

    public static void imprimirCasaNormal(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + ". Ela é uma casa normal, nada acontece.\n");
    }

    public static void imprimirCasaVoltarAoInicio(Jogador jogador, int numeroDaCasa) {
        System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + " e agora possui o poder de prejudicar muito alguém!\n");
    }

    public static void imprimirJogadorVoltouAoInicio(Jogador jogador) {
        System.out.println("Punição efetuada! Jogador " + jogador.getCor() + " foi escolhido e agora voltou ao início.\n");
    }

    public static void imprimirCasaSurpresaChegada(Jogador jogador, int numeroDaCasa) {
    System.out.println("Jogador " + jogador.getCor() + " está na casa " + numeroDaCasa + ". Surpresa! Sua sorte será reescrita!\n");
    }

    public static void imprimirCasaSurpresaResultado(Jogador novoJogador) {
    String novoTipo = novoJogador.getClass().getSimpleName(); 
    
    if (novoTipo.contains("Jogador")) {
        novoTipo = novoTipo.replace("Jogador", "");
    }
    
    System.out.println("\nO jogador " + novoJogador.getCor() + " tirou uma carta e se tornou um Jogador " + novoTipo + "!\n");
    }

    //Imprimir posições dos jogadores ao final de cada rodada
    static void imprimirPosicoes(ArrayList<Jogador> jogadores){
        System.out.println("\n---Posições Atuais---");

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            String cor = j.getCor();
            int casa = (j.getCasadotabuleiro()+1);
            System.out.println("O Jogador " + cor + " está na casa " + casa);
        }

        System.out.println("\n---------------------");
    }


    //Imprime o "desenho" do tabuleiro
    static void ImprimirTabuleiro(ArrayList<Jogador> jogadores){
        int numerodecasas = 40;
        int casasporLinha = 10;
        Map<Integer, String> posicoes = new HashMap<>();
        for (Jogador j : jogadores) {
            int casa = (j.getCasadotabuleiro()+1);
            char inicialDaCor = j.getCor().toUpperCase().charAt(0);//pega a cor, deixa em maiúsculo e pega a letra do indice 0
            
            //Construindo o texto (ex: "[A]", "[V]")
            
            String jogadoresNaCasa = posicoes.getOrDefault(casa, "["); 
            /*  o getOrDefault funciona para quando o jogador para na casa pela primeira vez não dar erro 
            (em vez de retornar null, ele retorna "["). Caso ja tenha algum jogador, ele so retorna a casa que
            esse outro está, retornando "[A", por exemplo, ao invés de "[", caso ainda fosse a primeira pessoa lá.*/
            
            jogadoresNaCasa += inicialDaCor; 
            //Junta os char's de todos os jogadores que estiverem na casa
            
            posicoes.put(casa, jogadoresNaCasa); 
            //para a casa X, escreve os N jogadores que estiverem nela
            
            posicoes.replaceAll((house, texto) -> texto + "]");
            //Bota "]" no final de cada casa com jogador. Escrevi "house" pq se escrevesse "casa", iria dar um conflito de variaveis
        }
        
        MetodosEstaticos.imprimirCaractere('=', 50);
        for (int i = 0; i < numerodecasas; i++) {
            int numeroDaCasa = i + 1;
            String conteudoDaCasa;
            
            if (posicoes.containsKey(numeroDaCasa)) {
                conteudoDaCasa = posicoes.get(numeroDaCasa);
                // Se o mapa contém esta casa é pq tem jogadores nela. Aí, ele coloca no conteudo da casa o que tem no hashmap
            } 
            else{
                conteudoDaCasa = String.valueOf(numeroDaCasa);
                //Se não tem jogadores, ele pega so o numero da casa
            }
            
            System.out.printf("%-5s", conteudoDaCasa);
            //Esse syso printa o coteudo da casa mas alinhando com o resto, fazendo com que cada casa ocupe 5 caracteres.
            
            if (numeroDaCasa % casasporLinha == 0) {
                System.out.println();
            }
            //quebra a linha qnd atinge o limite de casas por linha
        }
        MetodosEstaticos.imprimirCaractere('=', 50);
    }

    static void ImprimirResultados(ArrayList<Jogador> jogadores, Jogador jogadorVencedor){
        MetodosEstaticos.imprimirCaractere('=', 50);
        System.out.println("FIM DO JOGO");
        MetodosEstaticos.imprimirCaractere('=', 50);
        System.out.println("RESULTADOS DA PARTIDA:\n");
        System.out.println("O vencedor foi o jogador " + jogadorVencedor.getCor().toUpperCase() +"\n");
        for (Jogador j : jogadores) {
            System.out.println("Jogador "+ j.getCor() +":\n");
            System.out.println("Jogadas na partida: " + j.getContadorJogadas());
            System.out.println("Posição final: " + (j.getCasadotabuleiro()+1));
            System.out.println("\n");
        }
        
    }

}
