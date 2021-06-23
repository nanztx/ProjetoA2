package aplicação;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Banco_dados.ClasseConexao;

public class Inserir {

	public static void main(String[] args) {
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO carros (tipo,modelo,cor,placa,potencia,ano,diaria) VALUES (?,?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, "tipo");
			comando.setString(2, "modelo");
			comando.setString(3, "cor");
					
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					System.out.println("dados gravados no codigo:" + resultado.getInt(1));
				}
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
