package jogo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import jogadores.*;

public class ConsoleUtils {
    public static final Scanner scanner = new Scanner(System.in);

    public static int lerInteiro() {
    while(!scanner.hasNextInt()) {
        System.out.println("Entrada inválida. Digite um número inteiro:");
        scanner.next();
    }
    return scanner.nextInt();
}
    
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
    
    public static ArrayList<Jogador> configurarNovoJogo() {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        boolean configValida;
        int numJogadores;

        do {
            configValida = true;
            jogadores.clear(); 
            imprimirCaractere('!', 50);
            System.out.println("INICIANDO CONFIGURAÇÃO DE JOGADORES...");
            imprimirCaractere('!', 50);

            do {
                System.out.println("Quantos jogadores irão jogar? (mínimo 2, máximo 6)");
                numJogadores = lerInteiro(); 
                if (numJogadores < 2 || numJogadores > 6) {
                    System.out.println("Número inválido. O jogo deve ter entre 2 e 6 jogadores.");
                }
            } while (numJogadores < 2 || numJogadores > 6);

            for (int i = 0; i < numJogadores; i++) {
                String cor;
                int tipoJogador;

                System.out.println("Jogador " + (i + 1) + ":");
                
                
                System.out.println("Digite a cor do seu personagem (ex: vermelho, azul):");
                cor = scanner.next(); 

                do {
                    System.out.println("Qual o tipo de jogador?");
                    System.out.println("1 - Jogador Normal");
                    System.out.println("2 - Jogador Sortudo");
                    System.out.println("3 - Jogador Azarado");
                    tipoJogador = lerInteiro();
                    
                    if (tipoJogador < 1 || tipoJogador > 3) {
                        System.out.println("Opção inválida. Escolha uma opção de 1 a 3.");
                    }

                } while (tipoJogador < 1 || tipoJogador > 3);

                Jogador novoJogador = JogadorFactory.criarJogador(cor, tipoJogador); 
                jogadores.add(novoJogador);
            }

            Set<Class<? extends Jogador>> tiposDeJogadores = new HashSet<>(); 
            for (Jogador jogador : jogadores) {
                tiposDeJogadores.add(jogador.getClass());
            }
                
            if (tiposDeJogadores.size() < 2) {
                imprimirCaractere('!', 50);
                System.out.println("ERRO: O jogo deve ter pelo menos 2 tipos de jogadores diferentes.");
                System.out.println("Por favor, configure os jogadores novamente.");
                imprimirCaractere('!', 50);
                configValida = false; 
            }
        } while (!configValida);
        System.out.println("\nCONFIGURAÇÃO CONCLUÍDA!\n");    
        return jogadores;        
    }

    public static int escolherJogadorParaPunir(ArrayList<Jogador> jogadores, Jogador jogadorAtual) {
        int indiceAtual = jogadores.indexOf(jogadorAtual);
        int jogadorPunido = -1;
        
        System.out.println("Qual jogador você quer punir?");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            if (i != indiceAtual) {
                System.out.println((i + 1) + " - Jogador " + j.getCor() + " (Casa " + j.getCasaDoTabuleiro() + ")");
            }
        }
        
        do {
            System.out.print("Escolha o número do jogador (1 a " + jogadores.size() + "): ");
            try {
                jogadorPunido = lerInteiro() - 1;

                if(jogadorPunido < 0 || jogadorPunido >= jogadores.size() || jogadorPunido == indiceAtual) {
                    System.out.println("Seleção inválida. Escolha o número de um outro jogador válido.");
                }

            } catch(java.util.InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas um número.");
                scanner.nextLine(); 
            }
        } while (jogadorPunido < 0 || jogadorPunido >= jogadores.size() || jogadorPunido == indiceAtual);
        
        return jogadorPunido; 
    }

    public static void avancarRodada() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Pressione ENTER para rolar os dados...");
        
        if (scanner.hasNextLine()) {
            scanner.nextLine(); 
        }
        
        scanner.nextLine();
    }
    public static int[] lerDadosDebug(Jogador jogador) {
        int dado1 = 0;
        int dado2 = 0;
        
        System.out.println("\nMODO DEBUG ATIVO para Jogador " + jogador.getCor());

        // Leitura do Dado 1
        do {
            System.out.print("Digite o valor do primeiro dado (1-6): ");
            try {
                dado1 = lerInteiro();
                if(dado1 < 1 || dado1 > 6) {
                    System.out.println("Erro: Valor inválido. Digite um número entre 1 e 6.");
                }
            } catch(java.util.InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas um número inteiro.");
                scanner.nextLine(); 
            }
        } while(dado1 < 1 || dado1 > 6);

        // Leitura do Dado 2
        do {
            System.out.print("Digite o valor do segundo dado (1-6): ");
            try {
                dado2 = lerInteiro();
                if (dado2 < 1 || dado2 > 6) {
                    System.out.println("Erro: Valor inválido. Digite um número entre 1 e 6.");
                }
            } catch(java.util.InputMismatchException e) {
                System.out.println("Erro: Por favor, digite apenas um número inteiro.");
                scanner.nextLine();
            }
        } while(dado2 < 1 || dado2 > 6);
        
        // Consome a nova linha pendente
        if(scanner.hasNextLine()) {
            scanner.nextLine(); 
        }
        
        return new int[]{dado1, dado2};
    }
}