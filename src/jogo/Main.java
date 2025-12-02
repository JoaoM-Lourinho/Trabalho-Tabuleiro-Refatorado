package jogo;

import java.util.ArrayList;
import jogadores.*;


public class Main {
    static boolean sair = false;
    public static void main(String[] args) {
        ConsoleUtils.imprimirTitulo("SEJA BEM VINDO AO TABULEIRO POO!!!");
        
        do{
            ConsoleUtils.imprimirInicio();
            int alternativa = ConsoleUtils.lerInteiro();
            switch (alternativa) {
                case 1 -> {
                    ArrayList<Jogador> jogadores = ConsoleUtils.configurarNovoJogo();
                    Jogo novojogo = new Jogo(jogadores, false);
                    novojogo.iniciar(false);
                }
                
                case 2 -> {
                    ArrayList<Jogador> jogadores = ConsoleUtils.configurarNovoJogo();
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
    }
}
