package gestionPedidos;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	/**
	 * Gestión pedidos - Clase principal (Main)
	 * 
	 * @author Robert G
	 * 
	 */
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException, IOException {

		ArrayList<Productos> listaproductos = new ArrayList<Productos>();
		ArrayList<Pedidos> listapedidos = new ArrayList<Pedidos>();
		ArrayList<DetallePedidos> listadetalle = new ArrayList<DetallePedidos>();

		int opciones = 0;
		ResultSet rset;

		do {
			System.out.println("1. Crear Pedidos (Genera archivo csv)");
			System.out.println("2. Crear Detalle (Genera archivo csv)");
			System.out.println("3. Cargar Productos (Rellena un ArrayList)");
			System.out.println("4. Consultar Productos (Genera archivo txt)");
			System.out.println("5. Salir (Sale del programa)");
			opciones = sc.nextInt();

			switch (opciones) {

			case 1: // Crear Pedidos
				rset = AccesoDatos
						.ConsultaBD("select codigopedido, estado, codigocliente from pedidos where estado='entregado'");
				Pedidos.CrearFicheroPedido(rset, listapedidos);
				System.out.println("Fichero generado");
				System.out.println("****************");
				break;
			case 2: // Crear Detalle
				rset = AccesoDatos.ConsultaBD("select codigopedido, codigoproducto, cantidad, "
						+ "preciounidad, numerolinea from detallepedidos");
				DetallePedidos.CrearDetallePedido(rset, listadetalle);
				System.out.println("Fichero generado");
				System.out.println("****************");
				break;
			case 3: // Cargar Productos
				rset = AccesoDatos.ConsultaBD("select * from productos where codigoproducto='FR-102'");
				Productos.ObtenerProductos(rset, listaproductos);
				System.out.println("Producto Cargado");
				System.out.println("****************");
				break;
			case 4: // Consultar Productos
				Productos.ConsultarProductos(listaproductos, listadetalle);
				System.out.println("Fichero txt creado");
				System.out.println("******************");
				break;
			case 5: // Salir y mostrará el sysout que hay después del do while
				break;
			default:
				System.out.println("Opcion incorrecta - Pulse del 1 al 4 o 5 para salir");
				System.out.println("***************************************************");
				break;
			}
		} while (opciones != 5);
		System.out.println("Has salido del programa, hasta pronto");
	}
}
