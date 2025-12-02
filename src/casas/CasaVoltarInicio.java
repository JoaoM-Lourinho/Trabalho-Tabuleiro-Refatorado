package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaVoltarInicio extends Casa {
    public CasaVoltarInicio(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        tabuleiro.processarPunicao(jogador);
        
        String mensagem = String.format("Jogador %s está na casa %d. Você deve punir outro jogador, que voltará ao início!", jogador.getCor(), this.numeroDaCasa);
        return new ResultadoDaAcao(mensagem, jogador, jogador.getCasaDoTabuleiro());
    }
}
