package jogo;

import java.util.ArrayList;
import java.util.Random;
import jogadores.*;

public class Jogo {
    private final Tabuleiro tabuleiro;
    private boolean terminou;
    private final ArrayList<Jogador> jogadores;
    private int resultadoDados;
    private Jogador jogadorVencedor;


        public Jogo(ArrayList<Jogador> jogadores, boolean modoDebug) {
        this.jogadores = jogadores;
        this.tabuleiro = new Tabuleiro(jogadores, modoDebug, new Random());
        }

        private boolean processarBloqueio(Jogador jogadorDaVez) {
            if(jogadorDaVez.isBloqueado()) {
                System.out.println("\nO jogador " + jogadorDaVez.getCor() + " está bloqueado e não joga nesta rodada.");
                ConsoleUtils.imprimirCaractere('-', 50);
                jogadorDaVez.desbloquear();
                return true;
            } 
            return false;
        }

        private void executarTurno(Jogador jogadorDaVez, boolean modoDebug) {
            Imprimir.ImprimirTabuleiro(jogadores);
            Imprimir.imprimirPosicoes(jogadores);
            
            System.out.println("\nÉ a vez do jogador " + jogadorDaVez.getCor() + " (Casa: " + (jogadorDaVez.getCasaDoTabuleiro() + 1) + ")");
            
            if(!modoDebug) {
                ConsoleUtils.avancarRodada();
            }
            
            if (modoDebug) {
                // Pede os valores ao usuário
                int[] dados = ConsoleUtils.lerDadosDebug(jogadorDaVez);
                // Define o resultado manual no jogador e captura o total
                resultadoDados = jogadorDaVez.setResultadoDadosManual(dados[0], dados[1]);
            } else {
                // Rolagem normal
                resultadoDados = jogadorDaVez.rolarDados();
            }
            
            Imprimir.imprimirResultadoDados(resultadoDados, jogadorDaVez.getUltimonumerodado1(), jogadorDaVez.getUltimonumerodado2());
            
            // 1. MOVER JOGADOR (agora retorna o resultado)
            ResultadoDaAcao resultadoAcao = tabuleiro.moverJogador(jogadorDaVez, resultadoDados);
            
            // 2. PROCESSAR O RESULTADO (CAMADA DE APRESENTAÇÃO)
            // A responsabilidade de imprimir é do Jogo, chamando a classe Imprimir
            Imprimir.imprimirResultadoDaAcao(resultadoAcao); 
        }

        private boolean verificarVitoria(Jogador jogadorDaVez) {
            if (jogadorDaVez.getCasaDoTabuleiro() >= 39) {
                this.terminou = true;
                this.jogadorVencedor = jogadorDaVez;
                Imprimir.ImprimirTabuleiro(jogadores); //Imprime o tabuleiro uma ultima vez
                System.out.println(); //Quebra a linha
                ConsoleUtils.imprimirCaractere('-', 35);
                System.out.println("PARABÉNS!!! O JOGADOR '" + jogadorDaVez.getCor().toUpperCase()+ "' VENCEU O JOGO!");
                ConsoleUtils.imprimirCaractere('-', 35);
                System.out.println();//Quebra a linha
                return true;
            }
            return false;
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
                if(processarBloqueio(jogadorDaVez)) {
                    rodada++;//conta a rodada
                    continue;//Pula pra proxima parte do loop
                }
                
                executarTurno(jogadorDaVez, debug);
                
                //Checa se o jogador ganhou
                if (verificarVitoria(jogadorDaVez)) {
                    break; //Sai do loop
                }
                
                //Registra o número de jogadas
                jogadorDaVez.incrementarContadorJogadas();
                
                //Checa se os dados foram iguais
                if (jogadorDaVez.dadosIguais()) {
                    System.out.println("BOA! O Jogador "+ jogadorDaVez.getCor() + " tirou dados iguais. Ele joga novamente.");
                    //Se os dados são iguais, a rodada não é aumentada, forçando o mesmo jogador a jogar de novo
                } else {
                    rodada++; //No resto dos casos (debug ou dados diferentes), so aumenta a rodadae passa a vez
                }
                
                
                
            }while(!this.terminou);
            
            Imprimir.ImprimirResultados(jogadores, jogadorVencedor);
        }
    }