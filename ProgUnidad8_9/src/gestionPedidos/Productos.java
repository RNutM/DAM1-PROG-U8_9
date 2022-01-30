package gestionPedidos;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Productos {
	/**
	 * Gestión pedidos - Clase Productos
	 * 
	 * @author Robert G
	 * 
	 */
	private String CodigoProducto;
	private String Nombre;
	private String Gama;
	private String Dimensiones;
	private String Proveedor;
	private String Descripcion;
	private int CantidadStock;
	private double PrecioVenta;
	private int PrecioProveedor;

	public Productos(String codigoProducto, String nombre, String gama, String dimensiones, String proveedor,
			String descripcion, int cantidadStock, double precioVenta, int precioProveedor) {
		super();
		CodigoProducto = codigoProducto;
		Nombre = nombre;
		Gama = gama;
		Dimensiones = dimensiones;
		Proveedor = proveedor;
		Descripcion = descripcion;
		CantidadStock = cantidadStock;
		PrecioVenta = precioVenta;
		PrecioProveedor = precioProveedor;
	}

	public String getCodigoProducto() {
		return CodigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		CodigoProducto = codigoProducto;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getGama() {
		return Gama;
	}

	public void setGama(String gama) {
		Gama = gama;
	}

	public String getDimensiones() {
		return Dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		Dimensiones = dimensiones;
	}

	public String getProveedor() {
		return Proveedor;
	}

	public void setProveedor(String proveedor) {
		Proveedor = proveedor;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public int getCantidadStock() {
		return CantidadStock;
	}

	public void setCantidadStock(int cantidadStock) {
		CantidadStock = cantidadStock;
	}

	public double getPrecioVenta() {
		return PrecioVenta;
	}

	public void setPrecioVenta(double precioVenta) {
		PrecioVenta = precioVenta;
	}

	public int getPrecioProveedor() {
		return PrecioProveedor;
	}

	public void setPrecioProveedor(int precioProveedor) {
		PrecioProveedor = precioProveedor;
	}

	@Override
	public String toString() {
		return "productos [CodigoProducto=" + CodigoProducto + ", Nombre=" + Nombre + ", Gama=" + Gama
				+ ", Dimensiones=" + Dimensiones + ", Proveedor=" + Proveedor + ", Descripcion=" + Descripcion
				+ ", CantidadStock=" + CantidadStock + ", PrecioVenta=" + PrecioVenta + ", PrecioProveedor="
				+ PrecioProveedor + "]";
	}

	public static void ObtenerProductos(ResultSet rset, ArrayList<Productos> listaproductos) throws SQLException {
		while (rset.next()) {

			listaproductos.add(new Productos(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
					rset.getString(5), rset.getString(6), rset.getInt(7), rset.getDouble(8), rset.getInt(9)));
		}
		System.out.println(listaproductos);
	}

	public static void ConsultarProductos(ArrayList<Productos> listaproductos, ArrayList<DetallePedidos> listadetalle)
			throws FileNotFoundException {
		
		String directorio = "./ficheros/pedidos.txt";
		PrintWriter ppedidos = new PrintWriter(directorio);
		for (Productos p : listaproductos) {
			for (DetallePedidos de : listadetalle) {
				if (p.getCodigoProducto().equals(de.getCodigoProducto())) {
					ppedidos.println("El producto " + p.getNombre() + " se ha pedido");
					ppedidos.println("Detalle: ");
					ppedidos.println("Cantidad: " + de.getCantidad());
					ppedidos.println("Precio Unidad" + de.getPrecioUnidad());
					ppedidos.println("Número de linea" + de.getNumerolinea());
				}
			}
		}
		ppedidos.flush();
		ppedidos.close();
	}
}
