package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;
import Banco_dados.ClasseConexao;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_excluir {

	private JFrame frmExcluirVeiculo;
	private JTextField txt_codigo;
	private JTable tabela1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_excluir window = new janela_excluir();
					window.frmExcluirVeiculo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janela_excluir() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExcluirVeiculo = new JFrame();
		frmExcluirVeiculo.setFont(new Font("Dialog", Font.BOLD, 15));
		frmExcluirVeiculo.setTitle("EXCLUIR VEICULO");
		frmExcluirVeiculo.setBounds(100, 100, 450, 300);
		frmExcluirVeiculo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExcluirVeiculo.getContentPane().setLayout(null);
		
		JLabel lblCodVeiculo = new JLabel("Cod. Veiculo:");
		lblCodVeiculo.setBounds(10, 25, 118, 34);
		lblCodVeiculo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		frmExcluirVeiculo.getContentPane().add(lblCodVeiculo);
		
		txt_codigo = new JTextField();
		txt_codigo.setBounds(138, 33, 86, 20);
		frmExcluirVeiculo.getContentPane().add(txt_codigo);
		txt_codigo.setColumns(10);
		
		JButton btnNewButton = new JButton("EXCLUIR VEICULO");
		btnNewButton.setBounds(50, 85, 121, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Excluir();
			
			}
		});
		frmExcluirVeiculo.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(224, 25, 200, 225);
		frmExcluirVeiculo.getContentPane().add(scrollPane);
		
		tabela1 = new JTable();
		scrollPane.setViewportView(tabela1);
	}
	
	private void Excluir()
	{
		
		int codigo = Integer.parseInt(txt_codigo.getText());
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "DELETE FROM carros  WHERE cod_carro=?";
			comando = conexao.prepareStatement(sql);
			
			comando.setInt(1,codigo); // O valor da primeira interrogação é = codigo
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				JOptionPane.showMessageDialog(null, "VEICULO EXCLUIDO COM SUCESSO");
				carregar_tabela();
				
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
	
	public void carregar_tabela()
	{


		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT * FROM carros";
			resultado = comando.executeQuery(meu_sql);
			tabela1.setModel(DbUtils.resultSetToTableModel(resultado));
			
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
