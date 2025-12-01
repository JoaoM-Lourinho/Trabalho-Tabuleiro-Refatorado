package jogadores;

public class JogadorAzarado extends Jogador{
    
    public JogadorAzarado(String cor){
        super(cor);
    }

@Override
    public int rolarDados() {
        int soma = 0;
        do {
            this.ultimonumerodado1 = dado.nextInt(6) + 1;
            this.ultimonumerodado2 = dado.nextInt(6) + 1;
            soma = ultimonumerodado1 + ultimonumerodado2;
        }while(soma > 6);
        return soma;
    }

@Override
    public boolean isAzarado() {
        return true;
    }

}