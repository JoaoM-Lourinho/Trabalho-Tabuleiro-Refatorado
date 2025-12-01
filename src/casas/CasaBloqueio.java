package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.Tabuleiro;

public class CasaBloqueio extends Casa {
    public CasaBloqueio(int numeroDaCasa) {
        super(numeroDaCasa);
    }
    
    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        Imprimir.imprimirCasaBloqueio(jogador, this.numeroDaCasa);
        jogador.bloquear();
    }
}