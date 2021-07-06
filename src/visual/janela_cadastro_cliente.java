package visual;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Random;

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

public class janela_cadastro_cliente {

	private JFrame frmRentIt;
	private JTextField txt_nome;
	private JTextField txt_email;
	private JTextField txt_codusuario;
	private JTextField txt_usuario;
	private JFormattedTextField txt_celular = null;
	private JFormattedTextField txt_cpf = null;
	private JFormattedTextField txt_dtnascimento = null;
	private JFormattedTextField txt_dtcadastro = null;
	private JComboBox<String> PermissaoBox;
	public int cont=0;
	public String listarPermissoes[] = new String[100];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cadastro_cliente window = new janela_cadastro_cliente();
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
	public janela_cadastro_cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT - Cadastro de Usu\u00E1rio");
		frmRentIt.setBounds(100, 100, 565, 336);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);
		
		JLabel lbl_cadastroUsuario = new JLabel("CADASTRO DE USU\u00C1RIO");
		lbl_cadastroUsuario.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_cadastroUsuario.setBounds(10, 11, 194, 24);
		frmRentIt.getContentPane().add(lbl_cadastroUsuario);
		
		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_nome.setBounds(10, 104, 47, 24);
		frmRentIt.getContentPane().add(lbl_nome);
		
		JLabel lbl_cpf = new JLabel("CPF:");
		lbl_cpf.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_cpf.setBounds(10, 139, 47, 24);
		frmRentIt.getContentPane().add(lbl_cpf);
		
		JLabel lbl_dtnascmento = new JLabel("Data Nascimento:");
		lbl_dtnascmento.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_dtnascmento.setBounds(10, 173, 109, 24);
		frmRentIt.getContentPane().add(lbl_dtnascmento);
		
		JLabel lbl_dtcadastro = new JLabel("Data Cadastro:");
		lbl_dtcadastro.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_dtcadastro.setBounds(10, 208, 98, 24);
		frmRentIt.getContentPane().add(lbl_dtcadastro);
		
		JLabel lbl_email = new JLabel("Email:");
		lbl_email.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_email.setBounds(316, 74, 47, 24);
		frmRentIt.getContentPane().add(lbl_email);
		
		JLabel lbl_celular = new JLabel("Celular:");
		lbl_celular.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_celular.setBounds(316, 104, 47, 24);
		frmRentIt.getContentPane().add(lbl_celular);
		
		JLabel lbl_codusuario = new JLabel("C\u00F3digo de Usu\u00E1rio:");
		lbl_codusuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_codusuario.setBounds(316, 139, 109, 24);
		frmRentIt.getContentPane().add(lbl_codusuario);
		
		txt_nome = new JTextField();
		txt_nome.setBounds(57, 104, 234, 20);
		frmRentIt.getContentPane().add(txt_nome);
		txt_nome.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(373, 74, 148, 20);
		frmRentIt.getContentPane().add(txt_email);
		
		txt_codusuario = new JTextField();
		txt_codusuario.setEditable(false);
		String texto = pegaProxId();
		txt_codusuario.setText(texto);
		txt_codusuario.setColumns(10);
		txt_codusuario.setBounds(435, 139, 86, 20);
		frmRentIt.getContentPane().add(txt_codusuario);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton.setBounds(129, 263, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
				
			}
		});
		btnNewButton_1.setBounds(316, 263, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton_1);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio");
		lbl_usuario.setFont(new Font("Calibri", Font.PLAIN, 14));
		lbl_usuario.setBounds(10, 69, 47, 24);
		frmRentIt.getContentPane().add(lbl_usuario);
		
		txt_usuario = new JTextField();
		txt_usuario.setColumns(10);
		txt_usuario.setBounds(57, 69, 158, 20);
		frmRentIt.getContentPane().add(txt_usuario);
		
		try {
			txt_celular = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Celular Inválido!");
			e1.printStackTrace();
		}
		txt_celular.setBounds(373, 104, 148, 20);
		frmRentIt.getContentPane().add(txt_celular);
		
		try {
			txt_cpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "CPF Inválido!");
			e1.printStackTrace();
		}
		txt_cpf.setBounds(56, 139, 158, 20);
		frmRentIt.getContentPane().add(txt_cpf);
		
		
		try {
			txt_dtnascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Data de Nascimento Inválida!");
			e1.printStackTrace();
		}
		txt_dtnascimento.setBounds(129, 173, 86, 20);
		frmRentIt.getContentPane().add(txt_dtnascimento);
		
		LocalDate datahoje = LocalDate.now(); 
		String dthoje = datahoje.toString();
		dthoje = montaDataHoje(dthoje);
		try {
			txt_dtcadastro = new JFormattedTextField(new MaskFormatter("##/##/####"));
			txt_dtcadastro.setText(dthoje);
			txt_dtcadastro.setEditable(false);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Data de Cadastro Inválida!");
			e1.printStackTrace();
		}
		txt_dtcadastro.setBounds(129, 208, 86, 20);
		frmRentIt.getContentPane().add(txt_dtcadastro);
		
		listarPermissoes();
		PermissaoBox = new JComboBox<>();
        if(cont>0) {
        	for(int i=0;i<cont;i++)	{
        		PermissaoBox.addItem(listarPermissoes[i]);
        	}
        	PermissaoBox.setSelectedItem(null);
        }
        PermissaoBox.action(null, PermissaoBox);
        PermissaoBox.setBounds(393, 172, 128, 22);
        frmRentIt.getContentPane().add(PermissaoBox);
        
        JLabel lbl_permissao = new JLabel("Permiss\u00E3o:");
        lbl_permissao.setFont(new Font("Calibri", Font.PLAIN, 14));
        lbl_permissao.setBounds(316, 173, 67, 24);
        frmRentIt.getContentPane().add(lbl_permissao);

        
	}
	

	private void cadastrar() {
		
		Connection conexao =   null;
		PreparedStatement  comando  =  null;
		
	
		//INSERT NA TABELA DE USUARIOS
		try {
			conexao = ClasseConexao.Conectar();
			String sql = "INSERT INTO usuarios (usuario, senha,permissao) VALUES (?,?,?)";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, txt_usuario.getText());
			comando.setString(2, generateRandomPassword(12));
			comando.setString(3, PermissaoBox.getSelectedItem().toString());
					
			// Vamos executar o comando verificar se deu certo:
			if(comando.executeUpdate()>0) {
				ResultSet resultado = comando.getGeneratedKeys();
				if(resultado.next()) {
					//System.out.println("dados gravados no codigo:" + resultado.getInt(1));
					//JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
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
			String txt1 = txt_dtnascimento.getText();
			comando.setString(3, montaData(txt1));
			String txt2 = txt_dtcadastro.getText();
			comando.setString(4, montaData(txt2));
			comando.setString(5, txt_email.getText());
			String celular = txt_celular.getText();
			comando.setString(6, trataCelular(celular));
			//QUANDO SE TEM UM .setText(), não precisa informar denovo
			comando.setString(7, txt_codusuario.getText());			
					
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
			JOptionPane.showMessageDialog(null, "Informação ultrapassa o numero de dígitos disponibilizado no campo");
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
	
    public void listarPermissoes() {
    	
		Connection conexao 	=  null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		try {
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT DISTINCT permissao FROM `permissoes`";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				//System.out.println(resultado.getInt("cod_carro")+ "  "+ resultado.getString("tipo")+"  "+ resultado.getString("modelo")+ "  "+ resultado.getString("cor")+ "  "+resultado.getDouble("potencia")+"  "+ resultado.getDouble("ano")+ "  "+ resultado.getDouble("diaria"));
				try {
						listarPermissoes[cont] = resultado.getString("permissao");
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
	
	public static String generateRandomPassword(int len) {
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
	          +"jklmnopqrstuvwxyz!@#$%&";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			return sb.toString();
		}

	public String montaData(String str) {
		int tamanho = str.length();
		String dataformatada= str.substring(6,10) + "-" + str.substring(3,5) + "-" + str.substring(0,2);
		return dataformatada;
	}
	
	public String montaDataHoje(String str) {
		int tamanho = str.length();
		String dataformatada= str.substring(8,10) + "/" + str.substring(5,7) + "/" + str.substring(0,4);
		return dataformatada;
	}
	
	public String trataCelular(String str) {
		int tamanho = str.length();
		String celular= str.replace(" ","");
		celular = celular.replace("(", "");
		celular = celular.replace(")", "-");
		celular = celular.replace("-", "");
		return celular;
	}

	public String pegaProxId() {
		
		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		String codigo ="";
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT MAX(codigo) FROM usuarios";
			resultado = comando.executeQuery(meu_sql);
			while(resultado.next()) {
				  codigo = String.valueOf(resultado.getInt(1)+1);
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
		return codigo;
	}

	private String String(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
