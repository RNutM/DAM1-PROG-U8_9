package gestionPedidosIntGraf;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.SystemColor;

public class InicioGP extends JFrame {
	/**
	 * Gestión pedidos interfaz gráfica - Ventana Principal del programa (Main)
	 * 
	 * @author Robert G
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static ResultSet rset;
	static InicioGP frame1;

	ArrayList<Productos> listaproductos = new ArrayList<Productos>();
	ArrayList<Pedidos> listapedidos = new ArrayList<Pedidos>();
	ArrayList<DetallePedidos> listadetalle = new ArrayList<DetallePedidos>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 = new InicioGP();
					frame1.setVisible(true);
					AltaProd.frame2 = new AltaProd();
					AltaProd.frame2.setVisible(false);
					BajaProd.frame3 = new BajaProd();
					BajaProd.frame3.setVisible(false);
					ModProd.frame4 = new ModProd();
					ModProd.frame4.setVisible(false);
					LeerArchivos.frame5 = new LeerArchivos();
					LeerArchivos.frame5.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InicioGP() {
		setTitle("Gestión de pedidos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Productos");
		menuBar.add(mnNewMenu);

		JMenuItem mntmDarDeAlta = new JMenuItem("Dar de alta");
		mntmDarDeAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				AltaProd.frame2.setVisible(true);
			}
		});
		mnNewMenu.add(mntmDarDeAlta);

		JMenuItem mntmDarDeBaja = new JMenuItem("Dar de baja");
		mntmDarDeBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				BajaProd.frame3.setVisible(true);
			}
		});
		mnNewMenu.add(mntmDarDeBaja);

		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				ModProd.frame4.setVisible(true);
			}
		});
		mnNewMenu.add(mntmModificar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblGestinDePedidos = new JLabel("Gesti\u00F3n de Pedidos V1.0");
		lblGestinDePedidos.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblGestinDePedidos.setBounds(135, 11, 196, 25);
		contentPane.add(lblGestinDePedidos);

		JButton btnCrearPedidos = new JButton("Crear Pedidos");
		btnCrearPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rset = AccesoDatos.ConsultaBD(
							"select codigopedido, estado, codigocliente from pedidos where estado='entregado'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					Pedidos.CrearFicheroPedido(rset, listapedidos);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				try {
					CSVtoTxt.convert1();// Llamo al método de convertir y lo ejecuto con el arvhivo csv
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("Fichero generado");
				System.out.println("****************");
			}
		});
		btnCrearPedidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearPedidos.setBounds(45, 47, 120, 25);
		contentPane.add(btnCrearPedidos);

		JButton btnCrearDetalle = new JButton("Crear Detalle");
		btnCrearDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rset = AccesoDatos.ConsultaBD("select codigopedido, codigoproducto, cantidad, "
							+ "preciounidad, numerolinea from detallepedidos");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					DetallePedidos.CrearDetallePedido(rset, listadetalle);
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
				try {
					CSVtoTxt.convert2();// Llamo al método de convertir y lo ejecuto con el arvhivo csv
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println("Fichero generado");
				System.out.println("****************");
			}
		});
		btnCrearDetalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearDetalle.setBounds(203, 47, 120, 25);
		contentPane.add(btnCrearDetalle);

		JButton btnCargarProductos = new JButton("Cargar Productos");
		btnCargarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rset = AccesoDatos.ConsultaBD("select * from productos where codigoproducto='FR-102'");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					Productos.ObtenerProductos(rset, listaproductos);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				System.out.println("Producto Cargado");
				System.out.println("****************");
			}
		});
		btnCargarProductos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCargarProductos.setBounds(362, 47, 145, 25);
		contentPane.add(btnCargarProductos);

		JButton btnCProductosPedidos = new JButton("<html><p>Consultar</p><p>Productos Pedidos</p></html>");
		btnCProductosPedidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Productos.ConsultarProductos(listaproductos, listadetalle);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				System.out.println("Fichero txt creado");
				System.out.println("******************");
			}
		});
		btnCProductosPedidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCProductosPedidos.setBounds(184, 110, 161, 43);
		contentPane.add(btnCProductosPedidos);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Gracias por usar nuestro programa - Espero que haya sido de su agrado - Hasta pronto");
				System.exit(0);// Con esta linea cerramos ventana y salimos de la app
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(387, 243, 120, 25);
		contentPane.add(btnSalir);

		JLabel label1 = new JLabel("1\u00BA");
		label1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label1.setBounds(27, 47, 22, 25);
		contentPane.add(label1);

		JLabel label2 = new JLabel("2\u00BA");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label2.setBounds(184, 47, 22, 25);
		contentPane.add(label2);

		JLabel label = new JLabel("3\u00BA");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(343, 47, 22, 25);
		contentPane.add(label);

		JLabel lblEnElMenu = new JLabel(
				"<html><p>NOTA INFORMATIVA:</p><p>Si hacemos click en la palabra productos del menu superior de esta ventana</p><p>podremos acceder a un submenu para ejecutar lo siguiente:</p><p>A\u00F1adir, borrar o modificar productos de la BDD.</p></html>");
		lblEnElMenu.setForeground(new Color(178, 34, 34));
		lblEnElMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEnElMenu.setBounds(27, 201, 451, 67);
		contentPane.add(lblEnElMenu);

		JButton btnNewButton = new JButton("<html><p>Leer Archivos</p><p>En pantalla</p></html>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.setVisible(false);
				LeerArchivos.frame5.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(387, 111, 120, 40);
		contentPane.add(btnNewButton);

		JLabel lblGeneraUnFichero = new JLabel("<html><p>Generar archivos</p><p>|--------- txt----------></p></html>");
		lblGeneraUnFichero.setForeground(SystemColor.textHighlight);
		lblGeneraUnFichero.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGeneraUnFichero.setBounds(27, 107, 137, 51);
		contentPane.add(lblGeneraUnFichero);
	}
}
