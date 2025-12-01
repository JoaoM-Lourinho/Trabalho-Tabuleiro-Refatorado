package jogadores;

import java.util.Random;

public abstract class Jogador {
    private String cor; //cor do personagem do competidor
    private int casadotabuleiro; //qual casa do tabuleiro o competidor está
    private boolean estaBloqueado = false;
    protected Random dado = new Random();
    protected int ultimonumerodado1, ultimonumerodado2; //resultado dos ultimos dados rolados
    private int contadorJogadas = 0;

    public Jogador(String cor){
        this.cor = cor;
        this.casadotabuleiro = 0;
    }
    
    public abstract int rolarDados();

    public boolean dadosIguais(){
        return ultimonumerodado1 == ultimonumerodado2;
    }

    public String getCor() {
        return cor;
    } 
    
    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public int getCasadotabuleiro() {
        return casadotabuleiro;
    } 
    
    public void setCasadotabuleiro(int casadotabuleiro) {
        this.casadotabuleiro = casadotabuleiro;
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

    public boolean isAzarado() {
        return false;
    }

    public boolean isSortudo() {
        return false;
    }

    public boolean isNormal() {
        return false;
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
}   