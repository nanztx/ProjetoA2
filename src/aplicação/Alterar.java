package aplicação;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Banco_dados.ClasseConexao;

public class Alterar {

	public static void main(String[] args) {
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "UPDATE clientes  SET celular=? WHERE cpf=?";
			comando = conexao.prepareStatement(sql);
			comando.setDouble(1, 2000);
			comando.setInt(2,899966601);
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				System.out.println("dados alterados");
				
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
