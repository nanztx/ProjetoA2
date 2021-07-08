package visual;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class janela_altera_veiculo {

	private JFrame frmRentIt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_altera_veiculo window = new janela_altera_veiculo();
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
	public janela_altera_veiculo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT - Cadastro de Ve\u00EDculos");
		frmRentIt.setBounds(100, 100, 800, 345);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.getContentPane().setLayout(null);

		JLabel lbl_veiculos = new JLabel("CADASTRO DE VE\u00CDCULOS");
		lbl_veiculos.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_veiculos.setBounds(10, 11, 184, 30);
		frmRentIt.getContentPane().add(lbl_veiculos);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton_1.setBounds(516, 24, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton_1);

		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int novoCodigo 	= 0;
				String novoModelo	= "";
				String novoMarca	= "";
				String novoTipo		= "";
				String novoCor		= "";
				String novoPlaca	= "";
				String novoPotencia	= "";
				int novoAno		= 0;
				String novoDiaria = "";

				int j = 0;	//contador de colunas
				boolean valida = false;

				table.getModel();
				while (valida == false) {
					if (table.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, "Selecione uma linha antes de alterar");
						return;
					} else {
						while( j < table.getColumnCount()) {	//Varredura das Colunas da linha selecionada

							Object valorSetado= table.getValueAt(table.getSelectedRow(), j);
							int linhaCorrente = table.getSelectedRow();
							int colunaCorrente = j;

							table.setValueAt(valorSetado,linhaCorrente,colunaCorrente);

							if(j == 0) {
								novoCodigo = (int) table.getValueAt(linhaCorrente,colunaCorrente);	
							}
							else if(j == 1) {
								novoModelo = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 2){
								novoMarca = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 3){
								novoTipo = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 4){
								novoCor = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 5){
								novoPlaca = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 6){
								novoPotencia = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else if (j == 7){
								novoAno = (int) table.getValueAt(linhaCorrente,colunaCorrente);
							}
							else {
								novoDiaria = (String) table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							j++;
						}

						alteraVeiculos(novoModelo, novoMarca, novoTipo, novoCor,novoPlaca, novoPotencia, novoAno, novoDiaria, novoCodigo);
						valida = true;
						
					}
				}

			}
		});
		btnNewButton.setBounds(685, 24, 89, 23);
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
		/*public boolean isCellEditable() {
			boolean ret = true;
			if(table.getSelectedColumn()==0) {
				ret= false;
			}
			return ret;
		}*/
	}

	public void alteraVeiculos(String Modelo, String Marca, String Tipo, String Cor,String Placa, String Potencia, int Ano, String Diaria, int Codigo) {

		int novoCodigo		= Codigo;
		String novoModelo	= Modelo;
		String novoMarca	= Marca;
		String novoTipo		= Tipo;
		String novoCor		= Cor;
		String novoPlaca	= Placa;
		String novoPotencia	= Potencia;
		int novoAno		= Ano;
		String novoDiaria = "";
		novoDiaria = Diaria;


		novoDiaria = novoDiaria.replace("R$", "");
		novoDiaria = novoDiaria.replace(",", ".");
		//novoDiaria = novoDiaria.substring(1, novoDiaria.length());
		BigDecimal DiariaOk = new BigDecimal(novoDiaria);

		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ClasseConexao.Conectar();
			String sql = "UPDATE `veiculos` SET `modelo`=?,`marca`=?,`tipo`=?,`cor`=?,`placa`=?,`potencia`=?,`ano`=?,`diaria`=? WHERE `cod_veiculo`=?";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, novoModelo);
			comando.setString(2, novoMarca);
			comando.setString(3, novoTipo);
			comando.setString(4, novoCor);
			comando.setString(5, novoPlaca);
			comando.setString(6, novoPotencia);
			comando.setInt(7, novoAno);
			comando.setBigDecimal(8, DiariaOk);
			comando.setInt(9, novoCodigo);

			// Vamos executar o comando verificar se deu certo:
			if (comando.executeUpdate() > 0) {
				JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
				ResultSet resultado = comando.getGeneratedKeys();
			}

		} catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "O veículo "+novoCodigo+"já existe");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();

			}  catch(SQLException e) {
				e.printStackTrace();
			}
		}

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
