package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Banco_dados.ClasseConexao;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class janela_login {

	private JFrame frmRentIt;
	private JTextField txt_usuario;
	private JTextField txt_senha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_login window = new janela_login();
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
	public janela_login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT");
		frmRentIt.setBounds(100, 100, 450, 300);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);
		
		txt_usuario = new JTextField();
		txt_usuario.setText("Usu\u00E1rio");
		txt_usuario.setBounds(161, 112, 98, 20);
		frmRentIt.getContentPane().add(txt_usuario);
		txt_usuario.setColumns(10);
		
		txt_senha = new JTextField();
		txt_senha.setText("Senha");
		txt_senha.setBounds(161, 143, 98, 20);
		frmRentIt.getContentPane().add(txt_senha);
		txt_senha.setColumns(10);
		
		JButton btn_login = new JButton("Login");
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ação do Login
				boolean logou = login();
				if (logou == true){
					janela_home janela_home = new janela_home();
					janela_home.main(null);
				}
			}
		});
		btn_login.setBounds(161, 174, 101, 23);
		frmRentIt.getContentPane().add(btn_login);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio:");
		lbl_usuario.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuario.setBounds(105, 112, 56, 22);
		frmRentIt.getContentPane().add(lbl_usuario);
		
		JLabel lbl_senha = new JLabel("Senha:");
		lbl_senha.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_senha.setBounds(115, 144, 41, 19);
		frmRentIt.getContentPane().add(lbl_senha);
	}
	
	private boolean login() {
		
		String nome 	= txt_usuario.getText();
		String senha 	= txt_senha.getText();
				
			Connection conexao =   null;
			ResultSet  resultado = null;
			PreparedStatement  comando  =  null;
			boolean autentica = false;
			try {
				
				conexao = ClasseConexao.Conectar();
				String meu_sql ="SELECT * FROM usuarios WHERE nome = ? AND senha = ? ";
				comando = conexao.prepareStatement(meu_sql, Statement.RETURN_GENERATED_KEYS);
				comando.setString(1, nome);
				comando.setString(2, senha);
						
				resultado = comando.executeQuery();
				
				if (resultado.next() == true) {
					//System.out.println(resultado.getInt("codigo")+ "  "+ resultado.getString("nome")+"  "+ resultado.getString("senha"));
					JOptionPane.showMessageDialog(null, "Bem Vindo " + resultado.getString(2) + "!");
					autentica = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Login não autenticado!");
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
			return autentica;
	}

}
