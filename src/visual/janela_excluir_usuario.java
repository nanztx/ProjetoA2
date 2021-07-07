package visual;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

public class janela_excluir_usuario {

	private JFrame frmRentIt;
	private JTable tableUsuarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_excluir_usuario window = new janela_excluir_usuario();
					window.frmRentIt.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janela_excluir_usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("Rent It - Exclus\u00E3o de  Usu\u00E1rios");
		frmRentIt.setBounds(100, 100, 450, 300);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 412, 184);
		frmRentIt.getContentPane().add(scrollPane);

		tableUsuarios = new JTable();
		scrollPane.setViewportView(tableUsuarios);
		tableUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JLabel lbl_usuarios = new JLabel("EXCLUIR USU\u00C1RIO");
		lbl_usuarios.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuarios.setBounds(10, 11, 139, 18);
		frmRentIt.getContentPane().add(lbl_usuarios);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
				
			}
		});
		btn_cancelar.setBounds(236, 18, 89, 23);
		frmRentIt.getContentPane().add(btn_cancelar);

		JButton btn_excluir = new JButton("Excluir");
		btn_excluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo=0;
				
				codigo = Integer.parseInt(tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),0).toString());
								
				excluirUsuarios(codigo);
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btn_excluir.setBounds(335, 18, 89, 23);
		frmRentIt.getContentPane().add(btn_excluir);
		
		listarUsuariosExc();
	}


	public void listarUsuariosExc() {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT codigo, usuario, permissao FROM usuarios";
			resultado = comando.executeQuery(meu_sql);
			tableUsuarios.setModel(DbUtils.resultSetToTableModel(resultado));

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();
				resultado.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void excluirUsuarios(int i) {
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		int id=0;
		id=i;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "DELETE FROM usuarios  WHERE codigo=? and deleted <>'*'";
			comando = conexao.prepareStatement(sql);
			comando.setInt(1,id);
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				//System.out.println("Usuário "+id+" excluído");
				JOptionPane.showMessageDialog(null, "Usuário "+id+" excluído com sucesso");
				
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
