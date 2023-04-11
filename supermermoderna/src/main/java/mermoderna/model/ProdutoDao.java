package mermoderna.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mermoderna.controller.Auxiliar;
import mermoderna.controller.ProdutoController;
import mermoderna.controller.SecaoController;
import mermoderna.view.MenuPrincipal;

public class ProdutoDao {

	static Scanner scn = new Scanner(System.in);

	public static void atualizarProduto() throws Throwable {

		// (INICIO) - Desse jeito que foi criado ele bloquear a tentativa sql Injector

		Connection conexao = FarmConexaoDao.getConnection();

		System.out.println("******** ATUALIZACAO DE DADOS DO PRODUTO *********)");
		// Pergunta se o usuario deseja visualizar os produtos cadastrados para se
		// basear qual produto
		// que devera ser manipulado.
		System.out.println("Deseja visualizar a lista de produtos? (S- sim | N - não)");
		String opcao_lista_produ = scn.next();

		// Verifica se a opcao fornecida anteriormente eh valida (Se eh S ou N)
		// So vai sair do loop quando receber S/N
		while (!opcao_lista_produ.equalsIgnoreCase("S") && !opcao_lista_produ.equalsIgnoreCase("N")) {
			Auxiliar.clearConsole();
			System.out.println("Digite um valor válido S- sim ou N - não");
			opcao_lista_produ = scn.next();
		}

		// Limpar o console do java e exibe s lista de produtos cadastrados
		if (opcao_lista_produ.equalsIgnoreCase("S")) {
			Auxiliar.clearConsole();
			ProdutoDao.consultarTbProduto();
		}

		System.out.print(" ");
		System.out.print("Qual produto deseja atualizar?(*Informe o ID) : ");
		int id = scn.nextInt();

		// Limpa o console do java
		Auxiliar.clearConsole();

		System.out.println("Qual informação deseja atualizar? ");
		System.out.println("1 -  Nome do Produto");
		System.out.println("2 -  Valor do Produto");
		System.out.println("3 -  Nome e valor do Produto");
		System.out.println("4 -  Secao do Produto");

		int num1 = scn.nextInt();

		// Garantir que recebera um numero valido
		// Enquanto nao receber ficara solicitando uma opcao valida
		while (num1 > 4 && num1 < 1) {

			System.out.println("Dado inválido, escolha a opção 1 a 4");
			num1 = scn.nextInt();
			scn.nextLine();
		}

		// inicio das opcoes
		switch (num1) {
		case 1:
			// Limpa o console do java
			Auxiliar.clearConsole();

			System.out.print("Qual novo nome?: ");
			String nome = scn.next();

			try {
				// Comando SQL a ser executado
				String sql = "UPDATE produto_tb SET nome=? WHERE id_produto=?";

				// Preparacao de uma declaracao que esta recebendo um parametro
				PreparedStatement stmt = conexao.prepareStatement(sql);

				// Parametro 1 com o nome
				stmt.setString(1, nome);

				// Parametro 2 com o id
				stmt.setInt(2, id);

				// Executa o comando de update (SQL) passando como parametro as informacoes do
				// stmt
				stmt.execute();

				System.out.println("Produto Atualizado com sucesso!");
				System.out.println("Abaixo segue abaixo as informações atualizadas");

				// Comando SQL a ser executado
				String consulta = "SELECT * FROM produto_tb where id_produto = ?";

				// Preparacao de uma declaracao que esta recebendo um parametro
				PreparedStatement stmt2 = conexao.prepareStatement(consulta);

				// Parametro 1 com o nome
				stmt2.setInt(1, id);

				// Obtem o resultado oriundo da consulta (SELECT * FROM produto_tb where
				// id_produto = ?)
				ResultSet resultSet = stmt2.executeQuery();

				// Cria uma lista de produtos para armazenar os dados do resultset
				List<ProdutoController> produto = new ArrayList<ProdutoController>();

				// Percorre todos os dados vindo do resulset/consulta SQL
				while (resultSet.next()) {
					int idProduto = resultSet.getInt("id_produto");
					nome = resultSet.getString("nome");
					BigDecimal valor = resultSet.getBigDecimal("valor");
					int idsecao = resultSet.getInt("id_secao");

					// Adiciona a lista de produtos os dados vindo da consulta SQL instanciando um
					// novo produto
					produto.add(new ProdutoController(idProduto, nome, valor, idsecao));
				}

				// Realiza a leitura dos produto para poder exibir na tela do usuario
				for (ProdutoController s : produto) {

					System.out.println("Id_Produto= " + s.getIdProduto() + " nome= " + s.getNome() + " valor= "
							+ s.getValor() + " secao= " + s.getIdsecao());
				}

			} catch (SQLException e) {

				// Se tiver algum erro na execucao retornara na tela do usuario a mensagem
				// abaixo
				System.out
						.println("Erro ao nome do produto ou na exibição da atualizacao do produto: " + e.getMessage());
			}

			break;

		case 2:

			// Limpa o console do java
			Auxiliar.clearConsole();
			System.out.print("Qual novo valor do produto?: ");
			double preco = scn.nextDouble();

			try {
				// Comando SQL a ser executado pelo driver JDBC
				String sql = "UPDATE produto_tb SET valor=? WHERE id_produto=?";

				// Preparacao de uma declaracao que esta recebendo um parametro
				PreparedStatement stmt = conexao.prepareStatement(sql);

				// Parametro 1 com o preco
				stmt.setDouble(1, preco);

				// Parametro 2 com o id
				stmt.setInt(2, id);

				// Executa o comando de update (SQL) passando como parametro as informacoes do
				// stmt
				stmt.execute();

				System.out.println("Produto Atualizado com sucesso!");
				System.out.println("Abaixo segue abaixo as informações atualizadas");

				// Comando SQL a ser executado
				String consulta = "SELECT * FROM produto_tb where id_produto = ?";

				// Preparacao de uma declaracao que esta recebendo um parametro
				PreparedStatement stmt2 = conexao.prepareStatement(consulta);

				// Parametro 1 com o id
				stmt2.setInt(1, id);

				// Obtem o resultado oriundo da consulta (SELECT * FROM produto_tb where
				// id_produto = ?)
				ResultSet resultSet = stmt2.executeQuery();

				// Cria uma lista de produtos para armazenar os dados do resultset
				List<ProdutoController> produto = new ArrayList<ProdutoController>();

				// Percorre todos os dados vindo do resulset/consulta SQL
				while (resultSet.next()) {
					int idProduto = resultSet.getInt("id_produto");
					nome = resultSet.getString("nome");
					BigDecimal valor = resultSet.getBigDecimal("valor");
					int idsecao = resultSet.getInt("id_secao");

					// Adiciona a lista de produtos os dados vindo da consulta SQL instanciando um
					// novo produto
					produto.add(new ProdutoController(idProduto, nome, valor, idsecao));
				}

				// Realiza a leitura dos produto para poder exibir na tela do usuario
				for (ProdutoController s : produto) {

					// Se tiver algum erro na execucao retornara na tela do usuario a mensagem
					// abaixo
					System.out.println("Id_Produto= " + s.getIdProduto() + " nome= " + s.getNome() + " valor= "
							+ s.getValor() + " secao= " + s.getIdsecao());
				}

			} catch (SQLException e) {
				System.out.println("Erro ao atualizar valor do produto ou na exibição da atualizacao do produto: "
						+ e.getMessage());
			}

			break;
		case 3:

			// Limpa o console do java
			Auxiliar.clearConsole();

			System.out.print("Qual novo nome?: ");
			nome = scn.next();

			System.out.print("Qual novo preço?: ");
			float valor = scn.nextFloat();

			// Comando Sql de inserção String sql =
			String sql = "UPDATE produto_tb SET nome=?, valor=? WHERE id_produto=?";

			// Preparo da declaracao
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// Passando para o Statement "stmt" os parâmetros de insercao
			stmt.setString(1, nome);
			stmt.setFloat(2, valor);
			stmt.setInt(3, id);

			// executa o statement
			stmt.execute();

			System.out.println("Novo nome e valor do Produto Atualizados com sucesso!");

			break;
		case 4:
			// Limpa o console do java
			Auxiliar.clearConsole();

			System.out.print("Qual nova secao do produto?: ");
			int idsecao = scn.nextInt();

			// Comando Sql de inserção String sql =
			sql = "UPDATE produto_tb SET id_secao=? WHERE id_produto=?";

			// Preparo da declaracao
			PreparedStatement stmt3 = conexao.prepareStatement(sql);

			// Passando para o Statement "stmt" os parâmetros de insercao
			stmt3.setInt(1, idsecao);
			stmt3.setInt(2, id);

			// executa o statement
			stmt3.execute();

			System.out.println("Secao do produto Atualizada com sucesso!");

		default:
			// Limpa o console do java
			Auxiliar.clearConsole();
			System.out.println("Dado inválido, escolha a opção 1 a 4");

		}

		// Limpa o console do java
		Auxiliar.voltaMenu();

		// fecha o scanner
		scn.close();

		// fecha a conexao
		conexao.close();

	}

