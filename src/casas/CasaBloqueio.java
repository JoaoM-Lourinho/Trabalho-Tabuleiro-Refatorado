package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaBloqueio extends Casa {
    public CasaBloqueio(int numeroDaCasa) {
        super(numeroDaCasa);
    }
    
    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        jogador.bloquear();

        String mensagem = String.format("Jogador %s está na casa %d. Que pena! Você foi bloqueado e perderá a próxima rodada.", jogador.getCor(), this.numeroDaCasa + 1);
        return new ResultadoDaAcao(mensagem, jogador, jogador.getCasaDoTabuleiro());
    }
}