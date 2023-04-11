package mermoderna.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FarmConexaoDao {
	
	//Metodo responsavel pela conexao com a base de dados (Banco Postgres)
	public static Connection getConnection() {

		try {
			// Variavel da URL para conexao com o banco
			final String url = "jdbc:postgresql://localhost:5432/postgres";
			// Variavel do usuario do banco
			final String usuario = "postgres";
			// Variavel da senha do Banco
			final String senha = "123";

			// O metodo getConnection lanca uma excecao que precisa ser tratada pelo Throws
			// ou try cat

			// Neste caso implementado se acontecer uma excecao. A aplicao simplesmente
			// sai do metodo
			//System.out.println("Conexao realizada");
			return DriverManager.getConnection(url, usuario, senha);

		} catch (SQLException e) {

			throw new RuntimeException("Erro ao tentar conectar a Base de Dados: ERRO: "+e);
		}

	}
}
