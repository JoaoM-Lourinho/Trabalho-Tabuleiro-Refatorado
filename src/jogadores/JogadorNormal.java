package jogadores;

public class JogadorNormal extends Jogador{

    public JogadorNormal (String cor){
            super(cor);
    }

    @Override
    public int rolarDados() {
        this.ultimonumerodado1 = dado.nextInt(6) + 1;
        this.ultimonumerodado2 = dado.nextInt(6) + 1;
        return ultimonumerodado1 + ultimonumerodado2;
    }

    @Override
    public boolean isNormal() {
        return true;
    }
}
