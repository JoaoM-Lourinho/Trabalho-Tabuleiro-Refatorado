package jogadores;

public class JogadorNormal extends Jogador{

    public JogadorNormal (String cor){
            super(cor);
    }

    @Override
    public int rolarDados() {
        this.ultimoNumeroDado1 = dado.nextInt(6) + 1;
        this.ultimoNumeroDado2 = dado.nextInt(6) + 1;
        return ultimoNumeroDado1 + ultimoNumeroDado2;
    }
}
