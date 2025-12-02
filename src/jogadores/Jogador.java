package jogadores;

import java.util.Random;

public abstract class Jogador {
    private String cor; //cor do personagem do competidor
    private int casaDoTabuleiro; //qual casa do tabuleiro o competidor está
    private boolean estaBloqueado = false;
    protected Random dado = new Random();
    protected int ultimoNumeroDado1, ultimoNumeroDado2; //resultado dos ultimos dados rolados
    private int contadorJogadas = 0;
    protected static final int AVANCO_SORTE = 3;
    protected static final int POSICAO_FINAL = 39;

    public Jogador(String cor){
        this.cor = cor;
        this.casaDoTabuleiro = 0;
    }
    
    public abstract int rolarDados();

    public boolean dadosIguais(){
        return ultimoNumeroDado1 == ultimoNumeroDado2;
    }

    public String getCor() {
        return cor;
    } 
    
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public int getCasaDoTabuleiro() {
        return casaDoTabuleiro;
    } 
    
    public void setCasadotabuleiro(int casadotabuleiro) {
        this.casaDoTabuleiro = casadotabuleiro;
    }

    public boolean isBloqueado() {
        return estaBloqueado;
    }

    public void bloquear() { 
        this.estaBloqueado = true; 
    }
    
    public void desbloquear() {
        this.estaBloqueado = false;
    }

    public int getUltimonumerodado1() {
        return ultimoNumeroDado1;
    }

    public int getUltimonumerodado2() {
        return ultimoNumeroDado2;
    }

    public void registrarJogada() { 
        contadorJogadas++;
    }
    
    public int getContadorJogadas() {
        return contadorJogadas;
    }

    public void setNumeroJogadas(int numJogadas){
        this.contadorJogadas = numJogadas;
    }

    public void incrementarContadorJogadas() {
        this.contadorJogadas++;
    }

    public int setResultadoDadosManual(int dado1, int dado2) {
        this.ultimoNumeroDado1 = dado1;
        this.ultimoNumeroDado2 = dado2;
        return dado1 + dado2;
    }

    public int avancarCasaDaSorte() { 
        int casaAtual = this.getCasaDoTabuleiro(); 
        int novaCasa = casaAtual + AVANCO_SORTE; 
        if(novaCasa > POSICAO_FINAL) {
            novaCasa = POSICAO_FINAL; 
        } 
        this.setCasadotabuleiro(novaCasa); 
        return novaCasa;
    }

    public void copiarEstado(Jogador outro) {
        this.casaDoTabuleiro = outro.getCasaDoTabuleiro();
        this.estaBloqueado = outro.isBloqueado();
        this.contadorJogadas = outro.getContadorJogadas();
        this.ultimoNumeroDado1 = outro.ultimoNumeroDado1;
        this.ultimoNumeroDado1 = outro.ultimoNumeroDado2;
    }
}   