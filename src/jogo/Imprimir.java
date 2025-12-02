package jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jogadores.Jogador;

public class Imprimir {

    public static void imprimirResultadoDaAcao(ResultadoDaAcao resultado) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("CASA ATIVADA: " + resultado.mensagemPrincipal());
        
        if (resultado.jogadorTrocado()) {
            System.out.println("NOVO TIPO DE JOGADOR: " + resultado.jogadorAfetado().getClass().getSimpleName());
        }
        
        System.out.println("Nova Posição do Jogador " + resultado.jogadorAfetado().getCor() + ": " + (resultado.novaPosicao() + 1));
        System.out.println("--------------------------------------------------");
    }
    
    //Imprimir posições dos jogadores ao final de cada rodada
    static void imprimirPosicoes(ArrayList<Jogador> jogadores){
        System.out.println("\n---Posições Atuais---");

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            String cor = j.getCor();
            int casa = (j.getCasaDoTabuleiro()+1);
            System.out.println("O Jogador " + cor + " está na casa " + casa);
        }

        System.out.println("\n---------------------");
    }

    public static void imprimirResultadoDados(int resultadoTotal, int dado1, int dado2) {
        ConsoleUtils.imprimirCaractere('-', 50);
        System.out.println("🎲 DADOS ROLADOS: (" + dado1 + " + " + dado2 + ") = " + resultadoTotal + " casas.");
        ConsoleUtils.imprimirCaractere('-', 50);
    }

    //Imprime o "desenho" do tabuleiro
    static void ImprimirTabuleiro(ArrayList<Jogador> jogadores){
        int numerodecasas = 40;
        int casasporLinha = 10;
        Map<Integer, String> posicoes = new HashMap<>();
        for (Jogador j : jogadores) {
            int casa = (j.getCasaDoTabuleiro()+1);
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
        
        ConsoleUtils.imprimirCaractere('=', 50);
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
        ConsoleUtils.imprimirCaractere('=', 50);
    }

    static void ImprimirResultados(ArrayList<Jogador> jogadores, Jogador jogadorVencedor){
        ConsoleUtils.imprimirCaractere('=', 50);
        System.out.println("FIM DO JOGO");
        ConsoleUtils.imprimirCaractere('=', 50);
        System.out.println("RESULTADOS DA PARTIDA:\n");
        System.out.println("O vencedor foi o jogador " + jogadorVencedor.getCor().toUpperCase() +"\n");
        for (Jogador j : jogadores) {
            System.out.println("Jogador "+ j.getCor() +":\n");
            System.out.println("Jogadas na partida: " + j.getContadorJogadas());
            System.out.println("Posição final: " + (j.getCasaDoTabuleiro()+1));
            System.out.println("\n");
        }
        
    }
}
