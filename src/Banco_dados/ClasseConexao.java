package Banco_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClasseConexao {
	
	private static Connection conexao = null;
	// Metodo para fazer conexãocom o MySQL;
	public static Connection Conectar() {
		try {
			// Verificar primeiro se a conexão foi já foi feita:
			if(conexao==null) {
				// Vamos criar um string contendo o caminho do banco de dados:
				String url = "jdbc:mysql://143.244.169.186/aluguel_Carros";
				//String url = "jdbc:mysql://localhost/base_funcionarios";
				
				// Vamos fazer a coneção:
				conexao = DriverManager.getConnection(url,"admin","43e3b9c1ea37bbd8c26ddd634bb74e2b6f048303744b83b2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public static void FecharConexao(Connection c) {
		try {
			if(c!=null) {
				c.close();
				conexao=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
