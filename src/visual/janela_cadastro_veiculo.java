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
	public int cont=0;
	
	private JComboBox<String> marcaBox;
	public String listarVeiculos[] = new String[100];
	
	private JComboBox<String> coresBox;
	public String listarCores[] = new String[100];
	
	private JComboBox<String> tiposBox;
	public String listarTipos[] = new String[100];
	
	private JComboBox<String> modelosBox;
	public String listarModelos[] = new String[100];
	
	public JFormattedTextField txt_placa = null;
	
	public JFormattedTextField txt_valorDiaria = null;
	public String valorFormatado="";
	private JTextField txt_ano_fabricacao;
	
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
		frmRentIt.setBounds(100, 100, 450, 300);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarVeiculo();
			}
		});
		btnCadastrar.setBounds(335, 227, 89, 23);
		frmRentIt.getContentPane().add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnCancelar.setBounds(236, 227, 89, 23);
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
		lbl_placa.setBounds(236, 54, 47, 24);
		frmRentIt.getContentPane().add(lbl_placa);

		JLabel lbl_potencia = new JLabel("Pot\u00EAncia (CV's):");
		lbl_potencia.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_potencia.setBounds(236, 89, 78, 24);
		frmRentIt.getContentPane().add(lbl_potencia);

		JLabel lbl_ano_fabricacao = new JLabel("Ano de Fabrica\u00E7\u00E3o:");
		lbl_ano_fabricacao.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_ano_fabricacao.setBounds(236, 124, 142, 24);
		frmRentIt.getContentPane().add(lbl_ano_fabricacao);

		JLabel lbl_valor_diaria = new JLabel("Valor de Di\u00E1ria:");
		lbl_valor_diaria.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_valor_diaria.setBounds(236, 159, 103, 24);
		frmRentIt.getContentPane().add(lbl_valor_diaria);

		txt_potencia = new JTextField();
		txt_potencia.setBounds(338, 89, 86, 20);
		frmRentIt.getContentPane().add(txt_potencia);
		txt_potencia.setColumns(10);
		
		//COMBO-BOX MARCA
		listarVeiculos();
		marcaBox = new JComboBox<>();
		if(cont>0) {
			for(int i=0;i<cont;i++)	{
				marcaBox.addItem(listarVeiculos[i]);
			}
			marcaBox.setSelectedItem(null);
		}
		marcaBox.action(null, marcaBox);
		marcaBox.setBounds(68, 53, 127, 22);
		frmRentIt.getContentPane().add(marcaBox);

		//COMBO-BOX MODELO
		listarModelos();
		modelosBox = new JComboBox<>();
        if(cont>0) {
        	for(int j=0;j<cont;j++)	{
        		modelosBox.addItem(listarModelos[j]);
        	}
        	modelosBox.setSelectedItem(null);
        }
        modelosBox.action(null, modelosBox);
        modelosBox.setBounds(67, 91, 128, 22);
        frmRentIt.getContentPane().add(modelosBox);

		listarTipos();
		tiposBox = new JComboBox<>();
        if(cont>0) {
        	for(int l=0;l<cont;l++)	{
        		tiposBox.addItem(listarTipos[l]);
        	}
        	tiposBox.setSelectedItem(null);
        }
        tiposBox.action(null, tiposBox);
        tiposBox.setBounds(67, 123, 128, 22);
        frmRentIt.getContentPane().add(tiposBox);
        
        
		//COMBO-BOX CORES
		listarCores();
		coresBox = new JComboBox<>();
        if(cont>0) {
        	for(int k=0;k<cont;k++)	{
        		coresBox.addItem(listarCores[k]);
        	}
        	coresBox.setSelectedItem(null);
        }
        coresBox.action(null, coresBox);
        coresBox.setBounds(67, 158, 128, 22);
        frmRentIt.getContentPane().add(coresBox);
        
		try {
			txt_placa = new JFormattedTextField(new MaskFormatter("UUU-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Placa Inv�lida!");
			e1.printStackTrace();
		}
        txt_placa.setBounds(303, 54, 121, 21);
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
        txt_valorDiaria.setBounds(335, 159, 89, 21);
        frmRentIt.getContentPane().add(txt_valorDiaria);
        
        txt_ano_fabricacao = new JTextField();
        txt_ano_fabricacao.setColumns(10);
        txt_ano_fabricacao.setBounds(351, 124, 73, 20);
        frmRentIt.getContentPane().add(txt_ano_fabricacao);
        
		
	}

	public void cadastrarVeiculo() {

		int cod_marca=0;	
		int cod_modelo=0;	
		
		int i = 0;
		valorFormatado = valorFormatado.replace("R$", "");
		valorFormatado = valorFormatado.replace(",", ".");
		valorFormatado = valorFormatado.substring(1, valorFormatado.length());
		Double valorDiaria = Double.parseDouble(valorFormatado);
		BigDecimal valor = new BigDecimal(valorDiaria);
		
		cod_modelo = GetCodigoModelo(modelosBox.getSelectedItem().toString());
		cod_marca = GetCodigoMarca(marcaBox.getSelectedItem().toString());
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;

		//INSERT NA TABELA DE VE�CULOS
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO `veiculos`(`modelo`, `cod_modelo`, `marca`, `cod_marca`, `tipo`, `cor`, `placa`, `potencia`, `ano`, `diaria`) VALUES (?,?,?,?,?,?,?,?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, modelosBox.getSelectedItem().toString());
			comando.setInt(2, cod_marca );
			comando.setString(3, marcaBox.getSelectedItem().toString());
			comando.setInt(4, cod_modelo);
			comando.setString(5, tiposBox.getSelectedItem().toString());
			comando.setString(6, coresBox.getSelectedItem().toString());
			comando.setString(7, txt_placa.getText());
			comando.setString(8, txt_potencia.getText());
			int anoFab = Integer.parseInt(txt_ano_fabricacao.getText());
			comando.setInt(9, anoFab);	
			comando.setBigDecimal(10, valor);
			
			
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					//System.out.println("dados gravados no codigo:" + resultado.getInt(1));
					JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
					frmRentIt.dispose();
					janela_home_admin.main(null);
				}
			}

		}	catch (MysqlDataTruncation e) {
			JOptionPane.showMessageDialog(null, "Informa��o ultrapassa o numero de d�gitos disponibilizado no campo");
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

	public int GetCodigoMarca(String marca) {
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		ResultSet  resultado = null;
		
		int codMarca =0; 
		String marcaRecebida = marca;
		
		try {
			
			conexao = ClasseConexao.Conectar();
			String meu_sql ="SELECT codigo FROM marcas WHERE `marca` = '"+marcaRecebida+"' ";	
			comando = conexao.prepareStatement(meu_sql);
			
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				codMarca = resultado.getInt("codigo");
				//JOptionPane.showMessageDialog(null, "Pegou a marca!");
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
		return codMarca;
		
	}
	
	public int GetCodigoModelo(String modelo) {
		
 		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		ResultSet  resultado = null;
		
		int codModelo =0; 
		String modeloRecebido = modelo.trim();
		
		try {
			
			conexao = ClasseConexao.Conectar();
			String meu_sql ="SELECT codigo FROM modelos WHERE `modelo` = '"+modeloRecebido+"' ";	
			comando = conexao.prepareStatement(meu_sql);
			//comando.setString(1, modeloRecebido);
			
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				codModelo = resultado.getInt("codigo");
				//JOptionPane.showMessageDialog(null, "Pegou o modelo!");
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
		return codModelo;
	}
	
	public void listarVeiculos() {
		Connection conexao 	=  null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		cont=0;
		
		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT DISTINCT marca FROM `marcas`";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				//System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
				try {
					listarVeiculos[cont] = resultado.getString("marca");
					cont++;
				}catch(Exception ex) {
					ex.printStackTrace();
				}

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
	}

	public void listarTipos() {
		Connection conexao 	=  null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		cont=0;
		
		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT DISTINCT tipo_descricao FROM `tipos`";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				//System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
				try {
					listarTipos[cont] = resultado.getString("tipo_descricao");
					cont++;
				}catch(Exception ex) {
					ex.printStackTrace();
				}

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
	}
	
	public void listarModelos() {
		Connection conexao 	=  null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		cont=0;
		
		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT DISTINCT modelo FROM `modelos`";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				//System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
				try {
					listarModelos[cont] = resultado.getString("modelo");
					cont++;
				}catch(Exception ex) {
					ex.printStackTrace();
				}

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

	}

	public void listarCores() {
		Connection conexao 	=  null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		cont=0;
		
		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT DISTINCT cor_descricao FROM `cores`";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				//System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
				try {
					listarCores[cont] = resultado.getString("cor_descricao");
					cont++;
				}catch(Exception ex) {
					ex.printStackTrace();
				}

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

	}
}
