package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
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

import java.util.Random;

public class janela_cadastro_cliente {

	private JFrame frame;
	private JTextField txt_nome;
	private JTextField txt_cpf;
	private JTextField txt_dtnascimento;
	private JTextField txt_dtcadastro;
	private JTextField txt_email;
	private JTextField txt_celular;
	private JTextField txt_codusuario;
	private JTextField txt_usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cadastro_cliente window = new janela_cadastro_cliente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janela_cadastro_cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 565, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_cadastroUsuario = new JLabel("CADASTRO DE USU\u00C1RIO");
		lbl_cadastroUsuario.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_cadastroUsuario.setBounds(10, 11, 194, 24);
		frame.getContentPane().add(lbl_cadastroUsuario);
		
		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_nome.setBounds(10, 104, 47, 24);
		frame.getContentPane().add(lbl_nome);
		
		JLabel lbl_cpf = new JLabel("CPF:");
		lbl_cpf.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_cpf.setBounds(10, 139, 47, 24);
		frame.getContentPane().add(lbl_cpf);
		
		JLabel lbl_dtnascmento = new JLabel("Data Nascimento:");
		lbl_dtnascmento.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_dtnascmento.setBounds(10, 173, 109, 24);
		frame.getContentPane().add(lbl_dtnascmento);
		
		JLabel lbl_dtcadastro = new JLabel("Data Cadastro:");
		lbl_dtcadastro.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_dtcadastro.setBounds(10, 208, 98, 24);
		frame.getContentPane().add(lbl_dtcadastro);
		
		JLabel lbl_email = new JLabel("Email:");
		lbl_email.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_email.setBounds(316, 74, 47, 24);
		frame.getContentPane().add(lbl_email);
		
		JLabel lbl_celular = new JLabel("Celular:");
		lbl_celular.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_celular.setBounds(316, 104, 47, 24);
		frame.getContentPane().add(lbl_celular);
		
		JLabel lbl_codusuario = new JLabel("C\u00F3digo de Usu\u00E1rio:");
		lbl_codusuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_codusuario.setBounds(316, 139, 109, 24);
		frame.getContentPane().add(lbl_codusuario);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(57, 104, 234, 20);
		frame.getContentPane().add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_cpf = new JTextField();
		txt_cpf.setColumns(10);
		txt_cpf.setBounds(57, 139, 158, 20);
		frame.getContentPane().add(txt_cpf);
		
		txt_dtnascimento = new JTextField();
		txt_dtnascimento.setColumns(10);
		txt_dtnascimento.setBounds(129, 173, 86, 20);
		frame.getContentPane().add(txt_dtnascimento);
		
		txt_dtcadastro = new JTextField();
		txt_dtcadastro.setToolTipText("");
		txt_dtcadastro.setText("\t");
		txt_dtcadastro.setColumns(10);
		txt_dtcadastro.setBounds(129, 208, 86, 20);
		frame.getContentPane().add(txt_dtcadastro);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(373, 74, 148, 20);
		frame.getContentPane().add(txt_email);
		
		txt_celular = new JTextField();
		txt_celular.setColumns(10);
		txt_celular.setBounds(373, 104, 148, 20);
		frame.getContentPane().add(txt_celular);
		
		txt_codusuario = new JTextField();
		txt_codusuario.setColumns(10);
		txt_codusuario.setBounds(435, 139, 86, 20);
		frame.getContentPane().add(txt_codusuario);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.setBounds(126, 263, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
				
			}
		});
		btnNewButton_1.setBounds(373, 263, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio");
		lbl_usuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_usuario.setBounds(10, 69, 47, 24);
		frame.getContentPane().add(lbl_usuario);
		
		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);
		txt_usuario.setBounds(57, 69, 158, 20);
		frame.getContentPane().add(txt_usuario);
	}
	
	private void cadastrar() {
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		//INSERT NA TABELA DE USUARIOS
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, txt_usuario.getText());
			comando.setString(2, generateRandomPassword(12));	
					
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					//System.out.println("dados gravados no codigo:" + resultado.getInt(1));
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
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
		//INSERT NA TABELA DE CLIENTES
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO clientes (nome,cpf,data_nascimento,data_cadastro,email,celular,cod_usuario) VALUES (?,?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, txt_nome.getText());
			comando.setString(2, txt_cpf.getText());
			comando.setString(3, txt_dtnascimento.getText());
			comando.setString(4, txt_dtcadastro.getText());
			comando.setString(5, txt_email.getText());
			comando.setString(6, txt_celular.getText());	
			comando.setString(7, txt_codusuario.getText());
			
					
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
	
	public static String generateRandomPassword(int len) {
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
	          +"jklmnopqrstuvwxyz!@#$%&";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			return sb.toString();
		}
}