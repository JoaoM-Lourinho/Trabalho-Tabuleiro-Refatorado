package jogo;

import java.util.ArrayList;
import java.util.Scanner;
import jogadores.*;


public class Main {
    static boolean sair = false;
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        MetodosEstaticos.imprimirTitulo("SEJA BEM VINDO AO TABULEIRO POO!!!");
        
        do{
            MetodosEstaticos.imprimirInicio();
            int alternativa = teclado.nextInt();
            switch (alternativa) {
                case 1 -> {
                    ArrayList<Jogador> jogadores = MetodosEstaticos.configurarNovoJogo();
                    Jogo novojogo = new Jogo(jogadores, false);
                    novojogo.iniciar(false);
                }
                
                case 2 -> {
                    ArrayList<Jogador> jogadores = MetodosEstaticos.configurarNovoJogo();
                    Jogo novojogo = new Jogo(jogadores, true);
                    novojogo.iniciar(true);
                }
                
                case 3 -> {
                    sair = true;
                    break;
                }
                
                default -> System.out.println("Opção inválida. Por favor, digite um número válido:"); 
            }

        }while(!sair);
        teclado.close();
    }
}
