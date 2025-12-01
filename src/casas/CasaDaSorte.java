package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.Tabuleiro;

public class CasaDaSorte extends Casa {
    public CasaDaSorte(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
         if (!jogador.isAzarado()) {
            int casaAtual = jogador.getCasadotabuleiro();
            int novaCasa = casaAtual + 3;
            if (novaCasa >= 39) { // se a nova casa for maior ou igual a última casa (39)
            novaCasa = 39; // trava o jogador na última casa
            }
            jogador.setCasadotabuleiro(novaCasa);
            Imprimir.imprimirCasaDaSorteNaoAzarado(jogador);
        } else {
            Imprimir.imprimirCasaDaSorteAzarado(jogador, this.numeroDaCasa);
        }
    }
}
