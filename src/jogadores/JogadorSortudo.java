package jogadores;

public class JogadorSortudo extends Jogador{
    
    public JogadorSortudo (String cor){
        super(cor);
    }

    @Override
    public int rolarDados(){
        int soma = 0;
        do{
            this.ultimoNumeroDado1 = dado.nextInt(6) + 1;
            this.ultimoNumeroDado2 = dado.nextInt(6) + 1;

            soma = ultimoNumeroDado1 + ultimoNumeroDado2;
        } while (soma < 7);
    
        return soma;
    }
}
