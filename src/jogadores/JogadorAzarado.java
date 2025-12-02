package jogadores;

public class JogadorAzarado extends Jogador{
    
    public JogadorAzarado(String cor){
        super(cor);
    }

@Override
    public int rolarDados() {
        int soma = 0;
        do {
            this.ultimoNumeroDado1 = dado.nextInt(6) + 1;
            this.ultimoNumeroDado2 = dado.nextInt(6) + 1;
            soma = ultimoNumeroDado2 + ultimoNumeroDado2;
        }while(soma > 6);
        return soma;
    }

@Override
    public int avancarCasaDaSorte() {
        return this.getCasaDoTabuleiro();
    }
}