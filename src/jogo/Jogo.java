package jogo;

import java.util.ArrayList;
import java.util.Random;
import jogadores.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private boolean terminou;
    private ArrayList<Jogador> jogadores;
    private int resultadoDados;
    private Jogador jogadorVencedor;


        public Jogo(ArrayList<Jogador> jogadores, boolean modoDebug) {
        this.jogadores = jogadores;
        this.tabuleiro = new Tabuleiro(jogadores, modoDebug, new Random());
        }


        public void iniciar(boolean debug){
            System.out.println("------------O JOGO VAI COMEÇAR!!!------------");
            int rodada = 0;
            this.terminou = false;
            do {

                
                //Pegar o jogador da vez
                int jogadordavez = rodada % jogadores.size();//Pega o modulo da divisao dos jogadores pelas rodadas, que sempre vai dar o numero do prox jogador
                Jogador jogadorDaVez = jogadores.get(jogadordavez);
                
                
                //Checando se está bloqueado
                if(jogadorDaVez.isBloqueado()){
                    System.out.println("\nO jogador " + jogadorDaVez.getCor() + " está bloqueado e não joga nesta rodada.");
                    MetodosEstaticos.imprimirCaractere('-', 50);
                    jogadorDaVez.desbloquear();
                    rodada++;//conta a rodada
                    continue;//Pula pra proxima parte do loop
                }
                
                //Imprime, no terminal, o tabuleiro, a posição de cada um dos jogadores e suas respectivas cores
                Imprimir.ImprimirTabuleiro(jogadores);
                Imprimir.imprimirPosicoes(jogadores);
                
                
                //Lógica das jogadas
                System.out.println("\nÉ a vez do jogador " + jogadorDaVez.getCor() + " (Casa: " + (jogadorDaVez.getCasadotabuleiro()+1) + ")\n");
                int casaEscolhida = 10; //valor arbitrario diferente de 0
                int casasParaAndar = 0;
                
                if (debug){ //Modo Debug
                    do{
                        System.out.println("Digite para qual casa o jogador " + jogadorDaVez.getCor() + " deve se mover: (Entre 1 e 40)");
                        try {
                            casaEscolhida = MetodosEstaticos.teclado.nextInt();
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("Digite um número válido, seu gaiato\n");
                            casaEscolhida = 0; // força o loop a repetir 
                        } finally {
                            MetodosEstaticos.teclado.nextLine();
                        }
                    }while(casaEscolhida> 40 || casaEscolhida<1);
                    
                    casasParaAndar = casaEscolhida - jogadorDaVez.getCasadotabuleiro();
                    tabuleiro.moverJogador(jogadorDaVez, (casasParaAndar-1));
                    jogadorDaVez.registrarJogada();
                    System.out.println(); //quebra a linha
                    //Escolhe a casa e muda a posição do jogador atual para ela
                }
                
                else{ //Modo Normal
                    System.out.print("Pressione ENTER para rolar os dados...\n");
                    MetodosEstaticos.teclado.nextLine(); // Pede o comando do user pra continuar
                    
                    //Rolando os dados e movendo o jogador
                    if(!debug){
                        resultadoDados = jogadorDaVez.rolarDados();
                        jogadorDaVez.registrarJogada();
                        MetodosEstaticos.imprimirCaractere('-', 50);
                        System.out.println("\nO resultado dos dados foi " + resultadoDados + "!\n");
                        tabuleiro.moverJogador(jogadorDaVez, resultadoDados);
                        
                    }
                }
                
                
                //Checa se o jogador ganhou
                if (jogadorDaVez.getCasadotabuleiro() >= 39) {
                    this.terminou = true;
                    this.jogadorVencedor = jogadorDaVez;
                    Imprimir.ImprimirTabuleiro(jogadores); //Imprime o tabuleiro uma ultima vez
                    System.out.println(); //Quebra a linha
                    MetodosEstaticos.imprimirCaractere('-', 35);
                    System.out.println("PARABÉNS!!! O JOGADOR '" + jogadorDaVez.getCor().toUpperCase()+ "' VENCEU O JOGO!");
                    MetodosEstaticos.imprimirCaractere('-', 35);
                    System.out.println();//Quebra a linha
                    break; //Sai do loop
                }
                
                
                //Checa se os dados foram iguais
                if (jogadorDaVez.dadosIguais() && !debug) {
                    System.out.println("BOA! O Jogador "+ jogadorDaVez.getCor() + " tirou dados iguais. Ele joga novamente.");
                    //Se os dados são iguais, a rodada não é aumentada, forçando o mesmo jogador a jogar de novo
                } else {
                    rodada++; //No resto dos casos (debug ou dados diferentes), so aumenta a rodadae passa a vez
                }
                
                
                
            }while(!this.terminou);
            
            Imprimir.ImprimirResultados(jogadores, jogadorVencedor);
        }
    }
    