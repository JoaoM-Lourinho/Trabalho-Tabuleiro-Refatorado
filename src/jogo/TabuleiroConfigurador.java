package jogo;

import casas.*;

public class TabuleiroConfigurador {

    private static final int NUMERO_DE_CASAS = 40;

    public static Casa[] criarCasas() {
        Casa[] casas = new Casa[NUMERO_DE_CASAS];
        
        // 1. Inicializa todas como CasaNormal (OCP para o default)
        for (int i = 0; i < casas.length; i++) {
            casas[i] = new CasaNormal(i + 1);
        }

        // 2. Define as posições especiais (Configuração)
        casas[9] = new CasaBloqueio(10);
        casas[24] = new CasaBloqueio(25);
        casas[37] = new CasaBloqueio(38);
        casas[12] = new CasaSurpresa(13);
        casas[4] = new CasaDaSorte(5);
        casas[14] = new CasaDaSorte(15);
        casas[29] = new CasaDaSorte(30);
        casas[18] = new CasaMagica(19);
        casas[32] = new CasaMagica(33);
        casas[6] = new CasaVoltarInicio(7);
        casas[34] = new CasaVoltarInicio(35);

        return casas;
    }
}