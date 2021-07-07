package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JOptionPane;

import Banco_dados.ClasseConexao;

public class UsuarioDAO {
	public void login(String nome, String senha) {

			Connection conexao =   null;
			ResultSet  resultado = null;
			PreparedStatement  comando  =  null;
			
			
			try {
				
				conexao = ClasseConexao.Conectar();
				String meu_sql ="SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
				comando = conexao.prepareStatement(meu_sql, Statement.RETURN_GENERATED_KEYS);
				comando.setString(1, "nome");
				comando.setString(2, "senha");
						
				resultado = comando.executeQuery();
				
				while(resultado.next()) {
					System.out.println(resultado.getInt("codigo")+ "  "+ resultado.getString("nome")+"  "+ resultado.getString("senha"));
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
	
	public int getNextCodigo() {
		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		int nextCodigo=0;
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT * FROM carros";
			resultado = comando.executeQuery(meu_sql);
			if(resultado != null) {
				nextCodigo = (int) resultado.getObject(1);
				nextCodigo++;
		}
			else{
				nextCodigo = 0;
				
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
		return nextCodigo;
	}

	public static String alteraSenhaDAO(int i , String j) {
		
		int idRecebida	= 0;
		String senhaResetada = "";
		
		idRecebida = i;
		senhaResetada = j;
		
		
		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ClasseConexao.Conectar();
			String sql = "UPDATE `usuarios` SET `senha`=? WHERE `codigo`=?";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, senhaResetada);
			comando.setInt(2, idRecebida);

			// Vamos executar o comando verificar se deu certo:
			if (comando.executeUpdate() > 0) {
				
				JOptionPane.showMessageDialog(null, "Senha do usuario "+ idRecebida +" alterada para: \n"+ senhaResetada);
				
				ResultSet resultado = comando.getGeneratedKeys();
				if (resultado.next()) {
					//JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
					// frame.dispose();
					// janela_home_admin.main(null);
				}
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();

			}  catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return senhaResetada;
}
}
