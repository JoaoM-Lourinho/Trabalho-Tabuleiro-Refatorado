package jogo;

import jogadores.*;

public class JogadorFactory {
    private JogadorFactory(){}
    
    public static Jogador criarJogador(String cor, Class<? extends Jogador> tipo) {
        if (tipo.equals(JogadorSortudo.class)) {
            return new JogadorSortudo(cor);
        } else if (tipo.equals(JogadorAzarado.class)) {
            return new JogadorAzarado(cor);
        } else if (tipo.equals(JogadorNormal.class)) {
            return new JogadorNormal(cor);
        }
        return null;
    }
    
    public static Jogador criarJogador(String cor, int codigoTipo) {
        return switch (codigoTipo) {
            case 1 -> new JogadorNormal(cor);
            case 2 -> new JogadorSortudo(cor);
            case 3 -> new JogadorAzarado(cor);
            default -> null; // Tipo inválido
        };
    }

    public static Jogador trocarTipoJogador(Jogador jogadorAntigo, Class<? extends Jogador> novoTipo) {
        
        Jogador jogadorNovo = criarJogador(jogadorAntigo.getCor(), novoTipo);
        
        if (jogadorNovo != null) {
            jogadorNovo.copiarEstado(jogadorAntigo);
            return jogadorNovo;
        }
        return jogadorAntigo;
    }
}