package gestionPedidos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetallePedidos {
	/**
	 * Gestión pedidos - Detalle de pedidos
	 * 
	 * @author Robert G
	 * 
	 */
	private String CodigoPedido;
	private String CodigoProducto;
	private int Cantidad;
	private double PrecioUnidad;
	private int Numerolinea;

	public DetallePedidos(String codigoPedido, String codigoProducto, int cantidad, double precioUnidad,
			int numerolinea) {
		super();
		CodigoPedido = codigoPedido;
		CodigoProducto = codigoProducto;
		Cantidad = cantidad;
		PrecioUnidad = precioUnidad;
		Numerolinea = numerolinea;
	}

	public String getCodigoPedido() {
		return CodigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		CodigoPedido = codigoPedido;
	}

	public String getCodigoProducto() {
		return CodigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		CodigoProducto = codigoProducto;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public double getPrecioUnidad() {
		return PrecioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		PrecioUnidad = precioUnidad;
	}

	public int getNumerolinea() {
		return Numerolinea;
	}

	public void setNumerolinea(int numerolinea) {
		Numerolinea = numerolinea;
	}

	@Override
	public String toString() {
		return "detallePedidos [CodigoPedido=" + CodigoPedido + ", CodigoProducto=" + CodigoProducto + ", Cantidad="
				+ Cantidad + ", PrecioUnidad=" + PrecioUnidad + ", Numerolinea=" + Numerolinea + "]";
	}

	public static void CrearDetallePedido(ResultSet rset, ArrayList<DetallePedidos> listadetalle)
			throws SQLException, IOException {

		String directorio = "./ficheros/detalle.csv";
		FileWriter fw = new FileWriter(directorio);
		PrintWriter fichero = new PrintWriter(fw);
		fichero.println("Codigo Pedido;Codigo Producto;Cantidad;Precio Unidad;Numero linea");
		while (rset.next()) {
			fichero.println(rset.getString(1) + ";" + rset.getString(2) + ";" + rset.getInt(3) + ";" + rset.getDouble(4)
					+ ";" + rset.getInt(5));
			listadetalle.add(new DetallePedidos(rset.getString(1), rset.getString(2), rset.getInt(3), rset.getDouble(4),
					rset.getInt(5)));
		}

		fichero.flush();
		fichero.close();
	}
}
