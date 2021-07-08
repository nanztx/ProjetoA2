package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import Banco_dados.ClasseConexao;
import aplicação.Usuario;
import net.proteanit.sql.DbUtils;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class janela_altera_usuario {

	private JFrame frmRentIt;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_altera_usuario window = new janela_altera_usuario();
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
	public janela_altera_usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmRentIt = new JFrame();
		frmRentIt.setTitle("Rent It - Alterar Usu\u00E1rios");
		frmRentIt.setBounds(100, 100, 451, 346);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);

		JLabel lbl_usuarios = new JLabel("CADASTRO DE USU\u00C1RIO");
		lbl_usuarios.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuarios.setBounds(10, 11, 139, 18);
		frmRentIt.getContentPane().add(lbl_usuarios);

		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String novoCodigo 	= "";
				String novoUsuario 	= "";
				String novoPermissao= "";

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
								novoCodigo = (table.getValueAt(linhaCorrente,colunaCorrente).toString());	
							}
							else if(j == 1) {
								novoUsuario = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}
							else {
								novoPermissao = table.getValueAt(linhaCorrente,colunaCorrente).toString();
							}

							j++;
						}
						alteraUsuarios(novoUsuario, novoPermissao, novoCodigo);
						valida = true;
					}
				}
			}
		});
		btnNewButton.setBounds(335, 18, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton_1.setBounds(236, 18, 89, 23);
		frmRentIt.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 414, 198);
		frmRentIt.getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override			//BLOQUEAR EDIÇÃO DE COLUNA CÓDIGO VALIOSÍSSIMO
			public void mouseClicked(MouseEvent e) {
				int columnIndex= 0;
				columnIndex = table.getSelectedColumn ();
		        //System.out.println ( "Double click on jtable" );
		        if ( columnIndex == 0) {
		            JOptionPane.showMessageDialog ( null , "Não é permitido alterar a coluna COD_USUARIO!" , "ALERTA Não é Permitida Edição Deste Campo" , JOptionPane.ERROR_MESSAGE );
		        }
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JButton btnNewButton_2 = new JButton("RESET DE SENHA");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String novaSenha="";
				Boolean valida = false;

				table.getModel();
				while (valida == false) {
					if (table.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, "Selecione um usuário, para alterar a senha");
						return;
					} else {

						novaSenha = Usuario.alteraSenha(Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));		
						copiar(novaSenha);
						JOptionPane.showMessageDialog(null, "Senha copiada para area de transferência!");
					}
					valida = true;
				}
			}
		});
		btnNewButton_2.setBounds(10, 273, 139, 23);
		frmRentIt.getContentPane().add(btnNewButton_2);
		listarUsuarios();

	}

	@SuppressWarnings({ "serial", "deprecation" })
	public void listarUsuarios() {

		Connection conexao = null;
		Statement comando = null;
		ResultSet resultado = null;

		try {

			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql = "SELECT codigo, usuario, permissao FROM `usuarios`";
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

	public void alteraUsuarios(String usuario, String permissao, String codigo) {

		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ClasseConexao.Conectar();
			String sql = "UPDATE usuarios SET usuario=?, permissao=? WHERE codigo=? ";
			comando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			comando.setString(1, usuario);
			comando.setString(2, permissao);
			comando.setString(3, codigo);

			// Vamos executar o comando verificar se deu certo:
			if (comando.executeUpdate() > 0) {

				ResultSet resultado = comando.getGeneratedKeys();
				if (resultado.next()) {
					JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
					// frame.dispose();
					// janela_home_admin.main(null);
				}
			}

		} catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "O usuário "+ usuario +" já existe");
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

	private void copiar(String i){ 
		String textoCopiado="";

		textoCopiado= i;

		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); 
		String text = textoCopiado;
		StringSelection selection = new StringSelection(text); 
		clipboard.setContents(selection, null); }

}
