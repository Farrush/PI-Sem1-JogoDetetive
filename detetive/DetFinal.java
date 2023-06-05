/***********************************************************************************************************************
 *  Nome do Programa: Jogo Detetive
 * 	Autores:
 *  Caroline Ghidotti
 *  Giancarlo Marques
 *  Leonardo Cesario
 *  Rafael Carrenho
 *  Raissa Casilla
 *
 *	Versão: 1.0
 *	Última modificação: 05/06/2023
 ***********************************************************************************************************************/
package detetive;

import java.util.Random;
import java.util.Scanner;

public class DetFinal {
	// Variáveis de cores do console
	static final String ANSI_RESET = "\u001B[0m";
	static final String ANSI_RED = "\u001B[31m";
	static final String ANSI_GREEN = "\u001B[32m";

	// Arrays com os dados do jogo
	static String[] suspeitos = { "Sargento", "Florista", "Chefe de Cozinha", "Mordomo", "Médico", "Dançarina",
			"Coveiro",
			"Advogado" };
	static String[] armas = { "Faca", "Espingarda", "Veneno", "Pá", "Machado", "Pé de Cabra", "Soco inglês",
			"Tesoura" };
	static String[] locais = { "Escritório", "Cozinha", "Hall", "Conservatório", "Salão", "Sala de Jantar",
			"Sala de jogos", "Biblioteca" };

	// Variáveis que guardam todos os palpites que o jogador tentou
	// palpites de suspeito
	// Motivo: no método que exibe as listas do jogo, em cada elemento do vetor, ocorre uma verificação
	// baseada no método "Contain" da classe String, verifica se o jogador já tentou aquela opção
	// em uma sintaxe parecida com SE TentativasSus CONTÉM elementoDoVetor
	static String tentativasSus = "";
	// palpites de arma
	static String tentativasArm = "";
	// palpites de local
	static String tentativasLoc = "";
	
	
	// Array que guarda o palpite do jogador nas rodadas
	static String[] palpite = new String[3];
	
	// Guarda o menor número de rodadas que o jogador vencer
	static String[] melhorPontuacao = {"n/a","0"};

	static String[] culpado = new String[3];

	static Scanner teclado = new Scanner(System.in);
	// Nome do jogador
	static String jogador;
	
	static int rodada = 0;


	public static void main(String[] args) {
		
		System.out.println("Bem vindo(a) ao jogo Detetive!");
		exibeMenu();

		teclado.close();
	}

	/**
	 * Inicia o jogo
	 */
	static void iniciaJogo() {

		// o Culpado é definido quando o jogo inicia, selecionando um suspeito, arma
		// e local aleatório
		culpado[0] = suspeitos[new Random().nextInt(suspeitos.length)];
		culpado[1] = armas[new Random().nextInt(armas.length)];
		culpado[2] = locais[new Random().nextInt(locais.length)];

		System.out.println("Digite seu nome:");
		jogador = teclado.next();

		System.out.println("Encontre o culpado pelo crime: \n");

		// Este laço de repetição representa as rodadas, a cada rodada ele se repete e
		// exibe novamente a tabela de dados do jogo
		// ele não termina até que chegue em um determinado número de rodadas
		// Não acaba até o jogador vencer
		while (true) {
			// aumenta o número da rodada em cada repetição
			rodada++;
			
			exibirListas(suspeitos, armas, locais);

			// Após exibir os dados, o programa pergunta o palpite do jogador
			System.out.println("Digite o número da opção(entre 1 e 8): ");
			int indice = 0;
			
			do {
				System.out.println("Quem é o assassino?");
				indice = teclado.nextInt() - 1;
			} while (indice > 7 || indice < 0);
			
			// Determina o valor da primeira posição do vetor de palpites como o 
			// Suspeito escolhido pelo jogador
			palpite[0] = suspeitos[indice];
			
			// Salva o palpite de suspeito da rodada na variável que guarda todos os
			// palpites de suspeito
			tentativasSus += palpite[0];

			do {
				System.out.println("Qual foi a arma:");
				indice = teclado.nextInt() - 1;
			} while (indice > 7 || indice < 0);
			
			// Determina o valor da segunda posição do vetor de palpites como a
			// Arma escolhida pelo jogador
			palpite[1] = armas[indice];
			
			// salva o palpite de arma
			tentativasArm += palpite[1];

			do {
				System.out.println("Qual foi o local?");
				indice = teclado.nextInt() - 1;
			} while (indice > 7 || indice < 0);
			
			// Determina o valor da terceira posição do vetor de palpites como o 
			// Local escolhido pelo jogador
			palpite[2] = locais[indice];
			
			// salva o palpite de local
			tentativasLoc += palpite[2];

			// Se o jogador acertar os três, o loop acaba e aparece uma mensagem de fim de
			// jogo
			if (verificaPalpite()) {
				aoVencer(jogador, rodada);

				break;
				// Se não, o loop continua até ele acertar
			} else {
				System.out.println("-----------------------------------------------------------------");
				System.out.println(""+ANSI_RED+"Você errou!"+ANSI_RESET+"\nTente novamente.");
				System.out.println("-----------------------------------------------------------------");
			}

		}
		System.out.println("Deseja Retornar ao menu?");
		System.out.println("1 - Retornar ao menu\n2 - Encerrar");
		switch (teclado.nextInt()) {
			case 1:
				exibeMenu();
				break;
			case 2:
			default:
				System.exit(0);
		}
	}
	/**
	 * Exibe o menu inicial
	 */
	static void exibeMenu() {
		System.out.println("Digite\n1 - Jogar\n2 - Ver Pontuação\n3 - Sair");
		int escolha = teclado.nextInt();
		switch (escolha) {
			case 1:
				iniciaJogo();
				break;
			case 2:
				exibirPontuacao();
				exibeMenu();
				break;
			case 3:
			default:
				System.exit(0);
		}
	}

