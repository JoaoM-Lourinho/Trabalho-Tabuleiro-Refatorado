package casas;

import jogadores.*;
import jogo.Imprimir;
import jogo.MetodosEstaticos;
import jogo.Tabuleiro;

public class CasaVoltarInicio extends Casa {
    public CasaVoltarInicio(int numeroDaCasa) {
        super(numeroDaCasa);
    }

    @Override
    public void executarAcao(Jogador jogador, Tabuleiro tabuleiro) {
        Imprimir.imprimirCasaVoltarAoInicio(jogador, numeroDaCasa);
        int indiceEscolhido;

        indiceEscolhido = MetodosEstaticos.escolherJogadorParaPunir(tabuleiro.getJogadores(), jogador);
        tabuleiro.voltarAoInicio(indiceEscolhido);
        Jogador jogadorAlvo = tabuleiro.getJogadores().get(indiceEscolhido);
        Imprimir.imprimirJogadorVoltouAoInicio(jogadorAlvo);
        
    }
}
