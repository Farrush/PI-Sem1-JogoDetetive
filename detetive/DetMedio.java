/***********************************************************************************************************************
 * Nome do Programa: Jogo Detetive
 * 	Autores:
 *  Caroline Ghidotti
 *  Giancarlo Marques
 *  Leonardo Cesario
 *  Rafael Carrenho
 *  Raissa Casilla
 *
 *	Versão: 0.5
 *	Última modificação: 20/04/2023
 ***********************************************************************************************************************/
package detetive;

import java.util.Random;
import java.util.Scanner;
public class DetMedio {
	public static void main(String[] args) {
		// Variáveis de cores do console
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_GREEN = "\u001B[32m";

		Scanner teclado = new Scanner(System.in);

		// Nome do jogador
		String jogador;

		// Arrays com os dados do jogo
		String[] suspeitos = { "Sargento", "Florista", "Chefe de Cozinha", "Mordomo", "Médico", "Dançarina", "Coveiro",
				"Advogado" };
		String[] armas = { "Faca", "Espingarda", "Veneno", "Pá", "Machado", "Pé de Cabra", "Soco inglês", "Tesoura" };
		String[] locais = { "Escritório", "Cozinha", "Hall", "Conservatório", "Salão", "Sala de Jantar",
				"Sala de jogos", "Biblioteca" };
		String[] palpite = { "", "", "" };

		// Variáveis que guardam todos os palpites que o jogador tentou
		// palpites de suspeito
		String tentativasSus = "";
		// palpites de arma
		String tentativasArm = "";
		// palpites de local
		String tentativasLoc = "";

		// o Culpado é definido quando o programa inicia, selecionando um suspeito, arma
		// e local aleatório
		String[] culpado = { suspeitos[new Random().nextInt(suspeitos.length - 1)],
				armas[new Random().nextInt(armas.length - 1)], locais[new Random().nextInt(locais.length - 1)] };

		// TESTE para ver o culpado System.out.println("Culpado: "+culpado[0]+"
		// "+culpado[1]+" "+culpado[2]);

		System.out.println("Bem vindo ao jogo Detetive!\nDigite seu nome:");
		jogador = teclado.nextLine();

		System.out.println("Encontre o culpado pelo crime: \n\n");
		// Este laço de repetição representa as rodadas, a cada rodada ele se repete e
		// exibe novamente a tabela de dados do jogo
		// Por enquanto ele não termina até que chegue em certo número de rodadas,
		// enquanto o jogador não acertar
		// O culpado, esse laço não acaba
		while (true) {

			System.out.print("Lista de suspeitos   ");
			System.out.print("Lista de armas   ");
			System.out.print("Lista de locais\n");

			// Este laço serve para navegar por todos os elementos dos arrays de dados do
			// jogo, e exibe cada um
			for (int i = 0; i < suspeitos.length; i++) {

				// Este if verifica, ao exibir um nome específico do array, se ele já foi
				// tentado e se ele está
				// correto ou errado, se ele estiver errado, exibira vermelho, se estiver certo,
				// ficará verde
				// se não foi jogado ainda, fica preto
				if (suspeitos[i].equals(culpado[0]) && tentativasSus.contains(suspeitos[i])) {
					System.out.printf(ANSI_GREEN + "%-20s " + ANSI_RESET, suspeitos[i]);
				} else if (tentativasSus.contains(suspeitos[i])) {
					System.out.printf(ANSI_RED + "%-20s " + ANSI_RESET, suspeitos[i]);
				} else {
					System.out.printf("%-20s ", suspeitos[i]);
				}
				// If que faz a mesma coisa do anterior, mas com as armas
				if (armas[i].equals(culpado[1]) && tentativasArm.contains(armas[i])) {
					System.out.printf(ANSI_GREEN + "%-16s " + ANSI_RESET, armas[i]);

				} else if (tentativasArm.contains(armas[i])) {
					System.out.printf(ANSI_RED + "%-16s " + ANSI_RESET, armas[i]);
				} else {
					System.out.printf("%-16s ", armas[i]);
				}
				// If que faz a mesma coisa do anterior, mas com os locais
				if (locais[i].equals(culpado[2]) && tentativasLoc.contains(locais[i])) {
					System.out.printf(ANSI_GREEN + "%-18s " + ANSI_RESET, locais[i]);
				} else if (tentativasLoc.contains(locais[i])) {
					System.out.printf(ANSI_RED + "%-18s " + ANSI_RESET, locais[i]);
				} else {
					System.out.printf("%-18s ", locais[i]);
				}

				System.out.println("");

			}
			// Após exibir os dados, o programa pergunta o palpite do jogador
			System.out.println("Dê seu palpite: \nQuem é o assasino?\n");
			palpite[0] = teclado.nextLine();

			// Salva o palpite de suspeito da rodada na variável que guarda todos os
			// palpites de suspeito
			tentativasSus += palpite[0];

			System.out.println("Qual foi a arma: \n");
			palpite[1] = teclado.nextLine();
			
			// salva o palpite de arma
			tentativasArm += palpite[1];
			
			System.out.println("Qual foi o local? \n");
			palpite[2] = teclado.nextLine();

			// salva o palpite de local
			tentativasLoc += palpite[2];

			// Se o jogador acertar os três, o loop acaba e aparece uma mensagem de fim de
			// jogo
			if (palpite[0].equals(culpado[0]) && palpite[1].equals(culpado[1]) && palpite[2].equals(culpado[2])) {
				System.out.println(jogador + ", Parabéns, você acertou o culpado.\nFim de jogo.");
				break;

				// Se não, o loop continua até ele acertar
			} else {
				System.out.println("Você errou!\nTente novamente. ");

			}

		}

		teclado.close();
	}

}
