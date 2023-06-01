/***********************************************************************************************************************
 * Nome do Programa: Jogo Detetive
 * 	Autores:
 *  Caroline Ghidotti
 *  Giancarlo Marques
 *  Leonardo Cesario
 *  Rafael Carrenho
 *  Raissa Casilla
 *
 *	Versão: 0.1
 *	Última modificação: 06/04/2023
 ***********************************************************************************************************************/
package detetive;

import java.util.Random;
import java.util.Scanner;
public class DetInicial {
	public static void main(String[] args) {
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

				System.out.printf("%-20s " , suspeitos[i]);
				System.out.printf("%-16s ", armas[i]);
				System.out.printf("%-18s ", locais[i]);
				System.out.println("");

			}
			// Após exibir os dados, o programa pergunta o palpite do jogador
			System.out.println("\nDê seu palpite: \nQuem é o assasino?");
			palpite[0] = teclado.nextLine();

			System.out.println("Qual foi a arma: ");
			palpite[1] = teclado.nextLine();

			System.out.println("Qual foi o local?");
			palpite[2] = teclado.nextLine();
			System.out.println();
			if(palpite[0].equals(culpado[0])){
				System.out.println("Acertou o suspeito: "+palpite[0]);
			}
			
			if(palpite[1].equals(culpado[1])){
				System.out.println("Acertou a arma: "+palpite[1]);
			}
			
			if(palpite[2].equals(culpado[2])){
				System.out.println("Acertou o local: "+palpite[2]);
			}
			System.out.println();
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