	/**
	 * Exibe no console os arrays passados como parâmetro em forma de lista
	 * 
	 * @param susp Lista de suspeitos
	 * @param arma  Lista de armas
	 * @param local Lista de locais
	 */
	static void exibirListas(String[] susp, String[] arma, String[] local) {


		System.out.print("==Lista de suspeitos===");
		System.out.print("==Lista de armas===");
		System.out.print("==Lista de locais===\n");

		// Este laço serve para navegar por todos os elementos dos arrays de dados do
		// jogo, e exibe cada um
		for (int i = 0; i < susp.length; i++) {

			// Este if verifica, ao exibir um nome específico do array, se ele já foi
			// tentado e se ele está
			// correto ou errado, se ele estiver errado, exibira vermelho, se estiver certo,
			// ficará verde
			// se não foi jogado ainda, fica preto
			if (susp[i].equals(culpado[0]) && tentativasSus.contains(susp[i])) {
				System.out.printf("%d " + ANSI_GREEN + "%-20s " + ANSI_RESET, (i + 1), susp[i]);
			} else if (tentativasSus.contains(susp[i])) {
				System.out.printf("%d " + ANSI_RED + "%-20s " + ANSI_RESET, (i + 1), susp[i]);
			} else {
				System.out.printf("%d %-20s ", (i + 1), susp[i]);
			}
			// If que faz a mesma coisa do anterior, mas com as armas
			if (arma[i].equals(culpado[1]) && tentativasArm.contains(arma[i])) {
				System.out.printf("%d " + ANSI_GREEN + "%-16s " + ANSI_RESET, (i + 1), arma[i]);

			} else if (tentativasArm.contains(arma[i])) {
				System.out.printf("%d " + ANSI_RED + "%-16s " + ANSI_RESET, (i + 1), arma[i]);
			} else {
				System.out.printf("%d %-16s ", (i + 1), arma[i]);
			}
			// If que faz a mesma coisa do anterior, mas com os locais
			if (local[i].equals(culpado[2]) && tentativasLoc.contains(local[i])) {
				System.out.printf("%d " + ANSI_GREEN + "%-18s " + ANSI_RESET, (i + 1), local[i]);
			} else if (tentativasLoc.contains(local[i])) {
				System.out.printf("%d " + ANSI_RED + "%-18s " + ANSI_RESET, (i + 1), local[i]);
			} else {
				System.out.printf("%d %-18s ", (i + 1), local[i]);
			}

			System.out.println("");

		}
		System.out.println("==============================================================");
	}
	/**
	 * Limpa todas as variáveis relevantes para reiniciar o jogo
	 */
	static void limparVariaveis() {
		rodada = 0;
		jogador = "";
		tentativasArm = "";
		tentativasLoc = "";
		tentativasSus = "";
		palpite[0] = "";
		palpite[1] = "";
		palpite[2] = "";
		culpado[0] = "";
		culpado[1] = "";
		culpado[2] = "";
	}
	/**
	 * Mostra informações a respeito do último jogador que venceu em menor número de tentativas
	 */
	static void exibirPontuacao() {
		if(!melhorPontuacao[1].equals("0")) {
		System.out.printf("A melhor pontuação foi %nobtida pelo jogador %s,"
				+ " vencendo em %s rodadas%n%n", melhorPontuacao[0], melhorPontuacao[1]);
		}
		else {
			System.out.println("Sem informações sobre melhor pontuação.\n\n");
		}
	}
	/**
	 * Exibe a mensagem de vitória e salva a pontuação se for melhor que a anterior
	 * @param jogador Nome do player
	 * @param rodada Número atual da rodada
	 */
	static void aoVencer(String jogador, int rodada) {
		System.out.printf("%s , Parabéns, você encontrou o culpado: %s com %s em %s.\n" +
				"Quantidade de rodadas: %d\nFim de jogo.\n\n", jogador, palpite[0], palpite[1], palpite[2],
				rodada);

		// Salva a melhor pontuação, que é o número de rodadas que o jogador levou para
		// acertar
		if (melhorPontuacao[1].equals("0") || Integer.parseInt(melhorPontuacao[1]) >= rodada) {
			melhorPontuacao[0] = jogador;
			melhorPontuacao[1] = Integer.toString(rodada);
		}
		limparVariaveis();
	}
	/**
	 * Verifica se o palpite do jogador na rodada atual é exatamente igual ao culpado definido na partida
	 * 
	 * @return true se o palpite for igual ao culpado
	 */
	static boolean verificaPalpite() {
		boolean resultado = false;
		if(palpite[0].equals(culpado[0]) && palpite[1].equals(culpado[1]) && palpite[2].equals(culpado[2])) {
			resultado = true;
		}
		return resultado;
	}
}
