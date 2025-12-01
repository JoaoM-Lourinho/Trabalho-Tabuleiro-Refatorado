package casas;

import jogadores.*;
import jogo.Tabuleiro;

public abstract class Casa {
    protected int numeroDaCasa;

    protected Casa(int numeroDaCasa) {
        this.numeroDaCasa = numeroDaCasa;
    }

    public int getNumeroDaCasa() {
        return numeroDaCasa;
    }

    public abstract void executarAcao(Jogador jogador, Tabuleiro tabuleiro);
}