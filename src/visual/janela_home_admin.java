package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_home_admin {

	private JFrame frmRentIt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_home_admin window = new janela_home_admin();
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
	public janela_home_admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT");
		frmRentIt.setBounds(100, 100, 495, 343);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.getContentPane().setLayout(null);
		
		JLabel lbl_menuAdministrador = new JLabel("MENU ADMINISTRADOR");
		lbl_menuAdministrador.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_menuAdministrador.setBounds(10, 11, 167, 14);
		frmRentIt.getContentPane().add(lbl_menuAdministrador);
		
		JLabel lbl_usuario = new JLabel("Usu\u00E1rio");
		lbl_usuario.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuario.setBounds(10, 24, 167, 26);
		frmRentIt.getContentPane().add(lbl_usuario);
		
		JLabel lbl_usuario_1 = new JLabel("Ve\u00EDculo");
		lbl_usuario_1.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_usuario_1.setBounds(10, 165, 167, 26);
		frmRentIt.getContentPane().add(lbl_usuario_1);
		
		JLabel lbl_agenda = new JLabel("Agenda");
		lbl_agenda.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_agenda.setBounds(257, 34, 167, 14);
		frmRentIt.getContentPane().add(lbl_agenda);
		
		JButton btn_cadastrarUsuario = new JButton("Cadastrar Usu\u00E1rio");
		btn_cadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_cadastro_cliente.main(null);				
			}
		});
		btn_cadastrarUsuario.setBounds(10, 52, 128, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario);
		
		JButton btn_alterarUsuario = new JButton("Alterar Usu\u00E1rio");
		btn_alterarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				janela_altera_usuario.main(null);
			}
		});
		btn_alterarUsuario.setBounds(10, 86, 128, 23);
		frmRentIt.getContentPane().add(btn_alterarUsuario);
		
		JButton btn_removerUsuario = new JButton("Remover Usu\u00E1rio");
		btn_removerUsuario.setBounds(10, 120, 128, 23);
		frmRentIt.getContentPane().add(btn_removerUsuario);
		
		JButton btn_cadastrarVeiculo = new JButton("Cadastrar Ve\u00EDculo");
		btn_cadastrarVeiculo.setBounds(10, 202, 128, 23);
		frmRentIt.getContentPane().add(btn_cadastrarVeiculo);
		
		JButton btn_cadastrarUsuario_4 = new JButton("Alterar Ve\u00EDculo");
		btn_cadastrarUsuario_4.setBounds(10, 236, 128, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario_4);
		
		JButton btn_removerVeiculo = new JButton("Remover Ve\u00EDculo");
		btn_removerVeiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_removerVeiculo.setBounds(10, 270, 128, 23);
		frmRentIt.getContentPane().add(btn_removerVeiculo);
		
		JButton btn_cadastrarAgenda = new JButton("Cadastrar Agenda");
		btn_cadastrarAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_cadastrarAgenda.setBounds(257, 52, 128, 23);
		frmRentIt.getContentPane().add(btn_cadastrarAgenda);
		
		JButton btn_alterarAgenda = new JButton("Alterar Agenda");
		btn_alterarAgenda.setBounds(257, 86, 128, 23);
		frmRentIt.getContentPane().add(btn_alterarAgenda);
		
		JButton btn_removerAgenda = new JButton("Remover Agenda");
		btn_removerAgenda.setBounds(257, 120, 128, 23);
		frmRentIt.getContentPane().add(btn_removerAgenda);
	}
}
