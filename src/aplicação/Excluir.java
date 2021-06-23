package aplicação;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Banco_dados.ClasseConexao;

public class Excluir {

	public static void main(String[] args) {
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "DELETE FROM carros  WHERE cod_carro=?";
			comando = conexao.prepareStatement(sql);
			
			comando.setInt(1,5); // O valor da primeira interrogação é = 5
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				System.out.println("dados excluidos");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
