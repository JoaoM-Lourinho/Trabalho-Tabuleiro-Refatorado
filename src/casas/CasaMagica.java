package casas;

import jogadores.*;
import jogo.ResultadoDaAcao;
import jogo.Tabuleiro;

public class CasaMagica extends Casa {
    public CasaMagica(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public ResultadoDaAcao executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        String mensagem;
        
        if(tabuleiro.isUltimo(jogador)) {
            mensagem = String.format("Jogador %s está na casa %d. Ela é uma casa mágica e o jogador deve trocar com o último! Ops... ele já é o último. Nada acontece.", jogador.getCor(), this.numeroDaCasa);
        } else {
            tabuleiro.substituirJogador(jogador);
            int indiceUltimo = tabuleiro.obterJogadorMaisAtras();
            Jogador jogadorQueEstavaUltimo = tabuleiro.getJogadores().get(indiceUltimo);

            mensagem = String.format("Jogador %s está na casa %d. Você trocou de posição com o jogador %s. Ambos mudaram de casa!", 
                                     jogador.getCor(), 
                                     this.numeroDaCasa, 
                                     jogadorQueEstavaUltimo.getCor());
        }

        return new ResultadoDaAcao(mensagem, jogador, jogador.getCasaDoTabuleiro(), false);
    }    
}
