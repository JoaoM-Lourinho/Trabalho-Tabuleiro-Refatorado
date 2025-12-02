package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaSurpresa extends Casa {
    public CasaSurpresa(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        
        Class<? extends Jogador> novoTipo = tabuleiro.sortearNovaSorte(jogador);

        Jogador novoJogador = tabuleiro.TrocarTipoJogador(jogador, novoTipo);
        
        String mensagem = String.format("Jogador %s está na casa %d. SURPRESA! Você teve seu tipo alterado para %s!", jogador.getCor(), this.numeroDaCasa, novoJogador.getClass().getSimpleName());
        
        return new ResultadoDaAcao(mensagem, novoJogador, novoJogador.getCasaDoTabuleiro(), true);
    }
}
