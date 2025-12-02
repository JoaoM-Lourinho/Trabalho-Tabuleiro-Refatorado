package jogo;

import casas.*;
import java.util.ArrayList;
import java.util.Random;
import jogadores.*;

public class Tabuleiro {
    private final ArrayList<Jogador>jogadores;
    private Casa[] casas = new Casa[40];
    private final boolean modoDebug;
    private final Random random;

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public Tabuleiro(ArrayList<Jogador> jogadores, boolean modoDebug, Random random) {
        this.jogadores = jogadores;
        this.modoDebug = modoDebug;
        this.random = random;
        incializarCasas();
    }

    private void incializarCasas() {
        this.casas = TabuleiroConfigurador.criarCasas();
    }

    public ResultadoDaAcao moverJogador(Jogador jogador, int casasParaAndar) {
        int novaCasa = jogador.getCasaDoTabuleiro() + casasParaAndar;
        if (novaCasa >= casas.length) {
            novaCasa = casas.length - 1;
        }
        jogador.setCasadotabuleiro(novaCasa);
        
        return casas[novaCasa].executarAcao(jogador, this);
    }

    public int obterJogadorMaisAtras() {
        Jogador ultimo = jogadores.get(0);
        for(int i = 1; i < jogadores.size(); i++) {
            Jogador atual = jogadores.get(i);
            if(ultimo.getCasaDoTabuleiro() > atual.getCasaDoTabuleiro()) {
                ultimo = atual;
            }
        }
        return jogadores.indexOf(ultimo);
    }

    public boolean isUltimo(Jogador jogador) {
        return jogadores.indexOf(jogador) == obterJogadorMaisAtras();
    }

    public void substituirJogador(Jogador jogadorAtual) {
        int indiceJogadorMaisAtrasado = obterJogadorMaisAtras();
        Jogador jogadorMaisAtrasado = jogadores.get(indiceJogadorMaisAtrasado);
    
        int casaTemporaria = jogadorAtual.getCasaDoTabuleiro();

        jogadorAtual.setCasadotabuleiro(jogadorMaisAtrasado.getCasaDoTabuleiro());
        
        jogadorMaisAtrasado.setCasadotabuleiro(casaTemporaria);
    }

    public Jogador TrocarTipoJogador(Jogador jogadorAntigo, Class<? extends Jogador> novoTipo) {
        
        Jogador jogadorNovo = JogadorFactory.trocarTipoJogador(jogadorAntigo, novoTipo);
        
        if(jogadorNovo != jogadorAntigo) {
            int index = jogadores.indexOf(jogadorAntigo);
            if (index != -1) {
                this.jogadores.set(index, jogadorNovo);
            }
            return jogadorNovo; 
        } 
        return jogadorAntigo;
    }

    private int escolherJogadorParaPunir(Jogador jogadorAtual) {
        return ConsoleUtils.escolherJogadorParaPunir(jogadores, jogadorAtual);
    }

    private void voltarAoInicio(int indiceJogador) {
        Jogador escolhido = jogadores.get(indiceJogador);
        escolhido.setCasadotabuleiro(0);
    }

    public void processarPunicao(Jogador jogadorAtual) {
        int indiceJogadorPunido = escolherJogadorParaPunir(jogadorAtual);
        voltarAoInicio(indiceJogadorPunido);
    }

    public Class<? extends Jogador> sortearNovaSorte(Jogador jogadorAtual) {
        Class<? extends Jogador> novoTipo;

        do { 
            int sorteio = random.nextInt(3);

            novoTipo = switch(sorteio) {
                case 0 -> JogadorNormal.class;
                case 1 -> JogadorSortudo.class;
                case 2 -> JogadorAzarado.class;
                default -> JogadorNormal.class;
            };
            
        } while (novoTipo.equals(jogadorAtual.getClass()));

        return novoTipo;
    }

}
