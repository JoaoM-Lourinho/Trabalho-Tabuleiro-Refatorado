package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.Tabuleiro;

public class CasaNormal extends Casa {
    public CasaNormal(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        Imprimir.imprimirCasaNormal(jogador, numeroDaCasa);
    }
}
