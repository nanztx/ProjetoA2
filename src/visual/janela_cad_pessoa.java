package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class janela_cad_pessoa {

	private JFrame frmCadastroDeClientes;
	private JTextField txt_cpf;
	private JTextField txt_nome;
	JDateChooser data_nascimento = new JDateChooser();
	JDateChooser data_cadastro = new JDateChooser();
	private JTable tabela2;
	private JTextField txt_email;
	private JTextField txt_celular;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cad_pessoa window = new janela_cad_pessoa();
					window.frmCadastroDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janela_cad_pessoa() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeClientes = new JFrame();
		frmCadastroDeClientes.setTitle("CADASTRO DE CLIENTES");
		frmCadastroDeClientes.setBounds(100, 100, 588, 408);
		frmCadastroDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeClientes.getContentPane().setLayout(null);
		
		JLabel lblSetor = new JLabel("CPF:");
		lblSetor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor.setBounds(10, 11, 90, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor);
		
		JLabel lblSetor_1 = new JLabel("Nome:");
		lblSetor_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor_1.setBounds(10, 57, 90, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor_1);
		
		JLabel lblSetor_2 = new JLabel("Data de Nacimento:");
		lblSetor_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor_2.setBounds(10, 111, 145, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor_2);
		
		
		data_nascimento.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		data_nascimento.setBounds(179, 111, 108, 20);
		frmCadastroDeClientes.getContentPane().add(data_nascimento);
		
		txt_cpf = new JTextField();
		txt_cpf.setBounds(110, 18, 145, 20);
		frmCadastroDeClientes.getContentPane().add(txt_cpf);
		txt_cpf.setColumns(10);
		
		txt_nome = new JTextField();
		txt_nome.setColumns(10);
		txt_nome.setBounds(110, 64, 177, 20);
		frmCadastroDeClientes.getContentPane().add(txt_nome);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(240, 335, 89, 23);
		frmCadastroDeClientes.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 54, 244, 250);
		frmCadastroDeClientes.getContentPane().add(scrollPane);
		
		tabela2 = new JTable();
		scrollPane.setViewportView(tabela2);
		
		JLabel lblSetor_2_1 = new JLabel("Data de Cadastro:");
		lblSetor_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor_2_1.setBounds(10, 183, 145, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor_2_1);
		
		JLabel lblSetor_2_2 = new JLabel("Email:");
		lblSetor_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor_2_2.setBounds(10, 246, 66, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor_2_2);
		
		JLabel lblSetor_2_3 = new JLabel("Celular:");
		lblSetor_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSetor_2_3.setBounds(10, 294, 66, 31);
		frmCadastroDeClientes.getContentPane().add(lblSetor_2_3);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(86, 253, 201, 20);
		frmCadastroDeClientes.getContentPane().add(txt_email);
		
		txt_celular = new JTextField();
		txt_celular.setColumns(10);
		txt_celular.setBounds(94, 301, 145, 20);
		frmCadastroDeClientes.getContentPane().add(txt_celular);
		
		
		data_cadastro.setBounds(165, 183, 108, 20);
		frmCadastroDeClientes.getContentPane().add(data_cadastro);
	}
	
	private void inserir()
	{		
		
		String nome= txt_nome.getText();
		double cpf = Double.parseDouble(txt_cpf.getText());
		java.sql.Date nascimento = new java.sql.Date(data_nascimento.getDate().getTime());
		java.sql.Date cadastro = new java.sql.Date(data_cadastro.getDate().getTime());
		String email= txt_email.getText();
		double celular = Double.parseDouble(txt_celular.getText());
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO clientes (nome,cpf,data_nascimento,data_cadastro,email,celular) VALUES (?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1,nome);
			comando.setDouble(2, cpf);
			comando.setDate(3, nascimento);
			comando.setDate(4, cadastro);
			comando.setString(5,email);
			comando.setDouble(6, celular);
			
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					JOptionPane.showMessageDialog(null, "Cliente Cadastrado  Com SUCESSO ;)" + resultado.getInt(1));
					carregar_tabela();
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
	
	public void carregar_tabela()
	{


		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT * FROM clientes";
			resultado = comando.executeQuery(meu_sql);
			tabela2.setModel(DbUtils.resultSetToTableModel(resultado));
			
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