	public static void InserirProduto() throws Throwable {

		Scanner scn = new Scanner(System.in);
		
		//-------------------- INICIO --------------------------------------------------
		//- Verifica se existe uma secao cadastrada
		//- So eh possivel cadastrar um produto se antes tiver uma secao cadastrada
		//------------------------------------------------------------------------------
		
		// Comando SQL a ser executado
		String consulta = "SELECT * FROM secao_tb order by 1 asc";

		// abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Preparacao de uma declaracao como nao eh passado parametros eh utilizado o
		// Statement
		Statement statement = conexao.createStatement();

		// Obtem o resultado oriundo da consulta SQL
		ResultSet resultado = statement.executeQuery(consulta);

		if (!resultado.next()) {
		    System.out.println("Nao existe nenhuma secao cadastrada.");
		    System.out.println("Antes de cadastrar um produto, primero se deve cadastrar uma secao.");
		    Auxiliar.voltaMenu();
		}
		//-------------------- FIM --------------------------------------------------
		
		
		System.out.println("******** CADASTRO DE PRODUTO *********)");
		// Jeito certo (INICIO) - Desse jeito que foi criado ele bloquear� tentativa
		// de comandos SQL
		System.out.println("Informe o nome do produto");
		String nome = scn.nextLine();

		System.out.println("Informe a secao do produto");
		int secao = scn.nextInt();

		System.out.println("Informe o valor do produto");
		Double valor = scn.nextDouble();
		scn.nextLine();

		// Comando Sql de insercao
		String sql = "Insert Into produto_tb (nome, id_secao, valor) Values (?,?,?)";
		// String sql = "Insert Into pessoas (nome, codigo) Values (?,?)";

		// Preparo da declaracao
		PreparedStatement stmt = conexao.prepareStatement(sql);

		// Parametrizando
		stmt.setString(1, nome);
		stmt.setInt(2, secao);
		stmt.setDouble(3, valor);
		// stmt.setInt(2, 100);

		// execucao da query
		stmt.execute();

		System.out.println("Produto inserido com sucesso!");

		// Mensagem que pergunta ao usuario se deseja inserir um novo carro na base de
		// dados
		System.out.println("Deseja Inserir um novo produto? (S/N)");

		String carro = scn.next();

		// Se digitado S ele chama novamente o metodo de insercao, caso contrario ele
		// retorna para o Menu principal
		if (carro.equalsIgnoreCase("S")) {
			Auxiliar.clearConsole();
			InserirProduto();
		} else {
			Auxiliar.clearConsole();
			MenuPrincipal.main(null);
		}

		// Fecha o Statement
		stmt.close();
		// fecha o scanner
		scn.close();
		// fecha conexao
		conexao.close();
		// (FIM)

	}

