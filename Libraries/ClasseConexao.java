package pacote_banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClasseConexao {

	private static Connection conexao = null;
	
	//Método para fazer a conexao com o MYSQL	
	public static Connection Conectar() {
		try {
			//Verificar se a conexao já foi feita:
			if (conexao == null) {
				//Vamos criar uma string contendo o caminho da base de dados:
				String url = "jdbc:mysql://localhost/base_funcionarios";
				//Vamos fazer a conexao:
				conexao = DriverManager.getConnection(url,"root","vertrigo");
			}
			
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
		return conexao;
	}
	
	public static void FecharConexao(Connection c) {
		try {
			if (c != null) {	//Verifica se a Conexão c está feita:
				c.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
