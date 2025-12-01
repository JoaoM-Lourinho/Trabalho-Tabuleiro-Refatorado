# Jogo de Tabuleiro em Java

## Descrição do Projeto

### Visão Geral e Objetivo do Jogo

* Este projeto é uma simulação de um jogo de tabuleiro para computador, desenvolvido para comportar até seis participantes simultaneamente
* Cada jogador é identificado por uma cor única e tem sua posição rastreada no tabuleiro.
* O tabuleiro é composto por 40 casas, e todos os competidores iniciam sua jornada a partir da casa 0.
* O objetivo principal para vencer o jogo é ser o primeiro competidor a alcançar ou ultrapassar a casa 40.

### Tipos de Jogadores e Regras de Jogada

* O jogo é construído sobre os conceitos de herança e polimorfismo, apresentando três tipos distintos de jogadores que o usuário pode escolher. 
* A partida deve ser configurada com pelo menos dois jogadores de tipos diferentes.
* Os tipos são:
                 
                 • **Jogador com Sorte**, cuja soma dos dados é sempre 7 ou mais; 
                 • **Jogador Azarado**, com uma soma de dados sempre igual ou inferior a 6;
                 • **Jogador Normal**, que não possui restrições no resultado dos dados.
* A movimentação a cada rodada é definida pela soma dos valores de dois dados 
* Uma regra especial de jogada estipula que, caso um jogador obtenha dois resultados iguais nos dados, ele ganha o direito de jogar novamente.

### Tabuleiro Interativo e Casas Especiais

* O tabuleiro possui casas com efeitos que alteram a dinâmica da partida, sendo essas as casas 10, 25 e 38 fazem com que o jogador que parar nelas não jogue na próxima rodada.
* Existem também casas benéficas: as casas 5, 15 e 30 permitem ao jogador avançar 3 casas extras, desde que ele não seja um **Jogador Azarado**.
* Algumas casas promovem interação direta entre os competidores: nas casas 17 e 27, o jogador pode escolher um adversário para voltar ao início do jogo. 
* Nas casas mágicas 20 e 35, o jogador troca de lugar com o competidor que está mais atrás no tabuleiro.
* A casa 13 é uma casa "surpresa", na qual o jogador tira uma carta que muda aleatoriamente o seu tipo de jogador (sortudo, azarado ou normal).

### Interface e Recursos Técnicos

* A cada rodada, a interface do jogo deve exibir a posição atual de todos os jogadores no tabuleiro e indicar de quem é a vez de jogar. 
* Após o lançamento dos dados, o valor da soma deve ser mostrado, junto com uma mensagem caso o jogador caia em uma casa especial.
* Ao final da partida, o sistema deve declarar o vencedor e apresentar um resumo com a posição final e a quantidade total de jogadas de cada competidor.
* O projeto inclui um modo `Debug`, um recurso que permite ao usuário, em vez de jogar os dados, inserir manualmente o número da casa para a qual o jogador deverá se mover, facilitando a realização de testes.