package gestionPedidosIntGraf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BajaProd extends JFrame {
	/**
	 * Gestión pedidos interfaz gráfica - Baja de productos
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static BajaProd frame3;
	private JTextField textCodigoProducto;

	/**
	 * Create the frame.
	 */
	public BajaProd() {
		setTitle("Baja de productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBajaDeProductos = new JLabel("Baja de productos");
		lblBajaDeProductos.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblBajaDeProductos.setBounds(145, 20, 150, 25);
		contentPane.add(lblBajaDeProductos);

		JLabel lblCodigoProducto = new JLabel("Codigo Producto");
		lblCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoProducto.setBounds(37, 64, 100, 20);
		contentPane.add(lblCodigoProducto);

		JButton btnAceptarYBorrar = new JButton("Aceptar y Borrar");
		btnAceptarYBorrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					btnAceptarYBorrar.doClick();// Con doClick es como si pulsasemos con el ratón sobre el botón
					btnAceptarYBorrar.requestFocus();// Con requestFocus hacemos que el botón sea el que está activo
					btnAceptarYBorrar.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					btnAceptarYBorrar.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		btnAceptarYBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int num = 0;
				try {

					InicioGP.rset = AccesoDatos.ConsultaBD// Consulto si el codigo esta o no en la base de datos
					("SELECT count(*) FROM PRODUCTOS WHERE (CODIGOPRODUCTO) LIKE '" + textCodigoProducto.getText()
							+ "'");

					while (InicioGP.rset.next())
						num = InicioGP.rset.getInt(1);
					// System.out.println(num);//Compruebo en consola si hay 1 o 0

					if (num > 0) {// Si num es mayor que 0

						JOptionPane.showMessageDialog(null, "El codigo existe");
					} else {
						JOptionPane.showMessageDialog(null, "El codigo no existe");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				try {
					int msj = JOptionPane.showConfirmDialog(null, "¿Esta seguro de continuar?");
					if (msj == JOptionPane.YES_OPTION) {

						String consulta = "DELETE FROM productos WHERE codigoproducto LIKE '"
								+ textCodigoProducto.getText() + "'";

						InicioGP.rset = AccesoDatos.ConsultaBD(consulta);

						JOptionPane.showMessageDialog(null, "Producto borrado correctamente");
					}
					if (msj == JOptionPane.NO_OPTION) {

						Icon unIcono = null;
						int si = JOptionPane.showOptionDialog(null, "¿Quieres que borre el campo?",
								"Pulsa SI, NO o Cancelar", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, unIcono, new Object[] { "SI", "NO", "Cancelar" }, "SI");

						if (JOptionPane.OK_OPTION == si) {
							textCodigoProducto.setText(null);

						} else {
							JOptionPane.showMessageDialog(null, "Esta bien no borro nada");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptarYBorrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptarYBorrar.setBounds(20, 211, 135, 25);
		contentPane.add(btnAceptarYBorrar);

		textCodigoProducto = new JTextField();
		textCodigoProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					btnAceptarYBorrar.requestFocus();
					btnAceptarYBorrar.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					textCodigoProducto.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textCodigoProducto.setColumns(10);
		textCodigoProducto.setBounds(167, 63, 215, 20);
		contentPane.add(textCodigoProducto);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.setVisible(false);
				InicioGP.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(283, 211, 130, 25);
		contentPane.add(btnVolver);
	}
}
