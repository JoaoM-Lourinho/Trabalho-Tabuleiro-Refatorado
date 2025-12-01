package jogo;

import casas.*;
import java.util.ArrayList;
import java.util.Random;
import jogadores.*;

public class Tabuleiro {
    private ArrayList<Jogador>jogadores;
    private Casa[] casas = new Casa[40];
    private boolean modoDebug;
    private Random random;

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
        for (int i = 0; i < casas.length; i++) {
            casas[i] = new CasaNormal(i + 1);
        }

        casas[9] = new CasaBloqueio(10);
        casas[24] = new CasaBloqueio(25);
        casas[37] = new CasaBloqueio(38);
        casas[12] = new CasaSurpresa(13);
        casas[4] = new CasaDaSorte(5);
        casas[14] = new CasaDaSorte(15);
        casas[29] = new CasaDaSorte(30);
        casas[16] = new CasaVoltarInicio(17);
        casas[26] = new CasaVoltarInicio(27);
        casas[19] = new CasaMagica(20);
        casas[34] = new CasaMagica(35);
    }

    public void moverJogador(Jogador jogador, int casasParaAndar) {
    int novaCasa = jogador.getCasadotabuleiro() + casasParaAndar;
    if (novaCasa >= casas.length) {
        novaCasa = casas.length - 1;
    }
    jogador.setCasadotabuleiro(novaCasa);
    casas[novaCasa].executarAcao(jogador, this);
    }

    public int obterJogadorMaisAtras() {
        Jogador ultimo = jogadores.get(0);
        for(int i = 1; i < jogadores.size(); i++) {
            Jogador atual = jogadores.get(i);
            if(ultimo.getCasadotabuleiro() > atual.getCasadotabuleiro()) {
                ultimo = atual;
            }
        }
        return jogadores.indexOf(ultimo);
    }

    public boolean isUltimo(Jogador jogador) {
        return jogadores.indexOf(jogador) == obterJogadorMaisAtras();
    }

    public void substituirJogador(Jogador jogador) {
        int indiceUltimo = obterJogadorMaisAtras();    
        int casaTemp = jogador.getCasadotabuleiro();
        int ultimaCasa = jogadores.get(indiceUltimo).getCasadotabuleiro();
        
        jogador.setCasadotabuleiro(ultimaCasa);
        jogadores.get(indiceUltimo).setCasadotabuleiro(casaTemp);  
    }

    public void voltarAoInicio(int indiceJogadores) {
        Jogador escolhido = jogadores.get(indiceJogadores);
        escolhido.setCasadotabuleiro(0);
    }

    public Jogador TrocarTipoJogador(Jogador antigo, Class<? extends Jogador> novoTipo) {
        String cor = antigo.getCor();
        int casa = antigo.getCasadotabuleiro();
        boolean bloqueado = antigo.isBloqueado();
        int indice = jogadores.indexOf(antigo);
        int jogadasAnteriores = antigo.getContadorJogadas();
        
        Jogador novo;

        if (novoTipo.equals(JogadorSortudo.class)) {
        novo = new JogadorSortudo(cor);
        } else if (novoTipo.equals(JogadorAzarado.class)) {
        novo = new JogadorAzarado(cor);
        } else if (novoTipo.equals(JogadorNormal.class)) {
        novo = new JogadorNormal(cor);
        } else {
        return antigo; 
        }

        novo.setCasadotabuleiro(casa);
        if (bloqueado) {
            novo.bloquear();
        }

        novo.setNumeroJogadas(jogadasAnteriores);
        jogadores.set(indice, novo);

        return novo;
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
