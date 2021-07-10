package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import Banco_dados.ClasseConexao;
import aplicação.Veiculos;
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
import javax.swing.ListSelectionModel;

import Agenda.Agenda;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_cadastro_agenda {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cadastro_agenda window = new janela_cadastro_agenda();
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
	public janela_cadastro_agenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Selecione uma linha antes de alterar");
					return;
				} else {						
					int codigo = (int) table.getValueAt(table.getSelectedRow(), 0);
					selecionaCarro(codigo);
					frame.dispose();
					Agenda.main(null);
					}
			}
		});

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFocusable(false);		
		table.setBounds(10, 69, 414, 170);
		frame.getContentPane().add(table);


		JButton btnNewButton = new JButton("Alugar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Selecione um veículo na tabela");
					return;
				} 
			}
		});
		btnNewButton.setBounds(335, 24, 89, 23);
		frame.getContentPane().add(btnNewButton);



		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton_1.setBounds(236, 24, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		

		JLabel lbl_informeVeiculo = new JLabel("ESCOLHA SEU VE\u00CDCULO");
		lbl_informeVeiculo.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_informeVeiculo.setBounds(10, 11, 184, 30);
		frame.getContentPane().add(lbl_informeVeiculo);
		listarVeiculos();

	}

	public void listarVeiculos() {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT `cod_veiculo`, `modelo`, `marca`,"+/* `tipo`,*/" `cor`,"+ /*`placa`,*/" `potencia`, `ano`, `diaria` FROM `veiculos` ";
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

	public void selecionaCarro(int codigoCarro) {
		int veiculoSelecionado = 0;
		veiculoSelecionado = codigoCarro;
		Veiculos.veiculoAgenda = veiculoSelecionado;
	}

}
