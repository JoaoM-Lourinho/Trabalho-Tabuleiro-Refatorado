package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaDaSorte extends Casa {
    public CasaDaSorte(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        int novaCasa = jogador.avancarCasaDaSorte(); 
        
        String mensagem;
        if (jogador instanceof JogadorAzarado) {
            mensagem = String.format("Jogador %s tá na casa %d. Caiu na Casa da Sorte, mas por ser azarado, nada acontece.", jogador.getCor(), this.numeroDaCasa);
        } else {
            mensagem = String.format("Sorte grande! Jogador %s avanca 3 casas! Ele vai para a casa %d", jogador.getCor(), jogador.getCasaDoTabuleiro() + 1);
        }

        return new ResultadoDaAcao(mensagem, jogador, novaCasa);
    }
}
