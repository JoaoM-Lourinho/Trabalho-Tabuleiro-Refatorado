package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public abstract class Casa {
    protected int numeroDaCasa;

    protected Casa(int numeroDaCasa) {
        this.numeroDaCasa = numeroDaCasa;
    }

    public int getNumeroDaCasa() {
        return numeroDaCasa;
    }

    public abstract ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro);
}