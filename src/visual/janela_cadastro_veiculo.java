package visual;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import Banco_dados.ClasseConexao;

public class janela_cadastro_veiculo {

	private JFrame frmRentIt;
	private JTextField txt_potencia;
	public int cont = 0;
	public String listarMarcas[] = new String[100];

	private JComboBox<String> coresBox;
	public String listarCores[] = new String[100];
	public String listarTipos[] = new String[100];
	public String listarModelos[] = new String[100];

	public JFormattedTextField txt_placa = null;

	public JFormattedTextField txt_valorDiaria = null;
	public String valorFormatado = "";
	private JTextField txt_ano_fabricacao;
	public int contamarca = 0;
	public int contamod = 0;
	public int contacor = 0;
	String marcaSelecionada = "";
	public JFormattedTextField txt_modelo = null;
	public JFormattedTextField txt_marca  = null;
	public JFormattedTextField txt_tipo   = null;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cadastro_veiculo window = new janela_cadastro_veiculo();
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
	public janela_cadastro_veiculo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT - Cadastro de Ve\u00EDculos");
		frmRentIt.setBounds(100, 100, 550, 330);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarVeiculo();
			}
		});
		btnCadastrar.setBounds(399, 227, 89, 23);
		frmRentIt.getContentPane().add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnCancelar.setBounds(282, 227, 89, 23);
		frmRentIt.getContentPane().add(btnCancelar);

		JLabel lbl_cadastroUsuario = new JLabel("CADASTRO DE VE\u00CDCULOS");
		lbl_cadastroUsuario.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_cadastroUsuario.setBounds(10, 11, 194, 24);
		frmRentIt.getContentPane().add(lbl_cadastroUsuario);

		JLabel lbl_modelo = new JLabel("Modelo:");
		lbl_modelo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_modelo.setBounds(10, 89, 47, 24);
		frmRentIt.getContentPane().add(lbl_modelo);

		JLabel lbl_marca = new JLabel("Marca:");
		lbl_marca.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_marca.setBounds(10, 54, 47, 24);
		frmRentIt.getContentPane().add(lbl_marca);

		JLabel lbl_tipo = new JLabel("Tipo:");
		lbl_tipo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_tipo.setBounds(10, 124, 47, 24);
		frmRentIt.getContentPane().add(lbl_tipo);

		JLabel lbl_cor = new JLabel("Cor:");
		lbl_cor.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_cor.setBounds(10, 159, 47, 24);
		frmRentIt.getContentPane().add(lbl_cor);

		JLabel lbl_placa = new JLabel("Placa:");
		lbl_placa.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_placa.setBounds(282, 54, 65, 24);
		frmRentIt.getContentPane().add(lbl_placa);

		JLabel lbl_potencia = new JLabel("Pot\u00EAncia (CV's):");
		lbl_potencia.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_potencia.setBounds(282, 89, 96, 24);
		frmRentIt.getContentPane().add(lbl_potencia);

		JLabel lbl_ano_fabricacao = new JLabel("Ano de Fabrica\u00E7\u00E3o:");
		lbl_ano_fabricacao.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_ano_fabricacao.setBounds(282, 124, 160, 24);
		frmRentIt.getContentPane().add(lbl_ano_fabricacao);

		JLabel lbl_valor_diaria = new JLabel("Valor de Di\u00E1ria:");
		lbl_valor_diaria.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_valor_diaria.setBounds(282, 159, 121, 24);
		frmRentIt.getContentPane().add(lbl_valor_diaria);

		txt_potencia = new JTextField();
		txt_potencia.setBounds(402, 89, 86, 20);
		frmRentIt.getContentPane().add(txt_potencia);
		txt_potencia.setColumns(10);

		/*
		 * //COMBO-BOX MARCAS listarMarcas(); if(cont>0) { //for(int i=0;i<cont;i++) {
		 * for(contamarca=0;contamarca<cont;contamarca++){
		 * marcaBox.addItem(listarMarcas[contamarca]); } marcaBox.setSelectedItem(null);
		 * } //NAO ENTRA NO ACTION LISTENER modelosBox.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * marcaSelecionada = marcaBox.getItemAt(marcaBox.getSelectedIndex());
		 * listarModelos(marcaSelecionada); } }); if(cont>0) { //for(int j=0;j<cont;j++)
		 * { for(contamod=0;contamod<cont;contamod++){
		 * 
		 * modelosBox.addItem(listarModelos[contamod]); }
		 * modelosBox.setSelectedItem(null); }
		 * 
		 * listarTipos(); if(cont>0) { for(int l=0;l<cont;l++) {
		 * tiposBox.addItem(listarTipos[l]); } tiposBox.setSelectedItem(null); }
		 * 
		 * */
		//COMBO-BOX CORES 
		listarCores(); 
		coresBox = new JComboBox<>(); if(cont>0) {
			//for(int k=0;k<cont;k++) { 
			for(contacor=0;contacor<cont;contacor++) {
				coresBox.addItem(listarCores[contacor]); } 
			coresBox.setSelectedItem(null); }
		coresBox.action(null, coresBox); 
		coresBox.setBounds(67, 158, 128, 22);
		frmRentIt.getContentPane().add(coresBox);


		try {
			txt_placa = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Placa Inválida!");
			e1.printStackTrace();
		}
		txt_placa.setBounds(328, 54, 160, 21);
		frmRentIt.getContentPane().add(txt_placa);

		JFormattedTextField txt_valorDiaria = new JFormattedTextField();
		txt_valorDiaria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				String valortexto = txt_valorDiaria.getText();
				valortexto = valortexto.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
				BigDecimal valor = new BigDecimal(valortexto);
				NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
				valorFormatado = nf.format(valor).toString();
				txt_valorDiaria.setText(valorFormatado);
				valorFormatado = txt_valorDiaria.getText();
			}
		});
		txt_valorDiaria.setBounds(399, 159, 89, 21);
		frmRentIt.getContentPane().add(txt_valorDiaria);

		txt_ano_fabricacao = new JTextField();
		txt_ano_fabricacao.setColumns(10);
		txt_ano_fabricacao.setBounds(415, 124, 73, 20);
		frmRentIt.getContentPane().add(txt_ano_fabricacao);
		
		
		txt_modelo = new JFormattedTextField();
		txt_modelo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String texto = "";
				texto = txt_modelo.getText().toUpperCase();
				txt_modelo.setText(texto);
			}
		});
		txt_modelo.setBounds(67, 54, 160, 21);
		frmRentIt.getContentPane().add(txt_modelo);
		
		
		txt_marca = new JFormattedTextField();
		txt_marca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String texto = "";
				texto = txt_marca.getText().toUpperCase();
				txt_marca.setText(texto);
				
			}
		});
		txt_marca.setBounds(67, 89, 160, 21);
		frmRentIt.getContentPane().add(txt_marca);
		
		
		txt_tipo = new JFormattedTextField();
		txt_tipo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String texto = "";
				texto = txt_marca.getText().toUpperCase();
				txt_marca.setText(texto);
			}
		});
		txt_tipo.setBounds(67, 124, 160, 21);
		frmRentIt.getContentPane().add(txt_tipo);

	}

	public void cadastrarVeiculo() {

		// int cod_marca=0;
		// int cod_modelo=0;

		int i = 0;
		valorFormatado = valorFormatado.replace("R$", "");
		valorFormatado = valorFormatado.replace(",", ".");
		valorFormatado = valorFormatado.substring(1, valorFormatado.length());
		Double valorDiaria = Double.parseDouble(valorFormatado);
		BigDecimal valor = new BigDecimal(valorDiaria);

		// cod_modelo = GetCodigoModelo(modelosBox.getSelectedItem().toString());
		// cod_marca = GetCodigoMarca(marcaBox.getSelectedItem().toString());

		Connection conexao = null;
		PreparedStatement comando = null;

		// INSERT NA TABELA DE VEÍCULOS
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO `veiculos`(`modelo`, `marca`, `tipo`, `cor`, `placa`, `potencia`, `ano`, `diaria`) VALUES (?,?,?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, txt_modelo.getText());
			comando.setString(2, txt_marca.getText());
			comando.setString(3, txt_tipo.getText());
			comando.setString(4, coresBox.getSelectedItem().toString());
			comando.setString(5, txt_placa.getText());
			comando.setString(6, txt_potencia.getText());
			int anoFab = Integer.parseInt(txt_ano_fabricacao.getText());
			comando.setInt(7, anoFab);
			comando.setBigDecimal(8, valor);

			// Vamos executar o comando verificar se deu certo:
			if (comando.executeUpdate() > 0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if (resultado.next()) {
					// System.out.println("dados gravados no codigo:" + resultado.getInt(1));
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					frmRentIt.dispose();
					janela_home_admin.main(null);
				}
			}

		} catch (MysqlDataTruncation e) {
			JOptionPane.showMessageDialog(null, "Informação ultrapassa o numero de dígitos disponibilizado no campo");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ClasseConexao.FecharConexao(conexao);
			try {
				comando.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int GetCodigoMarca(String marca) {

		Connection conexao = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;

		int codMarca = 0;
		String marcaRecebida = marca;

		try {

			conexao = ClasseConexao.Conectar();
			String meu_sql = "SELECT codigo FROM marcas WHERE `marca` = '" + marcaRecebida + "' ";
			comando = conexao.prepareStatement(meu_sql);

			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				codMarca = resultado.getInt("codigo");
				// JOptionPane.showMessageDialog(null, "Pegou a marca!");
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
		return codMarca;

	}

	public int GetCodigoModelo(String modelo) {

		Connection conexao = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;

		int codModelo = 0;
		String modeloRecebido = modelo.trim();

		try {

			conexao = ClasseConexao.Conectar();
			String meu_sql = "SELECT codigo FROM modelos WHERE `modelo` = '" + modeloRecebido + "' ";
			comando = conexao.prepareStatement(meu_sql);
			// comando.setString(1, modeloRecebido);

			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				codModelo = resultado.getInt("codigo");
				// JOptionPane.showMessageDialog(null, "Pegou o modelo!");
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
		return codModelo;
	}

	public void listarMarcas() {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;
		cont = 0;

		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT marca FROM `marcas`";
			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				// System.out.println(resultado.getInt("cod_carro")+ " "+
				// resultado.getString("tipo")+" "+ resultado.getString("modelo")+ " "+
				// resultado.getString("cor")+ " "+resultado.getDouble("potencia")+" "+
				// resultado.getDouble("ano")+ " "+ resultado.getDouble("diaria"));
				try {
					listarMarcas[cont] = resultado.getString("marca");
					cont++;
				} catch (Exception ex) {
					ex.printStackTrace();
				}

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

	public void listarTipos() {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;
		cont = 0;

		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT DISTINCT tipo_descricao FROM `tipos`";
			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				// System.out.println(resultado.getInt("cod_carro")+ " "+
				// resultado.getString("tipo")+" "+ resultado.getString("modelo")+ " "+
				// resultado.getString("cor")+ " "+resultado.getDouble("potencia")+" "+
				// resultado.getDouble("ano")+ " "+ resultado.getDouble("diaria"));
				try {
					listarTipos[cont] = resultado.getString("tipo_descricao");
					cont++;
				} catch (Exception ex) {
					ex.printStackTrace();
				}

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

	public void listarModelos(String marca) {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;
		cont = 0;
		String marcaRecebida = "";
		marcaRecebida = marca;

		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT DISTINCT modelo FROM `modelos` WHERE `marca`='" + marcaRecebida + "'";
			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				// System.out.println(resultado.getInt("cod_carro")+ " "+
				// resultado.getString("tipo")+" "+ resultado.getString("modelo")+ " "+
				// resultado.getString("cor")+ " "+resultado.getDouble("potencia")+" "+
				// resultado.getDouble("ano")+ " "+ resultado.getDouble("diaria"));
				try {
					listarModelos[cont] = resultado.getString("modelo");
					cont++;
				} catch (Exception ex) {
					ex.printStackTrace();
				}

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

	public void listarCores() {
		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;
		cont = 0;

		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT DISTINCT cor_descricao FROM `cores`";
			resultado = comando.executeQuery(meu_sql);
			while (resultado.next()) {
				// System.out.println(resultado.getInt("cod_carro")+ " "+
				// resultado.getString("tipo")+" "+ resultado.getString("modelo")+ " "+
				// resultado.getString("cor")+ " "+resultado.getDouble("potencia")+" "+
				// resultado.getDouble("ano")+ " "+ resultado.getDouble("diaria"));
				try {
					listarCores[cont] = resultado.getString("cor_descricao");
					cont++;
				} catch (Exception ex) {
					ex.printStackTrace();
				}

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
}
