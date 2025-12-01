package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.Tabuleiro;

public class CasaSurpresa extends Casa {
    public CasaSurpresa(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        Imprimir.imprimirCasaSurpresaChegada(jogador, numeroDaCasa);
        
        Class<? extends Jogador> novoTipo = tabuleiro.sortearNovaSorte(jogador);

        Jogador novoJogador = tabuleiro.TrocarTipoJogador(jogador, novoTipo);
        Imprimir.imprimirCasaSurpresaResultado(novoJogador);
    }
}
