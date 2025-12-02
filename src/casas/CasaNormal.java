package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaNormal extends Casa {
    public CasaNormal(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        String mensagem = String.format("Jogador %s está na casa %d. Nada acontece.", jogador.getCor(), this.numeroDaCasa);
        return new ResultadoDaAcao(mensagem, jogador, jogador.getCasaDoTabuleiro());
    }
}
