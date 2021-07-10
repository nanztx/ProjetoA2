package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Agenda.Agenda;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_home_cliente {

	private JFrame frmRentIt;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_home_cliente window = new janela_home_cliente();
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
	public janela_home_cliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRentIt = new JFrame();
		frmRentIt.setTitle("RENT IT");
		frmRentIt.setBounds(100, 100, 450, 300);
		frmRentIt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRentIt.setLocationRelativeTo(null);
		frmRentIt.getContentPane().setLayout(null);
		
		JLabel lbl_menuAdministrador = new JLabel("MENU USU\u00C1RIO");
		lbl_menuAdministrador.setFont(new Font("Calibri", Font.BOLD, 14));
		lbl_menuAdministrador.setBounds(10, 11, 167, 18);
		frmRentIt.getContentPane().add(lbl_menuAdministrador);
		
		JButton btn_cadastrarUsuario = new JButton("Alterar Dados Pessoais");
		btn_cadastrarUsuario.setBounds(10, 163, 167, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario);
		
		JButton btn_cadastrarUsuario_1 = new JButton("Agendar Reserva");
		btn_cadastrarUsuario_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmRentIt.dispose();
				Agenda.main(null);
			}
		});
		btn_cadastrarUsuario_1.setBounds(10, 60, 167, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario_1);
		
		JButton btn_cadastrarUsuario_1_1 = new JButton("Comprovante de Reserva");
		btn_cadastrarUsuario_1_1.setBounds(10, 112, 167, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario_1_1);
		
		JButton btn_cadastrarUsuario_2 = new JButton("Sair");
		btn_cadastrarUsuario_2.setBounds(10, 213, 167, 23);
		frmRentIt.getContentPane().add(btn_cadastrarUsuario_2);
	}

}
