package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.Tabuleiro;

public class CasaMagica extends Casa {
    public CasaMagica(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        if(tabuleiro.isUltimo(jogador)) {
            Imprimir.imprimirCasaMagicaUltimo(jogador, this.numeroDaCasa);
        } else {
            tabuleiro.substituirJogador(jogador);
            Imprimir.imprimirCasaMagicaNaoUltimo(jogador, this.numeroDaCasa);
        }
    }
}
