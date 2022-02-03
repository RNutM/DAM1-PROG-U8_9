package gestionPedidosIntGraf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class LeerArchivos extends JFrame {
	/**
	 * Gestión pedidos interfaz gráfica - Leer archivos
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static JTextArea textArea;
	static LeerArchivos frame5;

	/**
	 * Create the frame.
	 */
	public static void leerPedidostxt() throws IOException {
		FileReader f = new FileReader("./ficheros/interfazGrafica/pedidos.txt");
		@SuppressWarnings("resource")
		Scanner entrada = new Scanner(f);
		String cadena = "";
		while (entrada.hasNext()) {// lee cadena
			cadena = entrada.nextLine();
			// System.out.println(cadena);
			textArea.setText(cadena);
		}
		// entrada.close();
	}

	public LeerArchivos() {
		setTitle("Contenido de ficheros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 620);
		contentPane = new JPanel();
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 149, 600, 400);
		contentPane.add(scrollPane);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		scrollPane.setViewportView(textArea_1);
		textArea_1.setWrapStyleWord(true);
		textArea_1.setLineWrap(true);

		JButton btnLeerFicheroTxt = new JButton("Leer Fichero txt");
		btnLeerFicheroTxt.setBounds(40, 70, 125, 25);
		btnLeerFicheroTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLeerFicheroTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {// Codigo para leer ficheros y mostrarlos en textArea y en consola
					FileReader entrada = new FileReader("./ficheros/interfazGrafica/pedidos.txt");

					try (BufferedReader buffer = new BufferedReader(entrada)) {
						String cadena = "";
						String texto = "";
						while (cadena != null) {
							cadena = buffer.readLine();
							if (cadena != null)
								texto = texto + cadena + "\n";// Para salto de linea \n

							System.out.println(cadena);// Muestro en consola
						}
						textArea_1.setText(texto);// Muestro en textArea
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error");
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnLeerFicheroTxt);

		JButton btnLeerPedidosCsv = new JButton("Leer pedidos csv");
		btnLeerPedidosCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {// Codigo para leer ficheros y mostrarlos en textArea y en consola
					FileReader entrada = new FileReader("./ficheros/interfazGrafica/pedidos.csv");
					
					try (BufferedReader buffer = new BufferedReader(entrada)) {
						String cadena = "";
						String texto = "";
						while (cadena != null) {
							cadena = buffer.readLine();
							if (cadena != null)
								texto = texto + cadena + "\n";// Para salto de linea \n

							System.out.println(cadena);// Muestro en consola
						}
						textArea_1.setText(texto);// Muestro en textArea
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error");
					e1.printStackTrace();
				}
			}
		});
		btnLeerPedidosCsv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLeerPedidosCsv.setBounds(180, 70, 125, 25);
		contentPane.add(btnLeerPedidosCsv);

		JButton btnLeerDetalleCsv = new JButton("Leer detalle csv");
		btnLeerDetalleCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {// Codigo para leer ficheros y mostrarlos en textArea y en consola
					FileReader entrada = new FileReader("./ficheros/interfazGrafica/detalle.csv");
					
					try (BufferedReader buffer = new BufferedReader(entrada)) {
						String cadena = "";
						String texto = "";
						while (cadena != null) {
							cadena = buffer.readLine();
							if (cadena != null)
								texto = texto + cadena + "\n";// Para salto de linea \n

							System.out.println(cadena);// Muestro en consola
						}
						textArea_1.setText(texto);// Muestro en textArea
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error");
					e1.printStackTrace();
				}
			}
		});
		btnLeerDetalleCsv.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLeerDetalleCsv.setBounds(320, 70, 125, 25);
		contentPane.add(btnLeerDetalleCsv);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame5.setVisible(false);
				InicioGP.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(550, 70, 90, 25);
		contentPane.add(btnVolver);

		JLabel lblTitulo = new JLabel("Ventana para mostrar contenido de ficheros");
		lblTitulo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(151, 11, 347, 20);
		contentPane.add(lblTitulo);

		JButton btnLeerPedidosTxt = new JButton("Leer pedidos txt");
		btnLeerPedidosTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {// Codigo para leer ficheros y mostrarlos en textArea y en consola
					FileReader entrada = new FileReader("./ficheros/interfazGrafica/pedidoscsv.txt");
					
					try (BufferedReader buffer = new BufferedReader(entrada)) {
						String cadena = "";
						String texto = "";
						while (cadena != null) {
							cadena = buffer.readLine();
							if (cadena != null)
								texto = texto + cadena + "\n";// Para salto de linea \n

							System.out.println(cadena);// Muestro en consola
						}
						textArea_1.setText(texto);// Muestro en textArea
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error");
					e1.printStackTrace();
				}
			}
		});
		btnLeerPedidosTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLeerPedidosTxt.setBounds(180, 113, 125, 25);
		contentPane.add(btnLeerPedidosTxt);

		JButton btnLeerDetalleTxt = new JButton("Leer detalle txt");
		btnLeerDetalleTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {// Codigo para leer ficheros y mostrarlos en textArea y en consola
					FileReader entrada = new FileReader("./ficheros/interfazGrafica/detallecsv.txt");
					
					try (BufferedReader buffer = new BufferedReader(entrada)) {
						String cadena = "";
						String texto = "";
						while (cadena != null) {
							cadena = buffer.readLine();
							if (cadena != null)
								texto = texto + cadena + "\n";// Para salto de linea \n

							System.out.println(cadena);// Muestro en consola
						}
						textArea_1.setText(texto);// Muestro en textArea
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error");
					e1.printStackTrace();
				}
			}
		});
		btnLeerDetalleTxt.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLeerDetalleTxt.setBounds(320, 113, 125, 25);
		contentPane.add(btnLeerDetalleTxt);

		JLabel lblConvertidos = new JLabel("Convertidos ---->");
		lblConvertidos.setForeground(SystemColor.textHighlight);
		lblConvertidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConvertidos.setBounds(40, 113, 130, 25);
		contentPane.add(lblConvertidos);
	}
}
