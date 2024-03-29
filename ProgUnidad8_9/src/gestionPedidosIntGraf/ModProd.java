package gestionPedidosIntGraf;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ModProd extends JFrame {
	/**
	 * Gesti�n pedidos interfaz gr�fica - Modificar producto
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ModProd frame4;
	private JTextField textCodigoProducto;
	private JTextField textNombre;
	private JTextField textDimensiones;
	private JTextField textProveedor;
	private JTextField textDescripcion;
	private JTextField textCantidadStock;
	private JTextField textPrecioVenta;
	private JTextField textPrecioProveedor;

	/**
	 * Create the frame.
	 */
	public ModProd() {
		setTitle("Modificaci�n de productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFormularioDeModificacin = new JLabel("Formulario de modificaci\u00F3n de productos");
		lblFormularioDeModificacin.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblFormularioDeModificacin.setBounds(50, 20, 330, 25);
		contentPane.add(lblFormularioDeModificacin);

		JLabel lblCodigoProducto = new JLabel("Codigo Producto");
		lblCodigoProducto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCodigoProducto.setBounds(20, 71, 100, 20);
		contentPane.add(lblCodigoProducto);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(20, 102, 100, 20);
		contentPane.add(lblNombre);

		JLabel lblGama = new JLabel("Gama");
		lblGama.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGama.setBounds(20, 133, 100, 20);
		contentPane.add(lblGama);

		JLabel lblDimensiones = new JLabel("Dimensiones");
		lblDimensiones.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDimensiones.setBounds(20, 164, 100, 20);
		contentPane.add(lblDimensiones);

		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblProveedor.setBounds(20, 195, 100, 20);
		contentPane.add(lblProveedor);

		JLabel lblDescripcion = new JLabel("Descripci\u00F3n");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDescripcion.setBounds(20, 226, 100, 20);
		contentPane.add(lblDescripcion);

		JLabel lblCantidadStock = new JLabel("Cantidad Stock");
		lblCantidadStock.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidadStock.setBounds(20, 257, 100, 20);
		contentPane.add(lblCantidadStock);

		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioVenta.setBounds(20, 288, 100, 20);
		contentPane.add(lblPrecioVenta);

		JLabel lblPrecioProveedor = new JLabel("Precio Proveedor");
		lblPrecioProveedor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecioProveedor.setBounds(20, 319, 100, 20);
		contentPane.add(lblPrecioProveedor);

		JComboBox<Object> comboBoxGama = new JComboBox<Object>();
		comboBoxGama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textDimensiones.requestFocus();
					textDimensiones.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																	// pulsando enter
					comboBoxGama.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});

		comboBoxGama.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Herbaceas", "Herramientas", "Arom\u00E1ticas", "Frutales", "Ornamentales" }));
		comboBoxGama.setSelectedItem(null);// Con esta linea ocultamos las opciones
		comboBoxGama.setBounds(150, 132, 215, 22);
		contentPane.add(comboBoxGama);

		textCodigoProducto = new JTextField();
		textCodigoProducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textNombre.requestFocus();
					textNombre.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																// pulsando enter
					textCodigoProducto.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textCodigoProducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				int num = 0;
				String codigo = textCodigoProducto.getText().toUpperCase();
				if (codigo.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El campo no puede estar vacio");

				} else {

					try {

						InicioGP.rset = AccesoDatos.ConsultaBD(
								"SELECT count(*) FROM PRODUCTOS WHERE (CODIGOPRODUCTO) LIKE '" + codigo + "'");

						while (InicioGP.rset.next())
							num = InicioGP.rset.getInt(1);
						// System.out.println(num);

						if (num > 0) {

							JOptionPane.showMessageDialog(null, "El c�digo existe");

							InicioGP.rset = AccesoDatos
									.ConsultaBD("SELECT * FROM PRODUCTOS WHERE (CODIGOPRODUCTO) LIKE '" + codigo + "'");
							while (InicioGP.rset.next()) {

								// textCodigoProducto.setText(InicioGP.rset.getString(1));
								textNombre.setText(InicioGP.rset.getString(2));
								comboBoxGama.setSelectedItem(InicioGP.rset.getString(3));
								textDimensiones.setText(InicioGP.rset.getString(4));
								textProveedor.setText(InicioGP.rset.getString(5));
								textDescripcion.setText(InicioGP.rset.getString(6));
								textCantidadStock.setText(InicioGP.rset.getString(7));
								textPrecioVenta.setText(InicioGP.rset.getString(8));
								textPrecioProveedor.setText(InicioGP.rset.getString(9));
							}

						} else {
							JOptionPane.showMessageDialog(null, "El c�digo no existe");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textCodigoProducto.setColumns(10);
		textCodigoProducto.setBounds(150, 70, 215, 20);
		contentPane.add(textCodigoProducto);

		textNombre = new JTextField();
		textNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					comboBoxGama.requestFocus();
					comboBoxGama.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																// pulsando enter
					textNombre.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textNombre.setColumns(10);
		textNombre.setBounds(150, 101, 215, 20);
		contentPane.add(textNombre);

		textDimensiones = new JTextField();
		textDimensiones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textProveedor.requestFocus();
					textProveedor.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																	// pulsando enter
					textDimensiones.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textDimensiones.setColumns(10);
		textDimensiones.setBounds(150, 165, 215, 20);
		contentPane.add(textDimensiones);

		textProveedor = new JTextField();
		textProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textDescripcion.requestFocus();
					textDescripcion.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																	// pulsando enter
					textProveedor.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textProveedor.setColumns(10);
		textProveedor.setBounds(150, 196, 215, 20);
		contentPane.add(textProveedor);

		textDescripcion = new JTextField();
		textDescripcion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textCantidadStock.requestFocus();
					textCantidadStock.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					textDescripcion.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textDescripcion.setColumns(10);
		textDescripcion.setBounds(150, 227, 215, 20);
		contentPane.add(textDescripcion);

		textCantidadStock = new JTextField();
		textCantidadStock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textPrecioVenta.requestFocus();
					textPrecioVenta.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos accedido
																	// pulsando enter
					textCantidadStock.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textCantidadStock.setColumns(10);
		textCantidadStock.setBounds(150, 258, 215, 20);
		contentPane.add(textCantidadStock);

		textPrecioVenta = new JTextField();
		textPrecioVenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					textPrecioProveedor.requestFocus();
					textPrecioProveedor.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					textPrecioVenta.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textPrecioVenta.setColumns(10);
		textPrecioVenta.setBounds(150, 289, 215, 20);
		contentPane.add(textPrecioVenta);

		JButton btnAceptarYGuardar = new JButton("Aceptar y Guardar");
		btnAceptarYGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER

					btnAceptarYGuardar.doClick();// Con doClick es como si pulsasemos con el rat�n sobre el bot�n
					btnAceptarYGuardar.requestFocus();// Con requestFocus hacemos que el bot�n sea el que est� activo
					btnAceptarYGuardar.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					btnAceptarYGuardar.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		btnAceptarYGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {// Aviso al usuario si quiere continuar con la modificaci�n
					int msj = JOptionPane.showConfirmDialog(null, "�Esta seguro de continuar?");
					if (msj == JOptionPane.YES_OPTION) {// Si la opci�n es SI...

						String consulta = "UPDATE PRODUCTOS SET codigoproducto = '" + textCodigoProducto.getText() + "'"
								+ "," + "nombre= '" + textNombre.getText() + "'" + "," + "gama = '"
								+ comboBoxGama.getSelectedItem() + "'" + "," + "dimensiones = '"
								+ textDimensiones.getText() + "'" + "," + "proveedor = '" + textProveedor.getText()
								+ "'" + "," + "descripcion = '" + textDescripcion.getText() + "'" + ","
								+ "cantidadenstock ='" + textCantidadStock.getText() + "'" + "," + "precioventa = '"
								+ textPrecioVenta.getText() + "'" + "," + "precioproveedor ='"
								+ textPrecioProveedor.getText() + "'" + "WHERE codigoproducto LIKE '"
								+ textCodigoProducto.getText() + "'";

						InicioGP.rset = AccesoDatos.ConsultaBD(consulta);// Hago el INSERT en la BDD

						JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
					}
					if (msj == JOptionPane.NO_OPTION) {// Si la opci�n es NO...

						Icon unIcono = null;// Creo una variable de tipo icono
						int si = JOptionPane.showOptionDialog// Creo mi propia ventana de mensaje personalizado
						(null, "�Quieres que borre todos los campos?", "Pulsa SI, NO o Cancelar",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, unIcono,
								new Object[] { "SI", "NO", "Cancelar" }, "SI");

						if (JOptionPane.OK_OPTION == si) {// Borro todos los campos
							textCodigoProducto.setText(null);
							textNombre.setText(null);
							comboBoxGama.setSelectedItem(null);
							textDimensiones.setText(null);
							textProveedor.setText(null);
							textDescripcion.setText(null);
							textCantidadStock.setText(null);
							textPrecioVenta.setText(null);
							textPrecioProveedor.setText(null);
						} else {
							JOptionPane.showMessageDialog(null, "Esta bien no borro nada");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}// Fin public void actionPerformed(ActionEvent arg0)
		});// Fin btnAceptarYGuardar.addActionListener(new ActionListener()
		btnAceptarYGuardar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAceptarYGuardar.setBounds(20, 410, 135, 25);
		contentPane.add(btnAceptarYGuardar);

		textPrecioProveedor = new JTextField();
		textPrecioProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {// Elegimos ENTER
					btnAceptarYGuardar.requestFocus();
					btnAceptarYGuardar.setBackground(Color.lightGray);// Se pone gris claro el campo al que hemos
																		// accedido pulsando enter
					textPrecioProveedor.setBackground(Color.cyan);// Se pone cyan el campo que dejamos atras
				}
			}
		});
		textPrecioProveedor.setColumns(10);
		textPrecioProveedor.setBounds(150, 320, 215, 20);
		contentPane.add(textPrecioProveedor);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame4.setVisible(false);
				InicioGP.frame1.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(285, 410, 130, 25);
		contentPane.add(btnVolver);
	}
}
