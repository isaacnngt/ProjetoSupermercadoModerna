package mermoderna.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mermoderna.controller.Auxiliar;
import mermoderna.controller.ProdutoController;
import mermoderna.controller.SecaoController;

public class SecaoDao {

	public static void deletarSecao() throws Throwable {

		Scanner scanner = new Scanner(System.in);

		System.out.println("******** EXCUIR SEÇÃO *********)");
		System.out.println("Informa o Id da secao a ser excluída: ");

		// Comando SQL a ser executado
		String sql = "DELETE  FROM secao_tb WHERE id_secao = ?";

		int idsecao = scanner.nextInt();
		scanner.nextLine();

		// Abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Preparacao de uma declaracao que esta recebendo um parametro
		PreparedStatement stmt = conexao.prepareStatement(sql);

		// Parametro 1
		stmt.setInt(1, idsecao);

		// Verifica a quantidade de linhas que sofreram atualizacoes
		// Se mais de uma linha sofre atualizacoes indica que o dado foi excluido com
		// sucesso
		if (stmt.executeUpdate() > 0) {
			System.out.println("Secao deletada com sucesso");

			// Caso nenhuma linha nao seja atualizada
			// Indica nenhuma alteracao sofreu o banco de dados e asism o dado nao foi
			// excluido
		} else {
			System.out.println("Secao não encontrata , ou nao existe");
		}

		// Limpa console Java
		Auxiliar.voltaMenu();

		// fecha o statment
		stmt.close();

		// fecha o scanner
		scanner.close();

		// fecha a conexao
		conexao.close();

	}

	public static void inserirSecao() throws Throwable {

		System.out.println("******** CADASTRO DE SEÇÃO *********)");
		System.out.println(" informa o nome da seção: ");

		Scanner scanner = new Scanner(System.in);

		String nome = scanner.nextLine();

		// Comando SQL a ser executado
		String inserirSecao = "Insert into " + "secao_tb" + "(nome)" + "values" + "(?)";

		// Abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		PreparedStatement statement = conexao.prepareStatement(inserirSecao);

		// Parametro 1
		statement.setString(1, nome);

		// Executa o comando de update (SQL) passando como parametro as informacoes do
		// stmt
		statement.execute();

		System.out.println("Seção criada com sucesso");

		// Limpa o console Java
		Auxiliar.voltaMenu();

		// Fecha o statement
		statement.close();

		// fecha a conexao
		conexao.close();

	}

	public static void atualizaSecao() throws Throwable {

		Scanner scn = new Scanner(System.in);

		System.out.println("******** ATUALIZACAO DE DADOS DA SECAO *********)");
		System.out.println("Deseja visualizar a lista de seções cadastradas? (S- sim | N - não)");
		String opcao_lista_produ = scn.next();

		// Verifica se a opcao fornecida anteriormente eh valida (Se eh S ou N)
		// So vai sair do loop quando receber S/N
		while (!opcao_lista_produ.equalsIgnoreCase("S") && !opcao_lista_produ.equalsIgnoreCase("N")) {
			Auxiliar.clearConsole();
			System.out.println("Digite um valor válido S- sim ou N - não");
			opcao_lista_produ = scn.next();
		}

		// Limpar o console do java e exibe s lista de secoes cadastrados
		if (opcao_lista_produ.equalsIgnoreCase("S")) {
			Auxiliar.clearConsole();
			SecaoDao.consultarTbSecao();
		}

		System.out.print(" ");
		System.out.print("Qual seção deseja atualizar?(*Informe o ID) : ");
		int id = scn.nextInt();

		// Limpa console java
		Auxiliar.clearConsole();

		System.out.print("Qual novo nome da secao?: ");
		String nome = scn.next();

		// Abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Comando Sql de inserção String sql =
		String sql = "UPDATE secao_tb SET nome=? WHERE id_secao=?";

		// Preparo da declaracao
		PreparedStatement stmt = conexao.prepareStatement(sql);

		// Passando para o Statement "stmt" os parâmetros de insercao
		stmt.setString(1, nome);
		stmt.setFloat(2, id);

		// executa o statement
		stmt.execute();

		System.out.println("Seção Atualizada com sucesso!");

		// Limpa o console do java
		Auxiliar.voltaMenu();

		// fecha o statement
		stmt.close();

		// fecha a conexao
		conexao.close();

	}

	public static void consultarTbSecao() throws Throwable {

		// Limpa o console do java
		Auxiliar.clearConsole();

		// Cria uma lista de secao para armazenar os dados do resultset
		List<SecaoController> secao = new ArrayList<SecaoController>();

		// Comando SQL a ser executado
		String consulta = "SELECT * FROM secao_tb order by 1 asc";

		// abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Preparacao de uma declaracao como nao eh passado parametros eh utilizado o
		// Statement
		Statement statement = conexao.createStatement();

		// Obtem o resultado oriundo da consulta SQL
		ResultSet resultado = statement.executeQuery(consulta);

		// Percorre todos os dados vindo do resulset/consulta SQL
		while (resultado.next()) {
			final String nome = resultado.getString("nome");
			final int idSecao = resultado.getInt("id_secao");

			// Adiciona a lista de secao os dados vindo da consulta SQL instanciando um
			// nova secao
			secao.add(new SecaoController(nome, idSecao));

		}

		// Realiza a leitura das secoes para poder exibir na tela do usuario
		for (int i = 0; i < secao.size(); i++) {
			System.out.println(secao.get(i));
		}

		// fecha o statement
		statement.close();

		// fecha a conexao
		conexao.close();
	}

}
