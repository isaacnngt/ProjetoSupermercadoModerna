package mermoderna.view;

import java.util.Scanner;
import mermoderna.controller.Auxiliar;
import mermoderna.model.*;

public class MenuPrincipal {

	//CLASSE PRINCIPAL
	public static void main(String[] args) throws Throwable {

		
		//Barra de progresso (inicio)
		int totalTasks = 10;

		for (int i = 1; i <= totalTasks; i++) {
		    System.out.print("Carregando...  " + i + ": ");
		    int progress = (int) ((double) i / totalTasks * 100);
		    Auxiliar.printProgressBar(progress);
		    System.out.println();

		    try {
		        Thread.sleep(500);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		}//Barra de progresso (fim)
		
		//Limpa o console do java
		Auxiliar.clearConsole();
		
		//Carrega logo do sistema através do metodo logo() existente na classe Auxiliar
		Auxiliar.logo();

		// Instanciando Scanner para capturar o que for digitado na tela
		Scanner scn = new Scanner(System.in);

		// Menu de Opcoes
		System.out.println("=================================");
		System.out.println("  ESCOLHA UMA OPÇÃO ABAIXO:");
		System.out.println("=================================");
		System.out.println("1 -  Cadastrar Produto");
		System.out.println("2 -  Atualizar Produto");
		System.out.println("3 -  Excluir Produto");
		System.out.println("4 -  Cadastrar Secao");
		System.out.println("5 -  Atualizar Secao");
		System.out.println("6 -  Excluir Secao");
		System.out.println("7 -  Buscar Produto");
		System.out.println("8 -  Buscar Secao");
		System.out.println("---------------------------------");
		System.out.println("Opção: ");

		int num1 = scn.nextInt();
		scn.nextLine();

		// Garantir que recebera um numero valido
		// Enquanto nao receber ficara solicitando uma opcao valida
		while (num1 > 8 && num1 < 1) {

			System.out.println("Dado inválido, escolha a opção 1 a 8");
			num1 = scn.nextInt();
			scn.nextLine();
		}

		// inicio das opcoes
		switch (num1) {
		case 1:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de inserir produto existente na classe ProdutoDao
			ProdutoDao.InserirProduto();
			break;
		case 2:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de atualizarProduto produto existente na classe ProdutoDao
			ProdutoDao.atualizarProduto();
			break;
		case 3:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de excluirProduto produto existente na classe ProdutoDao
			ProdutoDao.excluirProduto();
			break;
		case 4:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de inserir secao existente na classe SecaoDao
			SecaoDao.inserirSecao();
			break;
		case 5:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de atualizar Secao existente na classe SecaoDao
			SecaoDao.atualizaSecao();
			break;
		case 6:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de deletar Secao existente na classe SecaoDao
			SecaoDao.deletarSecao();
			break;
		case 7:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de consultar produto existente na classe SecaoDao
			ProdutoDao.consultarTbProduto();
			//Valida e chama o menu principal
			Auxiliar.voltaMenu();
			break;
		case 8:
			//Limpa o console do java
			Auxiliar.clearConsole();
			//Chama o metodo de consultar seção existente na classe SecaoDao
			SecaoDao.consultarTbSecao();
			//Valida e chama o menu principal
			Auxiliar.voltaMenu();
			break;
		default:
			//Limpa o console do java
			Auxiliar.clearConsole();
			System.out.println("Dado inválido, escolha a opção 1 a 8");

		}

		// fecha o scanner
		scn.close();
	}

}