package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import java.awt.Choice;
import com.toedter.calendar.JDateChooser;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import java.sql.Date;

public class janela_agenda {

	private JFrame frame;
	private JTable table;
	private JComboBox<String> veiculosBox;
	private String listarModelo[] = new String[100];
	private String listarCodigo[] = new String[100];
	JDateChooser dateChooser_inicio = new JDateChooser();
	JDateChooser dateChooser_final = new JDateChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_agenda window = new janela_agenda();
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
	public janela_agenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\rauan\\Desktop\\esse.jpg"));
		frame.setTitle("Agenda de Carros");
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 795, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDataIncioDa = new JLabel("Data In\u00EDcio da Loca\u00E7\u00E3o:");
		lblDataIncioDa.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDataIncioDa.setBounds(10, 311, 143, 25);
		frame.getContentPane().add(lblDataIncioDa);

		JLabel lblDataFinalDa = new JLabel("Data Final da Loca\u00E7\u00E3o:");
		lblDataFinalDa.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDataFinalDa.setBounds(10, 342, 143, 20);
		frame.getContentPane().add(lblDataFinalDa);

		JDateChooser dateChooser_inicio = new JDateChooser();
		dateChooser_inicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				java.util.Date teste = dateChooser_inicio.getDate();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date ="";
				//dateChooser_inicio.setDate();
				date = sdf.format(dateChooser_inicio.getDate());
			}
		});
		dateChooser_inicio.setBounds(163, 316, 143, 20);
		frame.getContentPane().add(dateChooser_inicio);

		

		JDateChooser dateChooser_final = new JDateChooser();
		dateChooser_final.setBounds(163, 342, 143, 20);
		frame.getContentPane().add(dateChooser_final);

		JButton btnNewButton = new JButton("Agendar");
		btnNewButton.setBounds(613, 31, 111, 23);
		frame.getContentPane().add(btnNewButton);

		table = new JTable();
		table.setBounds(414, 116, 1, 1);
		frame.getContentPane().add(table);

		JLabel lbl_veiculos = new JLabel("RESERVA DE AGENDAS");
		lbl_veiculos.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_veiculos.setBounds(10, 15, 184, 30);
		frame.getContentPane().add(lbl_veiculos);

		
		listarVeiculos();
		int cont= countVeiculos();
		veiculosBox = new JComboBox<>();
		veiculosBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String carro ="";
				int codigo=0;
				carro = listarCodigo[veiculosBox.getSelectedIndex()];
				codigo = Integer. valueOf(carro);
				
				
				listaVeiculoAgenda(codigo);
			}
		});
		if(cont>0) {
			for(int i=0;i<cont;i++)	{
				veiculosBox.addItem(listarModelo[i]);
				listarCodigo[i] = listarCodigo[i] ;
			}
			veiculosBox.setSelectedItem(null);
		}
		veiculosBox.action(null, veiculosBox);
		veiculosBox.setBounds(10, 56, 128, 22);
		frame.getContentPane().add(veiculosBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 116, 622, 148);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override			//BLOQUEAR EDIÇÃO DE COLUNA CÓDIGO VALIOSÍSSIMO
			public void mouseClicked(MouseEvent e) {
				int columnIndex= 0;
				columnIndex = table.getSelectedColumn ();
		        //System.out.println ( "Double click on jtable" );
		        if ( columnIndex == 0) {
		            JOptionPane.showMessageDialog ( null , "Não é permitido alterar a coluna CODIGO!" , "ALERTA Não é Permitida Edição Deste Campo" , JOptionPane.ERROR_MESSAGE );
		        }
			}
		});
		scrollPane.setViewportView(table);

		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				janela_home_admin.main(null);
				
			}
		});
		btn_cancelar.setBounds(451, 31, 89, 23);
		frame.getContentPane().add(btn_cancelar);
		
		
	}

	public void listarVeiculos() {

		int cont=0;

		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT `cod_veiculo`, `modelo`, `marca`, `tipo`, `cor`, `placa`, `potencia`, `ano`, `diaria` FROM `veiculos` ";
			resultado = comando.executeQuery(meu_sql);

			while(resultado.next()) {
			listarModelo[cont] = resultado.getString("modelo");// +"-"+ resultado.getString("marca");
			listarCodigo[cont] = resultado.getString("cod_veiculo");
			cont++;
			}

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

	public int countVeiculos() {


		String count ="";
		int contVeiculos=0;
		Connection conexao = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			//comando = conexao.createStatement();
			String meu_sql = "SELECT * FROM `veiculos` ";
			comando = conexao.prepareStatement(meu_sql);
			resultado = comando.executeQuery(meu_sql);

			while(resultado.next()) {
				contVeiculos++;
			}

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
		return contVeiculos;
	}
	
	public void listaVeiculoAgenda(int carro) {

		int carroRecebido =0;
		carroRecebido = carro;
		
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT `codigo`, `data_da_reserva`, `data_final`, `nome_cliente`, `cod_carro`, `marca`, `modelo`, `disponibilidade`, `codigo_pai` FROM `agenda` WHERE `cod_carro`="+carroRecebido+" ";
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
