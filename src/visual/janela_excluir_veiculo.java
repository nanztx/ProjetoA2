package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_excluir_veiculo {

	private JFrame frmRentIt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_excluir_veiculo window = new janela_excluir_veiculo();
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
	public janela_excluir_veiculo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT - Exclus\u00E3o de Ve\u00EDculos");
		frmRentIt.setBounds(100, 100, 800, 345);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_altera_veiculo.main(null);
			}
		});
		btnNewButton_1.setBounds(516, 25, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton_1);
		
		JLabel lbl_veiculos = new JLabel("EXCLUIR VE\u00CDCULOS");
		lbl_veiculos.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_veiculos.setBounds(10, 11, 184, 30);
		frmRentIt.getContentPane().add(lbl_veiculos);
		
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.setBounds(685, 25, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 764, 207);
		frmRentIt.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override			//BLOQUEAR EDIÇÃO DE COLUNA CÓDIGO VALIOSÍSSIMO
			public void mouseClicked(MouseEvent e) {
				int columnIndex= 0;
				columnIndex = table.getSelectedColumn ();
		        //System.out.println ( "Double click on jtable" );
		        if ( columnIndex == 0) {
		            JOptionPane.showMessageDialog ( null , "Não é permitido alterar a coluna COD_VEICULO!" , "ALERTA Não é Permitida Edição Deste Campo" , JOptionPane.ERROR_MESSAGE );
		        }
			}
		});
		scrollPane.setViewportView(table);
		listarVeiculos();
	}

	public void listarVeiculos() {

		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT `cod_veiculo`, `modelo`, `marca`, `tipo`, `cor`, `placa`, `potencia`, `ano`, `diaria` FROM `veiculos` ";
			resultado = comando.executeQuery(meu_sql);
			table.setModel(DbUtils.resultSetToTableModel(resultado));

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
	
}
