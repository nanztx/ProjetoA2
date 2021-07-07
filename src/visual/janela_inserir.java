package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class janela_inserir {

	private JFrame frmInserirVeiculo;
	private JTextField txt_tipo;
	private JTextField txt_modelo;
	private JTextField txt_cor;
	private JTextField txt_potencia;
	private JTextField txt_ano;
	private JTextField txt_diaria;
	private JTable tabela1;
	private JLabel lblPlaca;
	private JTextField txt_placa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_inserir window = new janela_inserir();
					window.frmInserirVeiculo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public janela_inserir() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInserirVeiculo = new JFrame();
		frmInserirVeiculo.setFont(new Font("Dialog", Font.ITALIC, 16));
		frmInserirVeiculo.setTitle("Inserir Veiculo");
		frmInserirVeiculo.setBounds(100, 100, 450, 407);
		frmInserirVeiculo.setLocationRelativeTo(null);
		frmInserirVeiculo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInserirVeiculo.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("modelo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setBounds(10, 64, 68, 34);
		frmInserirVeiculo.getContentPane().add(lblNewLabel_1);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTipo.setBounds(10, 22, 68, 34);
		frmInserirVeiculo.getContentPane().add(lblTipo);
		
		JLabel lblCor = new JLabel("Cor:");
		lblCor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCor.setBounds(10, 102, 51, 34);
		frmInserirVeiculo.getContentPane().add(lblCor);
		
		JLabel lblPotencia = new JLabel("Potencia:");
		lblPotencia.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPotencia.setBounds(10, 192, 68, 34);
		frmInserirVeiculo.getContentPane().add(lblPotencia);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblAno.setBounds(10, 237, 68, 34);
		frmInserirVeiculo.getContentPane().add(lblAno);
		
		JLabel lblValorDaDiria = new JLabel("Valor da di\u00E1ria:");
		lblValorDaDiria.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblValorDaDiria.setBounds(10, 274, 111, 34);
		frmInserirVeiculo.getContentPane().add(lblValorDaDiria);
		
		txt_tipo = new JTextField();
		txt_tipo.setBounds(106, 30, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_tipo);
		txt_tipo.setColumns(10);
		
		txt_modelo = new JTextField();
		txt_modelo.setColumns(10);
		txt_modelo.setBounds(106, 72, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_modelo);
		
		txt_cor = new JTextField();
		txt_cor.setColumns(10);
		txt_cor.setBounds(106, 110, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_cor);
		
		txt_potencia = new JTextField();
		txt_potencia.setColumns(10);
		txt_potencia.setBounds(106, 200, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_potencia);
		
		txt_ano = new JTextField();
		txt_ano.setColumns(10);
		txt_ano.setBounds(106, 245, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_ano);
		
		txt_diaria = new JTextField();
		txt_diaria.setColumns(10);
		txt_diaria.setBounds(128, 282, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_diaria);
		
		JButton btnNewButton = new JButton("INSERIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserir();
			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 14));
		btnNewButton.setBounds(190, 334, 89, 23);
		frmInserirVeiculo.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(247, 22, 177, 267);
		frmInserirVeiculo.getContentPane().add(scrollPane);
		
		tabela1 = new JTable();
		scrollPane.setViewportView(tabela1);
		
		lblPlaca = new JLabel("Placa:");
		lblPlaca.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblPlaca.setBounds(10, 147, 51, 34);
		frmInserirVeiculo.getContentPane().add(lblPlaca);
		
		txt_placa = new JTextField();
		txt_placa.setColumns(10);
		txt_placa.setBounds(106, 155, 86, 20);
		frmInserirVeiculo.getContentPane().add(txt_placa);
	}
	
	private void inserir()
	{		
		String tipo= txt_tipo.getText();
		String modelo = txt_modelo.getText();
		String cor = txt_cor.getText();
		String placa= txt_placa.getText();
		double potencia = Double.parseDouble(txt_potencia.getText());
		double ano =  Double.parseDouble(txt_ano.getText());
		double diaria= Double.parseDouble(txt_diaria.getText());
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO carros (tipo,modelo,cor,placa,potencia,ano,diaria) VALUES (?,?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, tipo);
			comando.setString(2, modelo);
			comando.setString(3, cor);
			comando.setString(4, placa);
			comando.setDouble(5, potencia);
			comando.setDouble(6, ano);
			comando.setDouble(7, diaria);
			
			
			// Vamos executar o comando 
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					JOptionPane.showMessageDialog(null, "Veiculo Cadastrado  Com SUCESSO ;)" + resultado.getInt(1));
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
