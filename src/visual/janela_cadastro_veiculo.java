package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class janela_cadastro_veiculo {

	private JFrame frame;
	private JLabel lblPlaca;
	private JLabel lblPotencia;
	private JLabel lblAno;
	private JLabel lblDiaria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_cadastro_veiculo window = new janela_cadastro_veiculo();
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
	public janela_cadastro_veiculo() {
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
		
		JLabel lblTipodocarro = new JLabel("Tipo do carro");
		lblTipodocarro.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTipodocarro.setBounds(10, 21, 84, 18);
		frame.getContentPane().add(lblTipodocarro);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Calibri", Font.BOLD, 14));
		lblModelo.setBounds(10, 50, 84, 18);
		frame.getContentPane().add(lblModelo);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCor.setBounds(10, 79, 84, 18);
		frame.getContentPane().add(lblCor);
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPlaca.setBounds(10, 108, 84, 18);
		frame.getContentPane().add(lblPlaca);
		
		lblPotencia = new JLabel("Potencia");
		lblPotencia.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPotencia.setBounds(10, 137, 84, 18);
		frame.getContentPane().add(lblPotencia);
		
		lblAno = new JLabel("Ano");
		lblAno.setFont(new Font("Calibri", Font.BOLD, 14));
		lblAno.setBounds(10, 166, 84, 18);
		frame.getContentPane().add(lblAno);
		
		lblDiaria = new JLabel("Diaria");
		lblDiaria.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDiaria.setBounds(10, 195, 84, 18);
		frame.getContentPane().add(lblDiaria);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnCadastrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(236, 227, 89, 23);
		frame.getContentPane().add(btnCancelar);
	}
}
