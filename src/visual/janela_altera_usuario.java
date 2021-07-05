package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Banco_dados.ClasseConexao;
import net.proteanit.sql.DbUtils;

public class janela_altera_usuario {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_altera_usuario window = new janela_altera_usuario();
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
	public janela_altera_usuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_usuarios = new JLabel("CADASTRO DE USU\u00C1RIO");
		lbl_usuarios.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuarios.setBounds(10, 11, 139, 18);
		frame.getContentPane().add(lbl_usuarios);
		
		JButton btnNewButton = new JButton("Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(335, 18, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				janela_home_admin.main(null);
			}
		});
		btnNewButton_1.setBounds(236, 18, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 414, 198);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		listarUsuarios();
		
	}
	
public void listarUsuarios() {
		
		Connection conexao =   null;
		Statement  comando  =  null;
		ResultSet  resultado = null;
		
		try {
			
			conexao = ClasseConexao.Conectar();
			comando = conexao.createStatement();
			String meu_sql ="SELECT `codigo`, `usuario`, `permissao` FROM `usuarios`";
			resultado = comando.executeQuery(meu_sql);
			table.setModel(DbUtils.resultSetToTableModel(resultado));
			
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
