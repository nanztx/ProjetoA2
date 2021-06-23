package aplicação;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Banco_dados.ClasseConexao;

public class Listar {

	public static void main(String[] args) {

		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT * FROM carros";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();
				resultado.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
