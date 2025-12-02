package jogo;

import jogadores.Jogador;

public record ResultadoDaAcao(
    String mensagemPrincipal,
    Jogador jogadorAfetado,
    int novaPosicao,
    boolean jogadorTrocado
){
    public ResultadoDaAcao(String mensagemPrincipal, Jogador jogadorAfetado, int novaPosicao) {
        this(mensagemPrincipal, jogadorAfetado, novaPosicao, false);
    }
}