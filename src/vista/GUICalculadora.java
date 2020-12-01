package vista;

import logica.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GUICalculadora extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNum1,textFieldNum2;
	private Calculadora c;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICalculadora frame = new GUICalculadora();
					frame.setVisible(true);
					frame.setTitle("Calculadora.exe");
					frame.setResizable(false);
					frame.setIconImage(new ImageIcon(this.getClass().getResource("/files/logo.png")).getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUICalculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelDatos = new JPanel();
		contentPane.add(panelDatos);
		panelDatos.setLayout(new GridLayout(2, 2));
		
		JLabel lblNum1 = new JLabel("Ingrese primer numero:");
		panelDatos.add(lblNum1);
		
		textFieldNum1 = new JTextField();
		panelDatos.add(textFieldNum1);
		textFieldNum1.setColumns(10);
		
		JLabel lblNum2 = new JLabel("Ingrese segundo numero:");
		panelDatos.add(lblNum2);
		
		textFieldNum2 = new JTextField();
		panelDatos.add(textFieldNum2);
		textFieldNum2.setColumns(10);
		
		JPanel panelOpciones = new JPanel();
		contentPane.add(panelOpciones);
		
		comboBox = new JComboBox();
		panelOpciones.add(comboBox);
		
		c = new Calculadora();
		
		try {
			c.getPlugins();
			
			String [] lista_plugins = c.getNombresPlugins();
			
			for (int i=0;i<lista_plugins.length;i++) {
				comboBox.addItem(lista_plugins[i]);
			}
		}catch(NoClassDefFoundError ex) {
			JOptionPane.showMessageDialog(null, "Operaciones actualizadas.");
		}
		JButton btnCalcular = new JButton("Calcular");
		panelOpciones.add(btnCalcular);
		
		JButton btnActualizar = new JButton("Actualizar operaciones");
		panelOpciones.add(btnActualizar);
		
		JPanel panelResultado = new JPanel();
		contentPane.add(panelResultado);
		
		JLabel lblResultado = new JLabel("Resultado:");
		panelResultado.add(lblResultado);
		
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1,num2;
				String operacion;
				float result;
				
				try {
					num1 = Integer.parseInt(textFieldNum1.getText());
					num2 = Integer.parseInt(textFieldNum2.getText());
					
					operacion = (String) comboBox.getSelectedItem();
					
					result = c.runPlugin(num1, num2, operacion);
					
					lblResultado.setText("Resultado: "+String.format("%.4f", result));
					
				}catch(NumberFormatException ex1) {
					JOptionPane.showMessageDialog(null, "Error en el ingreso de datos. Ingrese datos correctos.");
				}catch(ArithmeticException ex2) {
					JOptionPane.showMessageDialog(null, "Error aritmetico en la operacion");
				}
			}
		});
		
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] lista_plugins;
				
				try {
					c.getPlugins();
					
					comboBox.removeAllItems();
					
					lista_plugins = c.getNombresPlugins();
					
					for (int i=0;i<lista_plugins.length;i++) {
						comboBox.addItem(lista_plugins[i]);
					}
	
					JOptionPane.showMessageDialog(null, "Operaciones actualizadas.");
				}catch(NoClassDefFoundError ex) {
					JOptionPane.showMessageDialog(null, "Error en la carga de plugins");
				}
				
			}
		});
	}

}
