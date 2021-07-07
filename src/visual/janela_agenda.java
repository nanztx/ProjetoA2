package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Window.Type;
import javax.swing.JTable;
import javax.swing.JPasswordField;

public class janela_agenda {

	private JFrame frmAgendaDeCarros;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janela_agenda window = new janela_agenda();
					window.frmAgendaDeCarros.setVisible(true);
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
		frmAgendaDeCarros = new JFrame();
		frmAgendaDeCarros.setTitle("Agenda de Carros");
		frmAgendaDeCarros.setBounds(100, 100, 648, 402);
		frmAgendaDeCarros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgendaDeCarros.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Marca do Carro:");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 52, 103, 30);
		frmAgendaDeCarros.getContentPane().add(lblNewLabel);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Calibri", Font.BOLD, 15));
		lblModelo.setBounds(10, 93, 103, 30);
		frmAgendaDeCarros.getContentPane().add(lblModelo);
		
		JLabel lblDataIncioDa = new JLabel("Data In\u00EDcio da Loca\u00E7\u00E3o:");
		lblDataIncioDa.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDataIncioDa.setBounds(10, 244, 143, 25);
		frmAgendaDeCarros.getContentPane().add(lblDataIncioDa);
		
		JLabel lblDataFinalDa = new JLabel("Data Final da Loca\u00E7\u00E3o:");
		lblDataFinalDa.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDataFinalDa.setBounds(337, 244, 143, 20);
		frmAgendaDeCarros.getContentPane().add(lblDataFinalDa);
		
		JDateChooser dateChooser_inicio = new JDateChooser();
		dateChooser_inicio.setBounds(152, 244, 143, 20);
		frmAgendaDeCarros.getContentPane().add(dateChooser_inicio);
		
		JDateChooser dateChooser_final = new JDateChooser();
		dateChooser_final.setBounds(479, 242, 143, 20);
		frmAgendaDeCarros.getContentPane().add(dateChooser_final);
		
		JButton btnNewButton = new JButton("Agendar");
		btnNewButton.setBounds(258, 315, 111, 23);
		frmAgendaDeCarros.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setBounds(392, 116, 128, 24);
		frmAgendaDeCarros.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNome.setBounds(10, 11, 103, 30);
		frmAgendaDeCarros.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(118, 14, 238, 20);
		frmAgendaDeCarros.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(118, 55, 238, 20);
		frmAgendaDeCarros.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDisponibilidade = new JLabel("Disponibilidade:");
		lblDisponibilidade.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDisponibilidade.setBounds(10, 134, 103, 30);
		frmAgendaDeCarros.getContentPane().add(lblDisponibilidade);
		
		textField_2 = new JTextField();
		textField_2.setBounds(118, 96, 238, 20);
		frmAgendaDeCarros.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(118, 137, 238, 20);
		frmAgendaDeCarros.getContentPane().add(textField_3);
		
		table = new JTable();
		table.setBounds(414, 116, 1, 1);
		frmAgendaDeCarros.getContentPane().add(table);
	}
}