	public static void excluirProduto() throws Throwable {

		// Instancia o Scanner
		Scanner scn = new Scanner(System.in);

		System.out.println("******** EXCLUSAO DE PRODUTO *********)");

		System.out.println("Informe o codigo do produto a ser excluído: ");
		int id_produto = scn.nextInt();

		// Abertura da conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Comando Sql para exclusao do dado
		String sql = "DELETE FROM produto_tb WHERE id_produto = ?";

		// Preparacao da declaracao
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id_produto);

		// utilizado o IF para confirma se realmente alguma linha sofreu algum impacto
		// durante o processo de exclusao
		// o "stmt.executeUpdate()" verifica a quantidade de linhas que foram excluidas
		if (stmt.executeUpdate() > 0) {

			// Mensagem de sucesso na operacao de exclusao do carro
			System.out.println("Produto excluido(a) com sucesso");

		} else {

			// Caso nao tenha produto a ser excluida ou o codigo n�o existe na base ir�
			// retornar a seguinte mensagem abaixo
			System.out.println("Produto não excluído: Codigo do produto inserido não existente na base");
		}

		Auxiliar.voltaMenu();

		// Fecha a conexao com o banco
		conexao.close();
		// fecha o Scanner
		scn.close();
		// fecha o PreparedStatement
		stmt.close();
	}

	public static void consultarTbProduto() throws Throwable {

		// Limpa o console do java
		Auxiliar.clearConsole();

		// Comando SQL a ser executado
		String consulta = "SELECT * FROM produto_tb order by 1 asc";

		// Cria uma lista de produtos para armazenar os dados do resultset
		List<ProdutoController> produto = new ArrayList<ProdutoController>();

		// Abre a conexao com o banco
		Connection conexao = FarmConexaoDao.getConnection();

		// Preparacao de uma declaracao como nao eh passado parametros eh utilizado o
		// statement
		Statement statement = conexao.createStatement();

		// Obtem o resultado oriundo da consulta (SELECT * FROM produto_tb where
		// id_produto = ?)
		ResultSet resultSet = statement.executeQuery(consulta);

		// Percorre todos os dados vindo do resulset/consulta SQL
		while (resultSet.next()) {
			int idProduto = resultSet.getInt("id_produto");
			String nome = resultSet.getString("nome");
			BigDecimal valor = resultSet.getBigDecimal("valor");
			int idsecao = resultSet.getInt("id_secao");

			// Adiciona a lista de produtos os dados vindo da consulta SQL instanciando um
			// novo produto
			produto.add(new ProdutoController(idProduto, nome, valor, idsecao));
		}

		// Realiza a leitura dos produto para poder exibir na tela do usuario
		for (int i = 0; i < produto.size(); i++) {
			System.out.println(produto.get(i));
		}

		// Fecha o Statement
		statement.close();

		// fecha conexao
		conexao.close();
		// (FIM)

	}

}
