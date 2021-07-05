package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class janela_altera_usuario {

	private JFrame frmRentIt;
	private JTable table;
	private JTextField txt_codigo;
	private JTextField txt_usuario;
	private JTextField txt_permissao;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_altera_usuario window = new janela_altera_usuario();
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
	public janela_altera_usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			
		frmRentIt = new JFrame();
		frmRentIt.setTitle("Rent It - Alterar Usuarios");
		frmRentIt.setBounds(100, 100, 448, 378);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);
		
		JLabel lbl_usuarios = new JLabel("CADASTRO DE USU\u00C1RIO");
		lbl_usuarios.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuarios.setBounds(10, 11, 139, 18);
		frmRentIt.getContentPane().add(lbl_usuarios);
		
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ATUALIZA EM TELA
				table.getModel();
				table.setValueAt(txt_codigo.getText(), table.getSelectedRow(), 0);
				table.setValueAt(txt_usuario.getText(), table.getSelectedRow(), 1);
				table.setValueAt(txt_permissao.getText(), table.getSelectedRow(), 2);
				
				String novoCodigo	=txt_codigo.getText();
				String novoUsuario	=txt_usuario.getText();
				String novoPermissao=txt_permissao.getText();
				
				alteraUsuarios(novoUsuario,novoPermissao,novoCodigo);
				//LIMPA
			}
		});
		btnNewButton.setBounds(335, 18, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton_1.setBounds(236, 18, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 414, 198);
		frmRentIt.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		txt_codigo = new JTextField();
		txt_codigo.setBounds(10, 284, 86, 20);
		frmRentIt.getContentPane().add(txt_codigo);
		txt_codigo.setColumns(10);
		
		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);
		txt_usuario.setBounds(114, 284, 86, 20);
		frmRentIt.getContentPane().add(txt_usuario);
		
		txt_permissao = new JTextField();
		txt_permissao.setColumns(10);
		txt_permissao.setBounds(222, 284, 86, 20);
		frmRentIt.getContentPane().add(txt_permissao);
		
		JLabel lbl_codigo = new JLabel("C\u00F3digo:");
		lbl_codigo.setBounds(10, 259, 46, 14);
		frmRentIt.getContentPane().add(lbl_codigo);
		
		JLabel lbl_usuario_1 = new JLabel("Usu\u00E1rio:");
		lbl_usuario_1.setBounds(114, 261, 46, 14);
		frmRentIt.getContentPane().add(lbl_usuario_1);
		
		JLabel lbl_permissao = new JLabel("Permiss\u00E3o:");
		lbl_permissao.setBounds(220, 261, 73, 14);
		frmRentIt.getContentPane().add(lbl_permissao);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(335, 284, 89, 20);
		frmRentIt.getContentPane().add(passwordField);
		
		JLabel lbl_permissao_1 = new JLabel("Senha:");
		lbl_permissao_1.setBounds(335, 261, 73, 14);
		frmRentIt.getContentPane().add(lbl_permissao_1);
		listarUsuarios();
		
	}
	
public void listarUsuarios() {
		
		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT `codigo`, `usuario`, `permissao` FROM `usuarios`";
			resultado = comando.executeQuery(meu_sql);
			table.setModel(DbUtils.resultSetToTableModel(resultado));
			
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

public void alteraUsuarios(String usuario, String permissao, String codigo) {
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "UPDATE `usuarios` SET `usuario`=?,`permissao`=? WHERE `codigo`=?";
			comando = conexao.prepareStatement(sql);
			comando.setString(1, usuario);
			comando.setString(2, permissao);
			comando.setString(3, codigo);
						
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
					//frame.dispose();
					//janela_home_admin.main(null);
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
