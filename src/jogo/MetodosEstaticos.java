package jogo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import jogadores.*;

public class MetodosEstaticos {
    private static final Scanner scanner = new Scanner(System.in);
    static Scanner teclado = new Scanner(System.in);
    
    public static void imprimirCaractere(char caractere, int tamanho){
        for(int i = 0; i<tamanho; i++){
            System.out.print(caractere);
            //printa o caractere por x vezes (x = tamanho)
        }
        System.out.println();
        //quebra a linha
    }
    
    public static void imprimirTitulo(String titulo){
        int tamanho = 50;
        imprimirCaractere('=', tamanho);
        System.out.println(titulo);
        imprimirCaractere('=', tamanho);
    }
    
    public static void imprimirInicio(){
        System.out.println("Escolha o modo que você quer acessar:");
        imprimirCaractere('-', 37);
        System.out.print("1 - Nova Partida\n");
        imprimirCaractere('-', 25);
        System.out.print("2 - Modo Debug\n");
        imprimirCaractere('-', 25);
        System.out.print("3 - Sair do programa\n");
        imprimirCaractere('-', 25);
    }  
    


    
    public static int escolherJogadorParaPunir(ArrayList<Jogador> jogadores, Jogador atual) {
        int indiceAtual = jogadores.indexOf(atual);
        int jogadorPunido;

        // loop infinito que só é interrompido por um 'return' válido
        while (true) {
            System.out.println("Digite o número do jogador escolhido: ");
            for (int i = 0; i < jogadores.size(); i++) {
                if (i == indiceAtual) continue; // pula o jogador atual
                System.out.printf("%d - %s%n", i, jogadores.get(i).getCor());
            }
            try {
                jogadorPunido = scanner.nextInt();

                // verifica se é um número
                if (jogadorPunido >= 0 && jogadorPunido < jogadores.size() && jogadorPunido != indiceAtual) {
                    scanner.nextLine(); // limpa o buffer APENAS se a entrada for 100% válida
                    return jogadorPunido; // se o número for válido, ele retorna e sai do método
                } else {
                    // Se o número for inválido (fora do limite ou o próprio jogador)
                    System.out.println("Seleção inválida. Tente novamente.");
                    scanner.nextLine(); // limpa o buffer para evitar loop infinito
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas um número."); //caso o abençoado (vulgo usuário) coloque uma letra
                scanner.nextLine();
            }
        } // o loop repete automaticamente se a entrada foi inválida (tipo ou valor)
    }

    




    public static ArrayList<Jogador> configurarNovoJogo(){
        int qtdJogadores;
        ArrayList<Jogador> jogadores = new ArrayList<>();
        boolean configValida;
        MetodosEstaticos.imprimirTitulo("CONFIGURANDO NOVA PARTIDA");
        
        //escolhendo a qtd de jogadores
        do{
            jogadores.clear();
            configValida = true;
            do{
                System.out.println("Quantos jogadores serão? (De 2 a 6 jogadores)");
                qtdJogadores = teclado.nextInt();
                teclado.nextLine();//Limpa o buffer
                
                if (qtdJogadores < 2 || qtdJogadores > 6) {
                    System.out.println("Número de jogadores inválido. Tente novamente.");
                }
                
            } while (qtdJogadores < 2 || qtdJogadores > 6);
            
            //Configurando cada jogador
            for (int i = 0; i < qtdJogadores; i++) {
                MetodosEstaticos.imprimirTitulo("CONFIGURANDO JOGADOR "+ (i+1));

                //Cor
                System.out.println("Digite a cor do " +  (i+1) + " jogador: ");
                String cor = teclado.nextLine(); 
                imprimirCaractere('-', 50);

                //Tipo de jogador
                int tipoJogador;
                do {
                    System.out.println("Escolha o tipo: 1-Normal, 2-Sortudo, 3-Azarado");

                    try {
                        tipoJogador = teclado.nextInt();
                    } catch (java.util.InputMismatchException e) { //no caso do usuario digitar uma letra no lugar de um numero
                        System.out.println("Erro: Por favor, digite apenas um número.");
                        tipoJogador = 0; //pra forçar a repetição do loop por um valor inválido
                    } finally {
                        teclado.nextLine(); // limpa o buffer em AMBOS os casos (sucesso ou erro)
                    }

                    if (tipoJogador < 1 || tipoJogador > 3) {
                        System.out.println("Tipo inválido. Escolha uma opção de 1 a 3.");
                    }

                } while (tipoJogador < 1 || tipoJogador > 3);

                //Adicionando o Jogador
                Jogador novoJogador = null;
                switch (tipoJogador) {
                    case 1 -> novoJogador = new JogadorNormal(cor);
                    case 2 -> novoJogador = new JogadorSortudo(cor);
                    case 3 -> novoJogador = new JogadorAzarado(cor);
                }
                jogadores.add(novoJogador);
            }

            Set<Class<? extends Jogador>> tiposDeJogadores = new HashSet<>(); // Conta quantos tipos diferentes de jogadores foram usado
                for (Jogador jogador : jogadores) {
                    tiposDeJogadores.add(jogador.getClass());
                }
                    
                // Verifica se a quantidade de tipos únicos é menor que 2
                if (tiposDeJogadores.size() < 2) {
                    imprimirCaractere('!', 50);
                    System.out.println("ERRO: O jogo deve ter pelo menos 2 tipos de jogadores diferentes.");
                    System.out.println("Por favor, configure os jogadores novamente.");
                    imprimirCaractere('!', 50);
                    configValida = false; // Força a repetição do loop
                }
        }while (!configValida);
        System.out.println("\nCONFIGURAÇÃO CONCLUÍDA!\n");    
        return jogadores;        
    }
}
