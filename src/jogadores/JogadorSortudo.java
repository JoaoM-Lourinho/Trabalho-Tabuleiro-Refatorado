package jogadores;

public class JogadorSortudo extends Jogador{
    
    public JogadorSortudo (String cor){
        super(cor);
    }

    @Override
    public int rolarDados(){
        int soma = 0;
        do{
            this.ultimonumerodado1 = dado.nextInt(6) + 1;
            this.ultimonumerodado2 = dado.nextInt(6) + 1;

            soma = ultimonumerodado1 + ultimonumerodado2;
        } while (soma < 7);
    
        return soma;
    }

    @Override
    public boolean isSortudo() {
        return true;
    }
}
